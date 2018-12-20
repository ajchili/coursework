const fs = require('fs');
const path = require('path');
const shortid = require('shortid');

const dataPath = path.join(__dirname, "../data");
const dataDB = path.join(dataPath, "data.json");

const requests = [];

/**
 * Reads user database file and resolves data in file
 * if no error on read occurs.
 * 
 * Returns Promise<JSON object>
 */
const readFromDatabase = () => {
  return new Promise((resolve, reject) => {
    fs.readFile(dataDB, (err, data) => {
      if (err) reject(err);
      else resolve(JSON.parse(data));
    });
  });
};

/**
 * Writes provided JSON objecty to user database file.
 * 
 * @param data JSON object
 * 
 * Returns Promise<Void>
 */
const writeToDatabase = data => {
  return new Promise((resolve, reject) => {
    fs.writeFile(dataDB, JSON.stringify(data), err => {
      if (err) reject(err);
      else resolve();
    });
  });
};

/**
 * Runs next request in requests queue if one exists.
 */
const runNextRequest = () => {
  if (requests.length) {
    let request = requests.shift();
    if (!request) runNextRequest();
    else data[request.method](request.parameters, request.callback);
  }
};

const data = {
  createDatabase: (name, callback) => {
    if (requests.length) {
      requests.push({
        method: 'createDatabase',
        parameters: name,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let id = shortid.generate();
          data[id] = {name, documents: []};
          writeToDatabase(data)
            .then(() => {
              callback(null, id);
              runNextRequest();          
            })
            .catch(err => {
              callback(err, null);
              runNextRequest();
            });
        })
        .catch(err => {
          callback(err, null);
          runNextRequest();
        });
    }
  },
  getDatabase: (id, callback) => {
    if (requests.length) {
      requests.push({
        method: 'getDatabase',
        parameters: id,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[id];
          if (database) {
            callback(null, {id, name: database.name});
            runNextRequest();
          } else {
            callback(new Error('Database does not exist!'), null);
            runNextRequest();
          }
        })
        .catch(err => {
          callback(err, null);
          runNextRequest();
        });
    }
  },
  getAllDatabases: (callback) => {
    if (requests.length) {
      requests.push({
        method: 'getAllDatabases',
        parameters: callback,
        callback: null
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          callback(null, Object.keys(data));
          runNextRequest();
        })
        .catch(err => {
          callback(err, null);
          runNextRequest();
        });
    }
  },
  createDocument: (requirements, callback) => {
    if (requests.length) {
      requests.push({
        method: 'createDocument',
        parameters: requirements,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[requirements.database];
          if (database) {
            let id = shortid.generate();
            let createdAt = new Date().getTime();
            let document = {
              id,
              createdAt,
              updatedAt: createdAt,
              ...requirements.document
            };
            database.documents.push(document);
            writeToDatabase(data)
              .then(() => {
                callback(null, document);
                runNextRequest();
              })
              .catch(err => {
                callback(err, null);
                runNextRequest();
              });
          } else {
            callback(new Error('Database does not exist!'), null);
            runNextRequest();
          }
        })
        .catch(err => {
          callback(err, null);
          runNextRequest();
        });
    }
  }
};

module.exports = data;
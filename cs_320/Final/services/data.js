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
    else data[request.method](request.parameters, request.callback, true);
  }
};

const data = {
  createDatabase: (name, callback, ignoreRequests = false) => {
    if (requests.length && !ignoreRequests) {
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
  getDatabase: (id, callback, ignoreRequests = false) => {
    if (requests.length && !ignoreRequests) {
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
  getAllDatabases: (callback, ignoreRequests = false) => {
    if (requests.length && !ignoreRequests) {
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
  deleteDatabase: (id, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'deleteDatabase',
        parameters: id,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          if (data[id]) {
            data[id] = undefined;
            writeToDatabase(data)
              .then(() => {
                callback(null);
                runNextRequest();
              })
              .catch(err => {
                callback(err);
                runNextRequest();
              });
          } else {
            callback(new Error('Database does not exist!'));
            runNextRequest();
          }
        })
        .catch(err => {
          callback(err);
          runNextRequest();
        });
    }
  },
  createDocument: (requirements, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
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
  },
  getDocument: (requirements, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'getDocument',
        parameters: requirements,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[requirements.database];
          if (database) {
            let document = database.documents.find(document => document.id === requirements.document);
            if (document) {
              callback(null, document);
              runNextRequest();
            } else {
              callback(new Error('Document does not exist!'), null);
              runNextRequest();
            }
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
  getAllDocument: (id, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'getAllDocument',
        parameters: id,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[id];
          if (database) {
            callback(null, database.documents);
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
  deleteDocument: (requirements, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'deleteDocument',
        parameters: requirements,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[requirements.database];
          if (database) {
            let document = database.documents.find(document => document.id === requirements.document);
            if (document) {
              let index = database.documents.indexOf(document);
              database.documents.splice(index, 1);
              writeToDatabase(data)
                .then(() => {
                  callback(null);
                  runNextRequest();
                })
                .catch(err => {
                  callback(err);
                  runNextRequest();
                });
            } else {
              callback(new Error('Document does not exist!'));
              runNextRequest();
            }
          } else {
            callback(new Error('Database does not exist!'));
            runNextRequest();
          }
        })
        .catch(err => {
          callback(err);
          runNextRequest();
        });
    }
  },
  setDocument: (requirements, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'setDocument',
        parameters: requirements,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[requirements.database];
          if (database) {
            let document = database.documents.find(document => document.id === requirements.document);
            if (document) {
              let index = database.documents.indexOf(document);
              database.documents[index] = {
                ...requirements.documentData,
                id: document.id,
                createAt: document.createdAt,
                updatedAt: new Date().getTime()
              };
              writeToDatabase(data)
                .then(() => {
                  callback(null, database.documents[index]);
                  runNextRequest();
                })
                .catch(err => {
                  callback(err, null);
                  runNextRequest();
                });
            } else {
              callback(new Error('Document does not exist!'), null);
              runNextRequest();
            }
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
  updateDocument: (requirements, callback, ignoreRequests = false) => {
   if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'updateDocument',
        parameters: requirements,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          let database = data[requirements.database];
          if (database) {
            let document = database.documents.find(document => document.id === requirements.document);
            if (document) {
              let index = database.documents.indexOf(document);
              database.documents[index] = {
                ...document,
                ...requirements.documentData,
                id: document.id,
                updatedAt: new Date().getTime()
              };
              database.documents[index].createdAt = document.createdAt;
              writeToDatabase(data)
                .then(() => {
                  callback(null, database.documents[index]);
                  runNextRequest();
                })
                .catch(err => {
                  callback(err, null);
                  runNextRequest();
                });
            } else {
              callback(new Error('Document does not exist!'), null);
              runNextRequest();
            }
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
  download: (requirements, callback, ignoreRequests = false) => {
    if (requests.length && !ignoreRequests) {
      requests.push({
        method: 'download',
        parameters: requirements,
        callback
      });
    } else {
      requests.push(null);
      readFromDatabase()
        .then(data => {
          callback(null, data);
          runNextRequest();
        })
        .catch(err => {
          callback(err, null);
          runNextRequest();
        });
    }
  }
};

module.exports = data;
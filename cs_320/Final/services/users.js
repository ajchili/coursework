const bcrypt = require('bcrypt');
const fs = require('fs');
const jwt = require('jsonwebtoken');
const path = require('path');

const dataPath = path.join(__dirname, "../data");
const usersDB = path.join(dataPath, "users.json");

const requests = [];

/**
 * Reads user database file and resolves data in file
 * if no error on read occurs.
 * 
 * Returns Promise<JSON object>
 */
const readFromDatabase = () => {
  return new Promise((resolve, reject) => {
    fs.readFile(usersDB, (err, data) => {
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
    fs.writeFile(usersDB, JSON.stringify(data), err => {
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
    let request = requests.pop();
    users[request.method](request.parameters, request.callback);
  }
};

const users = {
  /**
   * Determines if user exists for provided credentials and if the
   * provided credentials are accurate.
   * 
   * @param credentials User credentials
   * @param callback    Callback function to be called on completion
   */
  validatePassword: (credentials, callback) => {
    if (requests.length) {
      requests.push({
        method: 'validatePassword',
        parameters: credentials,
        callback
      });
    } else {
      readFromDatabase()
        .then(data => {
          let user = data.find(user => user.username === credentials.username);
          if (user) {
            bcrypt.compare(credentials.password, user.password, (err, valid) => {
              callback(err, {valid, user: valid === true ? user : null});
              runNextRequest();
            });
          } else {
            callback(null, null);
            runNextRequest();
          }
        })
        .catch(err => {
          callback(err, null);
          runNextRequest();
        });
    }
  },
  /**
   * Gererates a JWT for a user with the specified id.
   * 
   * @param id       Id of user to generate JWT for
   * @param callback Callback function to called on completion
   */
  generateJWT: (id, callback) => {
    if (requests.length) {
      requests.push({
        method: 'generateJWT',
        parameters: id,
        callback
      });
    } else {
      readFromDatabase()
        .then(data => {
          let user = data.find(user => user.id === id);
          if (user) {
            jwt.sign({ id: user.id, username: user.username}, 'secret', (err, token) => {
              if (err) {
                callback(err, null);
                runNextRequest();
              } else {
                let index = data.indexOf(user);
                data[index].jwt = token;
                writeToDatabase(data)
                  .then(() => {
                    callback(null, token);
                    runNextRequest();
                  })
                  .catch(err => {
                    callback(err, null);
                    runNextRequest();
                  });
              }
            });
          } else {
            callback(new Error('User does not exist!'), null);
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

module.exports = users;
const fs = require('fs');
const path = require('path');

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
    let request = requests.shift();
    data[request.method](request.parameters, request.callback);
  }
};

const data = {
  createDatabase: (name, callback) => {

  },
  createDocument: (requirements, callback) => {
    
  }
};

module.exports = data;
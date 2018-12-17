const bcrypt = require("bcrypt");
const fs = require("fs");
const path = require("path");

const dataPath = path.join(__dirname, "../data");
const usersDB = path.join(dataPath, "users.json");
const dataDB = path.join(dataPath, "data.json");

const bootstrap = {
  /**
   * Determines if any folders/files need to be created in order for
   * service to function properly.
   *
   * Returns Promise<Void>
   */
  validate: () => {
    return new Promise((resolve, reject) => {
      let promises = [
        bootstrap.createDatabase(),
        bootstrap.createUsersDatabase()
      ];

      bootstrap
        .createDataFolder()
        .then(() => {
          Promise.all(promises)
            .then(() => {
              resolve();
            })
            .catch(err => reject(err));
        })
        .catch(err => reject(err));
    });
  },
  /**
   * Creates data folder in current directory if it does not exist.
   *
   * Returns Promise<Void>
   */
  createDataFolder: () => {
    return new Promise((resolve, reject) => {
      fs.exists(dataPath, exists => {
        if (!exists) {
          fs.mkdir(dataPath, err => {
            if (err) reject(err);
            else resolve();
          });
        } else resolve();
      });
    });
  },
  /**
   * Creates users database in data folder if it does not exist.
   *
   * Returns Promise<Void>
   */
  createUsersDatabase: () => {
    return new Promise((resolve, reject) => {
      bcrypt.hash("password", 10, (err, hash) => {
        if (err) reject(err);
        else {
          let defaultData = [
            {
              id: 1,
              username: "root",
              password: hash
            }
          ];

          fs.exists(usersDB, exists => {
            if (!exists) {
              fs.writeFile(usersDB, JSON.stringify(defaultData), err => {
                if (err) reject(err);
                else resolve();
              });
            } else resolve();
          });
        }
      });
    });
  },
  /**
   * Creates database in data folder if it does not exist.
   *
   * Returns Promise<Void>
   */
  createDatabase: () => {
    return new Promise((resolve, reject) => {
      fs.exists(dataDB, exists => {
        if (!exists) {
          fs.writeFile(dataDB, JSON.stringify({}), err => {
            if (err) reject(err);
            else resolve();
          });
        } else resolve();
      });
    });
  }
};

module.exports = bootstrap;

const express = require('express');
const router = express.Router();
const users = require('../services/users');
const data = require('../services/data');

router.use((req, res, next) => {
  if (!req.headers || !req.headers.authorization) res.status(401).send();
  else {
    let parts = req.headers.authorization.split(' ');
    if (parts.length === 2 && parts[0] === 'Bearer') {
      users.verifyJWT(parts[1], (err, expired) => {
        if (err) res.status(500).send();
        else if (expired) res.status(401).send();
        else next();
      });
    } else res.status(400).send('Bearer TOKEN must be provided!');
  }
});

router.post('/create', (req, res) => {
  if (!req.body.name && !req.body.names) res.status(400).send();
  else {
    let names = req.body.names || [req.body.name];
    let promises = [];
    names.forEach(name => {
      promises.push(new Promise((resolve, reject) => {
        data.createDatabase(name, (err, id) => {
          if (err) reject(err);
          else resolve(id);
        });
      }));
    });
    Promise.all(promises)
      .then(ids => res.status(200).send(ids))
      .catch(err => res.status(500).send());
  }
});

router.get('/', (req, res) => {
  data.getAllDatabases((err, databases) => {
    if (err) res.status(500).send();
    else res.status(200).json(databases);
  });
});

router.get('/:id', (req, res) => {
  let id = req.params.id;
  data.getDatabase(id, (err, database) => {
    if (err) res.status(500).send();
    else res.status(200).json(database);
  });
});

router.post('/documents/create', (req, res) => {
  if (!req.body.database) res.status(400).send();
  else {
    let database = req.body.database;
    let document = req.body.document || {};
    data.createDocument({database, document}, (err, document) => {
      if (err) res.status(500).send();
      else res.status(200).json(document);
    });
  }
});

router.get('/:id/documents', (req, res) => {
  let id = req.params.id;
  console.log(id);
  data.getAllDocument(id, (err, documents) => {
    console.error(err)
    if (err) res.status(500).send();
    else res.status(200).json(documents);
  });
});

router.get('/:database/documents/:document', (req, res) => {
  let database = req.params.database;
  let document = req.params.document;
  data.getDocument({database, document}, (err, document) => {
    if (err) res.status(500).send();
    else res.status(200).json(document);
  });
});

module.exports = router;
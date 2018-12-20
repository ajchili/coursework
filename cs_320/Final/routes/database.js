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

router.post('/delete', (req, res) => {
  if (!req.body.id) res.status(400).send();
  else {
    let id = req.body.id;
    data.deleteDatabase(id, (err) => {
      if (err) res.status(500).send();
      else res.status(200).send();
    });
  }
});

router.get('/:id', (req, res) => {
  let id = req.params.id;
  data.getDatabase(id, (err, database) => {
    if (err) res.status(500).send();
    else res.status(200).json(database);
  });
});

router.get('/:id/documents', (req, res) => {
  let id = req.params.id;
  data.getAllDocument(id, (err, documents) => {
    if (err) res.status(500).send();
    else res.status(200).json(documents);
  });
});

router.post('/:id/documents/create', (req, res) => {
  let database = req.params.id;
  let document = req.body.document || {};
  data.createDocument({database, document}, (err, document) => {
    if (err) res.status(500).send();
    else res.status(200).json(document);
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

router.post('/:database/documents/:document/set', (req, res) => {
  let database = req.params.database;
  let document = req.params.document;
  let documentData = req.body.data || {};
  data.setDocument({database, document, documentData}, (err, document) => {
    if (err) res.status(500).send();
    else res.status(200).json(document);
  });
});

router.post('/:database/documents/:document/update', (req, res) => {
  let database = req.params.database;
  let document = req.params.document;
  let documentData = req.body.data || {};
  data.updateDocument({database, document, documentData}, (err, document) => {
    if (err) res.status(500).send();
    else res.status(200).send(document);
  });
});

router.post('/:database/documents/:document/delete', (req, res) => {
  let database = req.params.database;
  let document = req.params.document;
  data.deleteDocument({database, document}, (err) => {
    if (err) res.status(500).send();
    else res.status(200).send();
  });
});

module.exports = router;
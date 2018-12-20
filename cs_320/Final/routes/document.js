const express = require('express');
const router = express.Router();
const data = require('../services/data');

router.post('/create', (req, res) => {
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

module.exports = router;
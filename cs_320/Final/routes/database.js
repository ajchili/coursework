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

router.get('/', (req, res) => {
  res.status(200).send('hello');
});

module.exports = router;
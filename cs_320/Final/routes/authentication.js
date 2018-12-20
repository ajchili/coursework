const express = require('express');
const router = express.Router();
const users = require('../services/users');

router.post('/login', (req, res) => {
  if (!req.body || !req.body.username || !req.body.password) {
    return res.status(400).send();
  } else {
    let credentials = {
      username: req.body.username,
      password: req.body.password
    };
    users.validatePassword(credentials, (err, data) => {
      if (err || !data.valid) res.status(401).send();
      else if (data.user) {
        users.generateJWT(data.user.id, (err, token) => {
          if (err) res.status(500).send();
          else res.status(200).send(token);
        })
      } else res.status(500).send();
    });
  }
});

module.exports = router;
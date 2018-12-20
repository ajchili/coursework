const bodyParser = require('body-parser');
const bootstrap = require("./config/bootstrap");
const express = require("express");
const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.get("/", (req, res) => res.send("Service not setup."));
app.use(require('./routes/authentication'));

bootstrap
  .validate()
  .then(() => {
    app.listen(port, () => {
      console.log(`Service started on port: ${port}`);
    });
  })
  .catch(err => console.error(err));

const bootstrap = require("./config/bootstrap");
const express = require("express");
const app = express();
const port = 3000;

app.get("/", (req, res) => res.send("Service not setup."));

bootstrap
  .validate()
  .then(() => {
    app.listen(port, () => {
      console.log(`Service started on port: ${port}`);
    });
  })
  .catch(err => console.error(err));

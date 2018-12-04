const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => res.send('Service not setup.'));

app.listen(port, () => {
  console.log(`Service started on port: ${port}`);
});

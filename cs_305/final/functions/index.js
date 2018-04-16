const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

let db = admin.firestore();

exports.createUser = functions.https.onRequest((req, res) => {
    if (req.query.name == null) {
        res.status(400).send('A name is required to create a user.');
    } else if (req.query.age == null) {
        res.status(400).send('An age is required to create a user.');
    } else {
        let id = db.collection('users').doc().id;
        db.collection('users').doc(id).set({
            name: req.query.name,
            age: req.query.age
        }).then(() => {
            res.status(201).send({ id: id });
        }).catch((err) => {
            res.status(500).send('Unable to create user.');
        });
    }
});

exports.getPhoto = functions.https.onRequest((req, res) => {
    if (req.query.id != null) {
        db.collection('photos').doc(req.query.id).get().then((snapshot) => {
            res.send({ id: snapshot.id, data: snapshot.data() });
        }).catch((err) => {
            res.status(500).send('Unable to query data.');
        });
    } else {
        res.status(400).send('No photo id provided.');
    }
});

exports.getRecentPhotos = functions.https.onRequest((req, res) => {
    let limit = req.query.limit != null ? parseInt(req.query.limit) : 10;

    db.collection('photos').limit(limit).get().then((snapshot) => {
        let recentPhotos = [];

        snapshot.forEach(function (doc) {
            recentPhotos.push({
                id: doc.id,
                data: doc.data()
            });
        });

        res.send(recentPhotos);
    }).catch((err) => {
        res.status(500).send('Unable to query data.');
    });
});

exports.getRecentPhotosOfUser = functions.https.onRequest((req, res) => {
    let limit = req.query.limit != null ? parseInt(req.query.limit) : 10;

    if (req.query.id != null) {
        db.collection('photos').limit(limit).where('user', '==', req.query.id).get().then((snapshot) => {
            let recentPhotos = [];

            snapshot.forEach(function (doc) {
                recentPhotos.push({
                    id: doc.id,
                    data: doc.data()
                });
            });

            res.send(recentPhotos);
        }).catch((err) => {
            res.status(500).send('Unable to query data.');
        });
    } else {
        res.status(400).send('No user id provided.');
    }
});

exports.getUser = functions.https.onRequest((req, res) => {
    let limit = req.query.limit != null ? parseInt(req.query.limit) : 10;

    if (req.query.id != null) {
        db.collection('users').doc(req.query.id).get().then((snapshot) => {
            res.send({ id: snapshot.id, data: snapshot.data() });
        }).catch((err) => {
            res.status(500).send('Unable to query data.');
        });
    } else {
        res.status(400).send('No user id provided.');
    }
});

exports.getUsers = functions.https.onRequest((req, res) => {
    let limit = req.query.limit != null ? parseInt(req.query.limit) : 10;
    db.collection('users').limit(limit).get().then((snapshot) => {
        let users = [];

        snapshot.forEach(function (doc) {
            users.push({
                id: doc.id,
                data: doc.data()
            });
        });

        res.send(users);
    }).catch((err) => {
        res.status(500).send('Unable to query data.');
    });
});

exports.uploadPhoto = functions.https.onRequest((req, res) => {
    if (req.method != 'POST') {
        res.status(404).send('Method must be POST.');
    } else if (req.query.id == null) {
        res.status(400).send('No user id provided.');
    } else if (req.query.title == null) {
        res.status(400).send('No title provided.');
    } else {
        db.collection('users').doc(req.query.id).get().then((snapshot) => {
            if (snapshot.exists) {
                let id = db.collection('photos').doc().id;

                db.collection('photos').doc(id).set({
                    user: req.query.id,
                    title: req.query.title,
                    url: "https://www.google.com/"
                }).then(() => {
                    res.status(201).send({ id: id });
                }).catch((err) => {
                    res.status(500).send('Unable to upload photo.');
                });
            } else {
                res.status(404).send('User does not exist.');
            }
        }).catch((err) => {
            res.status(500).send('Unable to upload photo.');
        });
    }
});

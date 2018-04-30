const functions = require('firebase-functions');
const admin = require('firebase-admin');
const gcs = require('@google-cloud/storage')();
const path = require('path');
const formidable = require('formidable');

admin.initializeApp();

let db = admin.firestore();
let bucket = admin.storage().bucket();

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
            res.send({ 
                id: doc.id,
                user: doc.data().user,
                title: doc.data().title,
                url: `https://firebasestorage.googleapis.com/v0/b/${bucket.name}/o/${encodeURIComponent(doc.data().url)}?alt=media&token=${new Date().getTime()}`
            });
        }).catch((err) => {
            res.status(500).send('Unable to query data.');
        });
    } else {
        res.status(400).send('No photo id provided.');
    }
});

exports.getRecentPhotos = functions.https.onRequest((req, res) => {
    let limit = req.query.limit != null ? parseInt(req.query.limit) : 10;

    db.collection('photos').limit(limit).get().then(snapshot => {
        let recentPhotos = [];

        snapshot.forEach(doc => {
            recentPhotos.push({
                id: doc.id,
                user: doc.data().user,
                title: doc.data().title,
                url: `https://firebasestorage.googleapis.com/v0/b/${bucket.name}/o/${encodeURIComponent(doc.data().url)}?alt=media&token=${new Date().getTime()}`
            });
        });

        res.send(recentPhotos);
    }).catch(err => {
        console.log(err);
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
                    user: doc.data().user,
                    title: doc.data().title,
                    url: `https://firebasestorage.googleapis.com/v0/b/${bucket.name}/o/${encodeURIComponent(doc.data().url)}?alt=media&token=${new Date().getTime()}`
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
    if (req.query.id != null) {
        db.collection('users').doc(req.query.id).get().then(snapshot => {
            res.send({ 
                id: snapshot.id, 
                name: snapshot.data().name,
                age: snapshot.data().age
            });
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
                name: doc.data().name,
                age: doc.data().age
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
        return new Promise((resolve, reject) => {
            var form = new formidable.IncomingForm();

            form.parse(req, (err, fields, files) => {
                try {
                    let file = files.image;
                    let filePath = file.path;

                    db.collection('users').doc(req.query.id).get().then((snapshot) => {
                        if (snapshot.exists) {
                            let id = db.collection('photos').doc().id;

                            bucket.upload(filePath, {
                                destination: 'photos/' + req.query.id + '/' + id,
                                metadata: {
                                    contentType: file.type
                                }
                            }).then((file) => {
                                db.collection('photos').doc(id).set({
                                    user: req.query.id,
                                    title: req.query.title,
                                    url: file[0].name
                                }).then(() => {
                                    resolve(id);
                                }).catch((err) => {
                                    reject(err);
                                });
                            }).catch((err) => {
                                reject(err);
                            });
                        } else {
                            res.status(404).send('User does not exist.');
                        }
                    }).catch((err) => {
                        reject(err);
                    });
                } catch (error) {
                    reject();
                }
            });
        }).then((id) => {
            res.status(201).send({ id: id });
        }).catch((err) => {
            res.status(500).send(err + 'Unable to upload photo.');
        });
    }
});

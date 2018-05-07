package com.kirinpatel.androidapp.utils;

import android.annotation.TargetApi;
import android.os.Build;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import static java.net.HttpURLConnection.HTTP_OK;

public class API {

    private static final String LOCAL_TUNNEL = "https://mean-treefrog-69.localtunnel.me";
    private static final String BASE_URL = "https://us-central1-cs-305-final.cloudfunctions.net/";
    private static final String LOCALHOST = LOCAL_TUNNEL + "/cs-305-final/us-central1/";

    private static String getRequest(String request) throws IOException {
        URL tempURL = new URL(BASE_URL + request);
        HttpsURLConnection connection = (HttpsURLConnection) tempURL.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HTTP_OK
                && connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new IOException(connection.getResponseMessage()
                        + " (" + connection.getResponseCode() + "): with "
                        + tempURL.toString());
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0 , bytesRead);
            }

            out.close();

            return new String(out.toByteArray());
        } finally {
            connection.disconnect();
        }
    }

    private static String postRequest(String id, String title, File photo) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(LOCALHOST + "uploadPhoto?id="
                    + id + "&title=" + title);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("image", new FileBody(photo))
                    .build();
            httppost.setEntity(reqEntity);

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return EntityUtils.toString(resEntity);
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

        return "";
    }

    public static class user {

        public static final int API = 0;

        public static class create {

            public static final int REQUEST = 0;

            public static final String DESCRIPTION = "Creates a user and returns their id.";

            public static String run(String name, int age) throws IOException {
                return getRequest("createUser?name=" + name + "&age=" + age);
            }
        }

        public static class get {

            public static final int REQUEST = 1;

            public static final String DESCRIPTION = "Returns the user with the provided id.";

            public static String run(String id) throws IOException {
                return getRequest("getUser?id=" + id);
            }
        }

        public static class getPhotos {

            public static final int REQUEST = 2;

            public static final String DESCTIPTION = "Returns the last 10 (or specified count) of" +
                    " pictures uploaded by the user with the provided id.";

            public static String run(String id) throws IOException {
                return getRequest("getRecentPhotosOfUser?id=" + id);
            }

            public static String run(String id, int limit) throws IOException {
                return getRequest("getRecentPhotosOfUser?id=" + id + "&limit=" + limit);
            }
        }
    }

    public static class users {

        public static final int API = 1;

        public static class get {

            public static final int REQUEST = 0;

            public static final String DESCRIPTION = "Returns the last 10 (or specified count) of" +
                    " users that have been created.";

            public static String run() throws IOException {
                return getRequest("getUsers");
            }

            public static String run(int limit) throws IOException {
                return getRequest("getUsers?limit=" + limit);
            }
        }
    }

    public static class photo {

        public static final int API = 2;

        public static class get {

            public static final int REQUEST = 0;

            public static final String DESCRIPTION = "Returns the photo with the provided id.";

            public static String run(String id) throws IOException {
                return getRequest("getPhoto?id=" + id);
            }
        }

        public static class upload {

            public static final int REQUEST = 1;

            public static final String DESCRIPTION = "Uploads a photo for a user with the" +
                    " provided id.";

            public static String run(String id, String title, File photo) throws IOException {
                return postRequest(id, title, photo);
            }
        }
    }

    public static class photos {

        public static final int API = 3;

        public static class getRecent {

            public static final int REQUEST = 0;

            public static final String DESCRIPTION = "Returns the last 10 (or specified count) of" +
                    " photos that have been uploaded.";

            public static String run() throws IOException {
                return getRequest("getRecentPhotos");
            }

            public static String run(String limit) throws IOException {
                return getRequest("getRecentPhotos?limit=" + limit);
            }
        }
    }

    public static class Request {

        private int api;
        private int request;
        private Map<String, Object> params;

        public Request(int api, int request, Map<String, Object> params) {
            this.api = api;
            this.request = request;
            this.params = params;
        }

        public int getAPI() {
            return api;
        }

        public int getREQUEST() {
            return request;
        }

        public String run() throws IOException {
            switch (api) {
                case 0:
                    switch (request) {
                        case 0:
                            return API.user.create.run(params.get("name").toString(),
                                    (int) params.get("age"));
                        case 1:
                            return API.user.get.run(params.get("id").toString());
                        case 2:
                            if (params.containsKey("limit")) {
                                return API.user.getPhotos.run(params.get("id").toString(),
                                        (int) params.get("limit"));
                            } else {
                                return API.user.getPhotos.run(params.get("id").toString());
                            }
                        default:
                            throw new IOException("Unable to run, no request specified!");
                    }
                case 1:
                    if (params.containsKey("limit")) {
                        return API.users.get.run((int) params.get("limit"));
                    } else {
                        return API.users.get.run();
                    }
                case 2:
                    switch (request) {
                        case 0:
                            return API.photo.get.run(params.get("id").toString());
                        case 1:
                            return API.photo.upload.run(params.get("id").toString(),
                                    params.get("title").toString(),
                                    (File) params.get("photo"));
                        default:
                            throw new IOException("Unable to run, no request specified!");
                    }
                case 3:
                    if (params.containsKey("limit")) {
                        return API.photos.getRecent.run(params.get("limit").toString());
                    } else {
                        return API.photos.getRecent.run();
                    }
                default:
                    throw new IOException("Unable to run, no api specified!");
            }
        }
    }
}

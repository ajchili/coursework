package com.kirinpatel.androidapp.utils;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PhotoFetcher {

    public byte[] getUrlBytes(String url) throws IOException {
        URL tempURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) tempURL.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + url);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0 , bytesRead);
            }

            out.close();

            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String url) throws IOException {
        return new String(getUrlBytes(url));
    }

    public ArrayList<Photo> fetchItems() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String url = Uri.parse("https://us-central1-cs-305-final.cloudfunctions.net/getRecentPhotos")
                    .buildUpon()
                    .build()
                    .toString();
            String json = getUrlString(url);
            JSONArray jsonObject = new JSONArray(json);
            parseItems(photos, jsonObject);
        } catch(IOException | JSONException e) {
             Log.e("Fetch Error", e.getMessage());
        }

        return photos;
    }

    private void parseItems(ArrayList<Photo> photos, JSONArray photoArray) throws JSONException {
        for (int i = 0; i < photoArray.length(); i++) {
            JSONObject photo = photoArray.getJSONObject(i);

            Photo tempPhoto = new Photo(photo.getString("id"),
                    new User(photo.getString("user")),
                    photo.getString("title"),
                    photo.getString("url"));

            photos.add(tempPhoto);
        }
    }
}

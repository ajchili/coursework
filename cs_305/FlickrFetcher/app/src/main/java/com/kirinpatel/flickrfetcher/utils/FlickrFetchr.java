package com.kirinpatel.flickrfetcher.utils;

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
import java.util.List;

public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "a8d46ce526c95d46b4fc70b3b7c39fef";

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

    private String getUrlString(String url) throws IOException {
        return new String(getUrlBytes(url));
    }

    public List<Photo> fetchItems() {
        List<Photo> photos = new ArrayList<>();

        try {
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build()
                    .toString();
            String json = getUrlString(url);
            Log.i(TAG, "Fetched: " + json);
            JSONObject jsonObject = new JSONObject(json);
            parseItems(photos, jsonObject);
        } catch(IOException | JSONException e) {
            Log.e(TAG, "Failed: " + e);
        }

        return photos;
    }

    private void parseItems(List<Photo> photos, JSONObject json) throws JSONException {
        JSONArray photoArray = json.getJSONObject("photos").getJSONArray("photo");

        for (int i = 0; i < photoArray.length(); i++) {
            JSONObject photo = photoArray.getJSONObject(i);

            if (!photo.has("url_s"))
                continue;

            Photo tempPhoto = new Photo(photo.getString("id"),
                    photo.getString("title"),
                    photo.getString("url_s"));

            photos.add(tempPhoto);
        }
    }
}

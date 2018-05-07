package com.kirinpatel.androidapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kirinpatel.androidapp.R;
import com.kirinpatel.androidapp.utils.API;
import com.kirinpatel.androidapp.utils.Photo;
import com.kirinpatel.androidapp.utils.PhotoFetcher;
import com.kirinpatel.androidapp.utils.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DemoDetailActivity extends AppCompatActivity {

    private static final String KEY_PHOTO_ID = "photoId";
    private static final String KEY_USER_ID = "userId";

    private Photo photo;
    private ImageView imageView;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_detail);

        if (getIntent().getSerializableExtra(KEY_PHOTO_ID) != null &&
                getIntent().getStringExtra(KEY_USER_ID) != null) {
            photo = (Photo) getIntent().getSerializableExtra(KEY_PHOTO_ID);
            String userId = getIntent().getStringExtra(KEY_USER_ID);

            imageView = findViewById(R.id.demo_detail_image);
            TextView title = findViewById(R.id.demo_detail_title);
            title.setText(photo.getTitle());
            author = findViewById(R.id.demo_detail_author);

            new FetchPhoto().execute();
            Map<String, Object> params = new HashMap<>();
            params.put("id", userId);
            new HTTPRequestTask().execute(
                    new API.Request(API.user.API, API.user.get.REQUEST, params));
        }
    }

    public static Intent createIntent(Context context, Photo photo) {
        Intent intent = new Intent(context, DemoDetailActivity.class);
        intent.putExtra(KEY_PHOTO_ID, photo);
        intent.putExtra(KEY_USER_ID, photo.getUser().getId());
        return intent;
    }

    private class FetchPhoto extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                byte[] bytes = new PhotoFetcher().getUrlBytes(photo.getUrl());
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            } catch (IOException e) {
                cancel(true);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);

            if (bitmap == null) {
                Toast.makeText(DemoDetailActivity.this,
                        "Unable to download image.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    class HTTPRequestTask extends AsyncTask<API.Request, Void, Map<String, Object>> {

        @Override
        protected Map<String, Object> doInBackground(API.Request... requests) {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("request", requests[0]);
                String result = requests[0].run();
                map.put("result", result);
                return map;
            } catch (IOException e) {
                Map<String, Object> errorMap = new HashMap<>();
                errorMap.put("request", requests[0]);
                errorMap.put("error",  e);
                return errorMap;
            }
        }

        @Override
        protected void onPostExecute( Map<String, Object> map) {
            if (map.containsKey("error")) {
                Toast.makeText(getApplicationContext(),
                        map.get("error").toString(),
                        Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(map.get("result").toString());
                    User user = new User(jsonObject.getString("id"),
                            jsonObject.getString("name"),
                            jsonObject.getInt("age"));
                    author.setText("Uploaded by: " + user.getName());
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

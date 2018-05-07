package com.kirinpatel.androidapp.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.kirinpatel.androidapp.R;
import com.kirinpatel.androidapp.adapters.PhotoAdapter;
import com.kirinpatel.androidapp.utils.API;
import com.kirinpatel.androidapp.utils.Photo;
import com.kirinpatel.androidapp.utils.PhotoFetcher;
import com.kirinpatel.androidapp.utils.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DemoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Photo> photos = new ArrayList<>();
    private File photo;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = findViewById(R.id.demo_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(DemoActivity.this, 3));

        setupAdapter();

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 100);
        new HTTPRequestTask().execute(new API.Request(API.photos.API,
                API.photos.getRecent.REQUEST,
                params));
    }

    @Override
    public void onResume() {
        super.onResume();

        if (photo != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", currentUserId);
            params.put("title", System.currentTimeMillis());
            params.put("photo", photo);
            new UploadPhotoTask().execute(new API.Request(API.photo.API,
                    API.photo.upload.REQUEST,
                    params));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_demo_createUser:
                Map<String, Object> params = new HashMap<>();
                params.put("name", System.currentTimeMillis());
                params.put("age", new Random().nextInt(100) + 1);
                new HTTPRequestTask().execute(new API.Request(API.user.API,
                        API.user.create.REQUEST,
                        params));
                return true;
            case R.id.menu_demo_uploadPhoto:
                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File directory = getApplicationContext().getFilesDir();
                photo = new File(directory, String.valueOf(System.currentTimeMillis()));
                Uri uri = FileProvider.getUriForFile(DemoActivity.this,
                        "com.kirinpatel.androidapp.FileProvider", photo);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                List<ResolveInfo> cameraActivities =
                        getPackageManager().queryIntentActivities(captureImage,
                                PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo info : cameraActivities) {
                    grantUriPermission(info.activityInfo.packageName,
                            uri,
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, 2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupAdapter() {
        recyclerView.setAdapter(new PhotoAdapter(photos));
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
        protected void onPostExecute(Map<String, Object> map) {
            if (map.containsKey("error")) {
                Toast.makeText(getApplicationContext(),
                        map.get("error").toString(),
                        Toast.LENGTH_SHORT).show();
            } else {
                if (((API.Request) map.get("request")).getAPI() == API.photos.API) {
                    try {
                        JSONArray jsonArray = new JSONArray(map.get("result").toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject photo = jsonArray.getJSONObject(i);

                            Photo tempPhoto = new Photo(photo.getString("id"),
                                    new User(photo.getString("user")),
                                    photo.getString("title"),
                                    photo.getString("url"));

                            photos.add(tempPhoto);
                        }

                        setupAdapter();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),
                                e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (((API.Request) map.get("request")).getAPI() == API.user.API) {
                    try {
                        JSONObject jsonObject = new JSONObject(map.get("result").toString());
                        currentUserId = jsonObject.getString("id");
                        Toast.makeText(getApplicationContext(),
                                "User created with ID: " + currentUserId,
                                Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),
                                e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    System.out.println(map.get("result").toString());
                }
            }
        }
    }

    class UploadPhotoTask extends AsyncTask<API.Request, Void, Map<String, Object>> {

        @Override
        protected Map<String, Object> doInBackground(API.Request... requests) {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("request", requests[0]);
                String result = requests[0].run();
                map.put("result", result);
                return map;
            } catch (IOException e) {
                e.printStackTrace();
                Map<String, Object> errorMap = new HashMap<>();
                errorMap.put("request", requests[0]);
                errorMap.put("error",  e);
                return errorMap;
            }
        }

        @Override
        protected void onPostExecute(Map<String, Object> map) {
            if (map.containsKey("error")) {
                System.out.println(map.get("error").toString());
            } else {
                System.out.println(map.get("result").toString());
            }

            photo = null;
        }
    }
}

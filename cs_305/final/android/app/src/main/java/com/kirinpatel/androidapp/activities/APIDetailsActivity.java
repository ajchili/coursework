package com.kirinpatel.androidapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kirinpatel.androidapp.R;
import com.kirinpatel.androidapp.utils.API;

public class APIDetailsActivity extends AppCompatActivity {

    private static final String API_KEY = "api";
    private static final String REQUEST_KEY = "request";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_details);

        TextView title = findViewById(R.id.api_details_textView_title);
        TextView description = findViewById(R.id.api_details_textView_description);

        switch (getIntent().getIntExtra(API_KEY, -1)) {
            case 0:
                switch (getIntent().getIntExtra(REQUEST_KEY, -1)) {
                    case 0:
                        title.setText("Create User");
                        description.setText(API.user.create.DESCRIPTION);
                        break;
                    case 1:
                        title.setText("Get User");
                        description.setText(API.user.get.DESCRIPTION);
                        break;
                    case 2:
                        title.setText("Get Photos of User");
                        description.setText(API.user.getPhotos.DESCTIPTION);
                        break;
                    default:
                        title.setText("Unknown");
                        description.setText("Could not load REQUEST.");
                        break;
                }
                break;
            case 1:
                switch (getIntent().getIntExtra(REQUEST_KEY, -1)) {
                    case 0:
                        title.setText("Get Users");
                        description.setText(API.users.get.DESCRIPTION);
                        break;
                }
                break;
            case 2:
                switch (getIntent().getIntExtra(REQUEST_KEY, -1)) {
                    case 0:
                        title.setText("Get Photo");
                        description.setText(API.photo.get.DESCRIPTION);
                        break;
                    case 1:
                        title.setText("Upload Photo");
                        description.setText(API.photo.upload.DESCRIPTION);
                        break;
                }
                break;
            case 3:
                switch (getIntent().getIntExtra(REQUEST_KEY, -1)) {
                    case 0:
                        title.setText("Get Recent Photos");
                        description.setText(API.photos.getRecent.DESCRIPTION);
                        break;
                }
                break;
            default:
                title.setText("Unknown");
                description.setText("Could not load API or REQUEST.");
                break;
        }
    }

    public static Intent getIntent(Context context, int api, int request) {
        Intent intent = new Intent(context, APIDetailsActivity.class);
        intent.putExtra(API_KEY, api);
        intent.putExtra(REQUEST_KEY, request);
        return intent;
    }
}

package com.kirinpatel.androidapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kirinpatel.androidapp.R;
import com.kirinpatel.androidapp.utils.API;

public class APIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        findViewById(R.id.api_button_createUser).setOnClickListener(
                new APIButtonOnClickListener(API.user.API, API.user.create.REQUEST));
        findViewById(R.id.api_button_getUser).setOnClickListener(
                new APIButtonOnClickListener(API.user.API, API.user.get.REQUEST));
        findViewById(R.id.api_button_getRecentPhotosOfUser).setOnClickListener(
                new APIButtonOnClickListener(API.user.API, API.user.getPhotos.REQUEST));
        findViewById(R.id.api_button_getPhoto).setOnClickListener(
                new APIButtonOnClickListener(API.photo.API, API.photo.get.REQUEST));
        findViewById(R.id.api_button_uploadPhoto).setOnClickListener(
                new APIButtonOnClickListener(API.photo.API, API.photo.upload.REQUEST));
        findViewById(R.id.api_button_getUsers).setOnClickListener(
                new APIButtonOnClickListener(API.users.API, API.users.get.REQUEST));
        findViewById(R.id.api_button_getRecentPhotos).setOnClickListener(
                new APIButtonOnClickListener(API.photos.API, API.photos.getRecent.REQUEST));
    }

    class APIButtonOnClickListener implements View.OnClickListener {

        private final int api, request;

        APIButtonOnClickListener(int api, int request) {
            this.api = api;
            this.request = request;
        }

        @Override
        public void onClick(View view) {
            startActivity(APIDetailsActivity.getIntent(view.getContext(), api, request));
        }
    }
}

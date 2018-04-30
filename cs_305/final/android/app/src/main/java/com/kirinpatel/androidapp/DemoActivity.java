package com.kirinpatel.androidapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kirinpatel.androidapp.adapters.PhotoAdapter;
import com.kirinpatel.androidapp.utils.Photo;
import com.kirinpatel.androidapp.utils.PhotoFetcher;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Photo> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = findViewById(R.id.demo_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(DemoActivity.this, 3));

        setupAdapter();
        new FetchPhotosTask().execute();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupAdapter() {
        recyclerView.setAdapter(new PhotoAdapter(photos));
    }

    private class FetchPhotosTask extends AsyncTask<Void, Void, ArrayList<Photo>> {

        @Override
        protected ArrayList<Photo> doInBackground(Void... voids) {
            return new PhotoFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(ArrayList<Photo> fetchedPhotos) {
            super.onPostExecute(fetchedPhotos);

            photos.addAll(fetchedPhotos);
            setupAdapter();
        }
    }
}

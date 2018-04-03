package com.kirinpatel.flickrfetcher;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kirinpatel.flickrfetcher.adapter.PhotoAdapter;
import com.kirinpatel.flickrfetcher.utils.FlickrFetchr;
import com.kirinpatel.flickrfetcher.utils.Photo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Photo> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_photo_gallery);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));

        setupAdapter();
        new FetchItemsTask().execute();
    }

    private void setupAdapter() {
        recyclerView.setAdapter(new PhotoAdapter(photos));
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<Photo>> {

        @Override
        protected List<Photo> doInBackground(Void... voids) {
            return new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<Photo> fetchedPhotos) {
            photos.addAll(fetchedPhotos);
            setupAdapter();
        }
    }
}

package com.kirinpatel.androidapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kirinpatel.androidapp.R;
import com.kirinpatel.androidapp.utils.Photo;
import com.kirinpatel.androidapp.utils.PhotoFetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private ArrayList<Photo> photos;
    private static Map<String, Bitmap> images = new HashMap<>();

    public PhotoAdapter(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class PhotoHolder extends ViewHolder {

        private Photo photo;
        private ImageView imageView;

        PhotoHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.list_photo_image);
        }

        void bind(Photo photo) {
            this.photo = photo;

            bindImageView();
        }

        void bindImageView() {
            if (images.containsKey(photo.getId())) {
                imageView.setImageBitmap(images.get(photo.getId()));
            } else {
                imageView.setImageDrawable(null);
                new FetchPhotoURL().execute(photo);
            }
        }

        private class FetchPhotoURL extends AsyncTask<Photo, Void, Void> {

            @Override
            protected Void doInBackground(Photo... photos) {
                if (photos.length > 0) {
                    try {
                        byte[] bytes = new PhotoFetcher().getUrlBytes(photos[0].getUrl());
                        images.put(photos[0].getId(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    } catch (IOException e) {
                        cancel(true);
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                bindImageView();
            }
        }
    }
}

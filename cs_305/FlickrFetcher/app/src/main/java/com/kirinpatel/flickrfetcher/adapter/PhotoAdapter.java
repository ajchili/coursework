package com.kirinpatel.flickrfetcher.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirinpatel.flickrfetcher.R;
import com.kirinpatel.flickrfetcher.utils.FlickrFetchr;
import com.kirinpatel.flickrfetcher.utils.Photo;

import java.io.IOException;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private List<Photo> photos;

    static class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Photo photo;
        private TextView photoCaption;
        private ImageView imageView;

        PhotoHolder(View view) {
            super(view);

            photoCaption = view.findViewById(R.id.photo_caption);
            imageView = view.findViewById(R.id.photo_photo);
        }

        void bind(Photo photo) {
            this.photo = photo;
            photoCaption.setText(this.photo.getCaption());
            imageView.setImageDrawable(null);
            if (this.photo.getImageBitmap() != null) {
                bindImageView();
            } else {
                new FetchImagesTask().execute(this.photo.getUrl());
            }
        }

        @Override
        public void onClick(View view) {

        }

        void bindImageView() {
            imageView.setImageBitmap(photo.getImageBitmap());
        }

        void bindImageView(Bitmap imageBitmap) {
            photo.setImageBitmap(imageBitmap);
            bindImageView();
        }

        private class FetchImagesTask extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... urls) {
                if (urls.length > 0) {
                    try {
                        byte[] bytes = new FlickrFetchr().getUrlBytes(urls[0]);
                        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    } catch (IOException e) {
                        cancel(true);
                        return null;
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Bitmap imageBitmap) {
                super.onPostExecute(imageBitmap);

                if (imageBitmap != null) {
                    bindImageView(imageBitmap);
                }
            }
        }
    }

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_photo, parent,false);
        return new PhotoHolder(view);
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
}

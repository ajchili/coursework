package com.kirinpatel.flickrfetcher.adapter;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirinpatel.flickrfetcher.R;
import com.kirinpatel.flickrfetcher.utils.Photo;

import java.net.URI;
import java.net.URISyntaxException;
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
            photoCaption.setText(photo.getCaption());
            imageView.setImageDrawable(photo.getDrawable());
        }

        @Override
        public void onClick(View view) {

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

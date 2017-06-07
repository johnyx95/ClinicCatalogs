package com.goldenie.devs.clinics_catalog.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.Gallery;
import com.goldenie.devs.clinics_catalog.ui.activity.GalleryPhotoActivity;

import java.util.ArrayList;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {
 
        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View photoView = inflater.inflate(R.layout.item_photo, parent, false);
            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }
 
        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {
 
            Gallery gallery = galleries.get(position);
            ImageView imageView = holder.mPhotoImageView;

            Glide.with(mContext)
                    .load(gallery.getUrl())
                    .placeholder(R.drawable.ic_star)
                    .into(imageView);
        }
 
        @Override
        public int getItemCount() {
            return (galleries.size());
        }
 
        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
 
            public ImageView mPhotoImageView;
 
            public MyViewHolder(View itemView) {
 
                super(itemView);
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }
 
            @Override
            public void onClick(View view) {
 
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    Gallery gallery = galleries.get(position);
                    Intent intent = new Intent(mContext, GalleryPhotoActivity.class);
                    intent.putExtra(GalleryPhotoActivity.EXTRA_SPACE_PHOTO, gallery);
                    itemView.getContext().startActivity(intent);

                }
            }
        }
 
        private ArrayList<Gallery> galleries;
        private Context mContext;
 
        public ImageGalleryAdapter(Context context, ArrayList<Gallery> galleri) {
            mContext = context;
            galleries = galleri;
        }
    }
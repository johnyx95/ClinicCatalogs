package com.goldenie.devs.clinics_catalog.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.Gallery;

public class GalleryPhotoActivity extends AppCompatActivity {
 
    public static final String EXTRA_SPACE_PHOTO = "GalleryPhotoActivity.SPACE_PHOTO";
    private ImageView mImageView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
 
        mImageView = (ImageView) findViewById(R.id.image);
        Gallery gallery = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);
         
        Glide.with(this)
                .load(gallery.getUrl())
                .asBitmap()
                .error(R.drawable.ic_error_outline)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }
}
package com.verma.android.template.ui.menu.aboutus;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.verma.android.template.R;

import timber.log.Timber;


public class ImageSupport {
    public static final String USER_AGENT = "Android Application";
    public static final String APP_AGENT = "VERMA";
    private static final String TAG = "ImageSupport";

    private GlideUrl getGlideUrl(String pURL) {
        if (pURL == null) {

            return null;
        }

        LazyHeaders authHeaders =
                new LazyHeaders.Builder()
                        .setHeader("User-Agent", USER_AGENT)
                        .setHeader("APP-Agent", APP_AGENT)
                        .build();
        GlideUrl glideURL = new GlideUrl(pURL, authHeaders);
        Timber.d(glideURL.toStringUrl());

        return glideURL;
    }

    public void setImageWithGlide(Context context, final String pURL, final ImageView pImageView) {
        GlideUrl glideURL = getGlideUrl(pURL);
        Glide.with(context)
                .load(glideURL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Timber.e("Error loading image");
                        pImageView.setImageResource(R.drawable.ic_about_face_profile_black_18dp);
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .thumbnail(0.5f)
                .into(pImageView);
    }
}

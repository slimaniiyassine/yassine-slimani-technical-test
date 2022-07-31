package com.cnexia.technicaltest.view

import android.content.ContentResolver
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.cnexia.technicaltest.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:setImageToImageViewFromDrawableWithName")
fun setImageToImageViewFromDrawableWithName(imageView: ImageView, imageName: String) {
    val uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + imageView.context.getPackageName() + "/drawable/" + imageName);

    Picasso.get()
        .load(uri)
        .placeholder(R.drawable.ic_placeholder)
        .into(imageView)
}
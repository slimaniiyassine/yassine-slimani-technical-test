package com.cnexia.technicaltest.view

import android.R
import android.content.ContentResolver
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pros_cons_item.view.*


@BindingAdapter("android:setImageToImageViewFromDrawableWithName")
fun setImageToImageViewFromDrawableWithName(imageView: ImageView, imageName: String) {
    val uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + imageView.context.getPackageName() + "/drawable/" + imageName);

    Picasso.get()
        .load(uri)
        .placeholder(com.cnexia.technicaltest.R.drawable.ic_placeholder)
        .into(imageView)
}

@BindingAdapter("android:inflateData")
fun inflateData(layout: LinearLayout, data: List<String>) {
    val inflater = LayoutInflater.from(layout.context)
    for (entry in data) {
        val myItem = inflater.inflate(com.cnexia.technicaltest.R.layout.pros_cons_item, layout, false)
        myItem.prosConsTV.text = entry
        layout.addView(myItem)
    }
}

@BindingAdapter("android:setViewVisibility")
fun setViewVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

package com.cnexia.technicaltest.view.adapter

import android.content.ContentResolver
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cnexia.technicaltest.R
import com.cnexia.technicaltest.utils.SimpleDividerItemDecoration
import com.cnexia.technicaltest.view.data.RecyclerViewItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pros_cons_item.view.*


@BindingAdapter("android:setImageToImageViewFromDrawableWithName")
fun setImageToImageViewFromDrawableWithName(imageView: ImageView, imageName: String) {
    val uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + imageView.context.packageName + "/drawable/" + imageName)

    Picasso.get()
        .load(uri)
        .placeholder(R.drawable.ic_placeholder)
        .into(imageView)
}

@BindingAdapter("android:inflateData")
fun inflateData(layout: LinearLayout, data: List<String>) {
    layout.removeAllViews()
    val inflater = LayoutInflater.from(layout.context)
    for (entry in data) {
            val myItem = inflater.inflate(R.layout.pros_cons_item, layout, false)
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


@BindingAdapter("android:setCarsRecyclerViewData", "android:recyclerViewAdapter")
fun setCarsRecyclerViewData(view: RecyclerView, listCars: List<RecyclerViewItem>?, recyclerViewAdapter: CarsAdapter?) {
    if(listCars!= null && recyclerViewAdapter != null) {
        recyclerViewAdapter.setLocationList(listCars)
        view.addItemDecoration(SimpleDividerItemDecoration(view.context, R.drawable.line_divider))

        if (view.adapter == null) {
            view.adapter = recyclerViewAdapter
        }
    }
}

package com.jphernandez.intermediachallenge.helpers

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jphernandez.intermediachallenge.R

fun displayThumbnail(url: String?, extension: String?, imageView: ImageView) {
    val baseUrl = "$url/standard_medium.$extension"
    Glide.with(imageView.context)
        .load(baseUrl)
        .fitCenter()
        .placeholder(R.drawable.thumbnail_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

fun displayFullImage(url: String?, extension: String?, imageView: ImageView) {
    val baseUrl = "$url.$extension"
    Glide.with(imageView.context)
        .load(baseUrl)
        .centerCrop()
        .placeholder(R.drawable.thumbnail_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}
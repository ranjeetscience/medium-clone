package com.example.conduit.util

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.conduit.R

@BindingAdapter("imageFromUrl", "imageRound", "someBoolean")
fun bindImageFromUrl(view: ImageView, imageUrl: String?, rounded: Boolean, some: Boolean) {

    if (rounded) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .circleCrop()
            .into(view)
    } else {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("favoritedImg")
fun bindFavouriteImage(view: ImageView, isFavourite: Boolean) {
    if (isFavourite) {
        view.setImageResource(R.drawable.ic_baseline_favorite_24)
    } else {
        view.setImageResource(R.drawable.ic_outline_favorite_border_24)

    }
}
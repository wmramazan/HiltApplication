package com.accenture.hiltapplication.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("avatar")
fun ImageView.setAvatar(avatarUrl: String?) {
    Glide.with(context)
        .load(avatarUrl)
        .circleCrop()
        .into(this)
}
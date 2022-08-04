package com.bchmsl.homework14_customarchitecture.extensions

import android.widget.ImageView
import com.bchmsl.homework14_customarchitecture.R
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) {
    Glide.with(this.context).load(url).placeholder(R.drawable.gif_loading).into(this)

}

package com.accenture.hiltapplication.ui.main.page.second

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.accenture.hiltapplication.R

@BindingAdapter("itemCount")
fun TextView.setItemCount(count: Int?) {
    count?.let { value ->
        text = resources.getQuantityString(R.plurals.item_count, value, value)
    }
}
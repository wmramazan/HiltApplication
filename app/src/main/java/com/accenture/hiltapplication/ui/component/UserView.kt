package com.accenture.hiltapplication.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.databinding.ComponentUserViewBinding

class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    private val binding = ComponentUserViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setUser(user: UserEntity) {
        binding.user = user
        binding.executePendingBindings()
    }

}
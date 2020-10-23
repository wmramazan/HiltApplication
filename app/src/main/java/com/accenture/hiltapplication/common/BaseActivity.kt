package com.accenture.hiltapplication.common

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.accenture.hiltapplication.BR
import com.accenture.hiltapplication.R
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : BaseViewModel, Binding : ViewDataBinding> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected lateinit var viewModel: VM
    protected lateinit var binding: Binding

    protected val navController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initBinding()
    }

    @Suppress("unchecked_cast")
    private fun initViewModel() {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        viewModel = ViewModelProvider(this).get(type as Class<VM>)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.model, viewModel)
        binding.executePendingBindings()
    }

    fun <T> startActivity(
        cls: Class<T>,
        clearStack: Boolean = false,
        block: Intent.() -> Unit = {}
    ) {
        val intent = Intent(this, cls).apply(block)

        if (clearStack) {
            intent.addFlags(
                Intent.FLAG_ACTIVITY_NO_ANIMATION
                        or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_NEW_TASK
            )
            finishAffinity()
        }

        startActivity(intent)
    }

}
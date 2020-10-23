package com.accenture.hiltapplication.common

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.accenture.hiltapplication.BR
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected lateinit var viewModel: VM
    protected lateinit var binding: Binding

    protected val parent by lazy {
        activity as BaseActivity<*, *>?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.apply {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.model, viewModel)
            executePendingBindings()
        }

        return binding.root
    }

    @Suppress("unchecked_cast")
    private fun initViewModel() {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        viewModel =
            ViewModelProvider(activity as BaseActivity<*, *>).get(type as Class<VM>)
    }

    fun <T> startActivity(
        cls: Class<T>,
        clearStack: Boolean = false,
        block: Intent.() -> Unit = {}
    ) {
        parent?.startActivity(cls, clearStack, block)
    }

}
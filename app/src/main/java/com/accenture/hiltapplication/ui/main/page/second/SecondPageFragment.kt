package com.accenture.hiltapplication.ui.main.page.second

import com.accenture.hiltapplication.R
import com.accenture.hiltapplication.common.BaseFragment
import com.accenture.hiltapplication.databinding.FragmentMainSecondPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondPageFragment : BaseFragment<SecondPageViewModel, FragmentMainSecondPageBinding>() {
    override val layoutId = R.layout.fragment_main_second_page

}
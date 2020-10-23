package com.accenture.hiltapplication.ui.main.page.third

import com.accenture.hiltapplication.R
import com.accenture.hiltapplication.common.BaseFragment
import com.accenture.hiltapplication.databinding.FragmentMainThirdPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdPageFragment : BaseFragment<ThirdPageViewModel, FragmentMainThirdPageBinding>() {
    override val layoutId = R.layout.fragment_main_third_page

}
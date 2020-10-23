package com.accenture.hiltapplication.ui.main.page.first

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.accenture.hiltapplication.R
import com.accenture.hiltapplication.common.BaseFragment
import com.accenture.hiltapplication.databinding.FragmentMainFirstPageBinding
import com.accenture.hiltapplication.ui.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstPageFragment : BaseFragment<FirstPageViewModel, FragmentMainFirstPageBinding>(),
    SwipeRefreshLayout.OnRefreshListener {
    override val layoutId = R.layout.fragment_main_first_page

    private val userListAdapter = UserListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapter()
        observeUsers()
        setRefreshListener()
        loadUsers()
    }

    override fun onRefresh() {
        setRefreshing(true)
        viewModel.refresh()
    }

    private fun setListAdapter() {
        binding.listUsers.apply {
            adapter = userListAdapter
        }
    }

    private fun observeUsers() {
        viewModel.users.observe(viewLifecycleOwner, { list ->
            userListAdapter.setUserList(list)
            setRefreshing(false)
        })
    }

    private fun setRefreshing(refreshing: Boolean) {
        binding.layoutSwipeRefresh.isRefreshing = refreshing
    }

    private fun setRefreshListener() {
        binding.layoutSwipeRefresh.setOnRefreshListener(this)
    }

    private fun loadUsers() {
        viewModel.loadUsers(false)
    }

}
package com.accenture.hiltapplication.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.ui.component.UserView

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(val view: UserView) : RecyclerView.ViewHolder(view)

    private val _users = mutableListOf<UserEntity>()
    val users: List<UserEntity>
        get() = _users

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = UserView(parent.context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.view.setUser(user)
    }

    override fun getItemCount(): Int = users.size

    fun setUserList(list: List<UserEntity>) {
        _users.clear()
        _users.addAll(list)
        notifyDataSetChanged()
    }


}
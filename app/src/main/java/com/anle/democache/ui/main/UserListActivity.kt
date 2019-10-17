package com.anle.democache.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anle.democache.R
import com.anle.democache.ui.detail.UserDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class UserListActivity : AppCompatActivity(), LifecycleOwner {
    lateinit var viewModel: UserListViewModel
    lateinit var booksAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[UserListViewModel::class.java]
        booksAdapter = UsersAdapter(arrayListOf()) { id -> UserDetailsActivity.start(this, id) }
        rvBooks.adapter = booksAdapter
        viewModel.usersLiveData.observe(this, Observer { users ->
            Log.d("UserListActivity", "User data has changed")
            booksAdapter.books.run {
                clear()
                addAll(users)
                booksAdapter.notifyDataSetChanged()
            }
        })

        btnClearUsers.setOnClickListener {
            viewModel.clearUsers()
        }

        viewModel.refreshListUsers()
    }
}



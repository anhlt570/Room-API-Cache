package com.anle.democache.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anle.democache.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlin.random.Random

class UserDetailsActivity : AppCompatActivity(), LifecycleOwner {

    lateinit var viewModel: UserDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
        intent.extras?.let {
            viewModel.init(it.getInt(ARG_ID))
        }

        viewModel.userLiveData.observe(this, Observer {
            tvName.text = it.name
            tvMoney.text = "${it.money}"
            Glide.with(this)
                .load(it.avatar)
                .into(imgAvatar)
            tvCreatedDate.text = it.createdAt
        })

        btnGenerateMoney.setOnClickListener {
            val money = Random.nextInt(100, 1000)
            viewModel.updateMoney(money)
        }
    }

    companion object {
        const val ARG_ID = "arg_id"
        fun start(context: Context, id: Int) {
            context.startActivity(Intent(context, UserDetailsActivity::class.java).apply {
                putExtra(ARG_ID, id)
            })
        }
    }
}
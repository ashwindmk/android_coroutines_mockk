package com.ashwin.coroutinesmockk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openTestableActivity(view: View) {
        startActivity(Intent(this, TestableActivity::class.java))
    }

    fun openNetworkActivity(view: View) {
        startActivity(Intent(this, NetworkActivity::class.java))
    }
}
package com.ashwin.coroutinesmockk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_testable.*

class TestableActivity : AppCompatActivity() {
    private lateinit var viewModel: TestableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testable)

        viewModel = ViewModelProvider(this, TestableViewModelFactory()).get(TestableViewModel::class.java)

        viewModel.count.observe(this, Observer {
            count_textview.text = it.toString()
        })
    }

    fun addOne(view: View) {
        viewModel.addOne()
    }

    fun subtractOne(view: View) {
        viewModel.subtractOne()
    }
}
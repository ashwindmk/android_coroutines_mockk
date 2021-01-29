package com.ashwin.coroutinesmockk

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_network.*
import org.json.JSONObject
import java.lang.Exception

class NetworkActivity : AppCompatActivity() {
    private lateinit var viewModel: NetworkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        viewModel = ViewModelProvider(this, NetworkViewModelFactory()).get(NetworkViewModel::class.java)

        viewModel.response.observe(this, Observer {
            try {
                val json = JSONObject(it)
                Log.d("debug-log", "response: $json")
                response_textview.text = "Hello " + json.getString("username")
            } catch (e: Exception) {
                Log.e("debug-log", "error: $e")
            }
        })
    }

    fun getResponse(view: View) {
        viewModel.requestResponse()
    }
}
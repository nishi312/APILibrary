package com.example.apisdkdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apilibrary.demo.CallAPI
import android.content.Intent
import com.apilibrary.demo.App
import com.apilibrary.demo.ui.view.MainActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //CallAPI.fetchNationalities(this)
    }
}
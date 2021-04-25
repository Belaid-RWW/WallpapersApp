package com.example.wallpaperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class HomeActivity : AppCompatActivity() {

    lateinit var btnHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnHome = findViewById(R.id.btn_home)

        btnHome.setOnClickListener {
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show() //message de 3 sec
        }

    }
}
package com.example.wallpaperapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_photos.*


class PhotosActivity : AppCompatActivity() {

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val imageList = listOf<PhotoModel>(
                PhotoModel(R.drawable.photo1),
                PhotoModel(R.drawable.photo2),
                PhotoModel(R.drawable.photo3),
                PhotoModel(R.drawable.photo4),
                PhotoModel(R.drawable.photo5),
                PhotoModel(R.drawable.photo6),
                PhotoModel(R.drawable.photo7),
                PhotoModel(R.drawable.photo8)
        )

        val intent = intent
        val txt = intent.getStringExtra(EMAILL)
        val x = findViewById<TextView>(R.id.name_email)
        name_email.setText(txt)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_photo)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = PhotosAdapter(this, imageList){
            val intent = Intent(this, PhotoDetailsActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
            }

    }


}
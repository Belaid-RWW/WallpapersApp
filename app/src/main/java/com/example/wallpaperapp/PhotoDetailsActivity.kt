package com.example.wallpaperapp

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_photo_details.*


class PhotoDetailsActivity : AppCompatActivity() {

    lateinit var arrow: ImageView
    lateinit var btn_set: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        val image = intent.getParcelableExtra<PhotoModel>(PhotosActivity.INTENT_PARCELABLE)

        val imageSrc = findViewById<ImageView>(R.id.img_detail)

        imageSrc.setImageResource(image.imageA)

        btn_set = findViewById(R.id.btn_wallpaper)
        arrow = findViewById(R.id.img_arrow)
        arrow.setOnClickListener {
            val intent = Intent(this, PhotosActivity::class.java)
            startActivity(intent)
        }

        btn_wallpaper.setOnClickListener{
            var wallpaperManager:WallpaperManager = WallpaperManager.getInstance(this)
            wallpaperManager.setResource(image.imageA)
            Toast.makeText(applicationContext,"Wallpaper updated", Toast.LENGTH_SHORT).show()

        }
    }
}
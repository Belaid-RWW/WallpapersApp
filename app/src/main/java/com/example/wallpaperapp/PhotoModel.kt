package com.example.wallpaperapp
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class PhotoModel(
        val imageA: Int,
): Parcelable
package com.example.myalbum.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory


data class Photo(var id: Int, var name: String, var path: String) {
    fun getThumbnail(): Bitmap {
        val originalBitmap = BitmapFactory.decodeFile(path)

        // 生成缩略图

        // 生成缩略图
        val targetWidth = 200 // 缩略图的目标宽度

        val targetHeight = 200 // 缩略图的目标高度

        return Bitmap.createScaledBitmap(originalBitmap, targetWidth, targetHeight, false)
    }
}

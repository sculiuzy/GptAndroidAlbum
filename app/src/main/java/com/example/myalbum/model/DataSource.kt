package com.example.myalbum.model

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object DataSource {
    fun getAlbumData(context: Context): ArrayList<Photo> {
        val photoList = arrayListOf<Photo>()
        // 获取媒体库中所有图片的 URI
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

// 定义需要查询的字段
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.RELATIVE_PATH,
            MediaStore.Images.Media.DATE_ADDED
        )

// 查询媒体库中的图片
        val cursor = context.contentResolver.query(uri, projection, null, null, null)

// 遍历查询结果，获取每个图片的信息
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // 获取图片的 ID、名称、相对路径和添加日期等信息
                val id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                val relativePath =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.RELATIVE_PATH))
                val dateAdded =
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED))

                // 通过内容 URI 获取图片的文件路径
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toLong()
                )
                val path = getPathFromUri(contentUri, context)

                // 处理获取到的图片信息，比如将其存储到一个 List 中
                val photo = Photo(id, name, path)
                photoList.add(photo)
            } while (cursor.moveToNext())
        }
// 关闭 Cursor
        cursor?.close()
        return photoList
    }

    /**
     *         // 根据内容 URI 获取图片的文件路径

     */
    private fun getPathFromUri(contentUri: Uri, context: Context): String {
        var path: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(contentUri, projection, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            path = cursor.getString(columnIndex)
            cursor.close()
        }
        return path ?: ""
    }
}
package com.example.myalbum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbum.model.DataSource
import com.example.myalbum.model.Photo


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = GridLayoutManager(this, 3) // 设置为网格布局，每行显示3个图片

        val photos: List<Photo> = DataSource.getAlbumData(this) // 获取相册中的图片列表

        val adapter = PhotoAdapter(photos)
        recyclerView.adapter = adapter
    }
}
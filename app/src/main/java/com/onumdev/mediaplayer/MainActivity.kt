package com.onumdev.mediaplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onumdev.mediaplayer.datasources.DataRepository
import com.onumdev.mediaplayer.musicengine.MusicReader
import com.onumdev.mediaplayer.recyclerutils.TracksAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            initializeRecyclerView()
        }

        initializeRecyclerView()
    }

    fun initializeRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val recyclerAdapter = TracksAdapter()
        val layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
    }
}
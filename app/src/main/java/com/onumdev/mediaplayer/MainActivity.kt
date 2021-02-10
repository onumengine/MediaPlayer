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

    companion object {
        private const val LOG_TAG = "MAIN_ACTIVITY"
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            initializeRecyclerView()
        }

        initializeRecyclerView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    DataRepository.fetchSongsFromDeviceStorage(this)
                } else {
                    Snackbar.make(
                            findViewById(R.id.recycler_view),
                            "I'm sorry, I'm not permitted to read music",
                            Snackbar.LENGTH_LONG
                    )
                }
                return
            }
            else -> {
                Log.d(LOG_TAG, "Baba e no work")
            }
        }
    }

    fun initializeRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val recyclerAdapter = TracksAdapter()
        val layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun addDeviceTracksToList() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            DataRepository.fetchSongsFromDeviceStorage(this)
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
            Log.d(LOG_TAG, "You're meant to show it in a context UI")
            Snackbar.make(findViewById(R.id.fab), "You're meant to show it in a context UI", Snackbar.LENGTH_LONG).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }
    }
}
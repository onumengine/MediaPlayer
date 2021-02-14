package com.onumdev.mediaplayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.onumdev.mediaplayer.datasources.DataRepository

class LauncherActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "MAIN_ACTIVITY"
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        findViewById<Button>(R.id.refresh_button).setOnClickListener{
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        AsyncTask.execute(
                Runnable { addDeviceTracksToList() }
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    DataRepository.getSongsInDeviceStorage(this)
                } else {
                    Snackbar.make(
                            findViewById(R.id.refresh_button),
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

    @RequiresApi(Build.VERSION_CODES.M)
    fun addDeviceTracksToList() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            DataRepository.getSongsInDeviceStorage(this)
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
            Log.d(LOG_TAG, "You're meant to show it in a context UI")
            Snackbar.make(findViewById(R.id.refresh_button), "You're meant to show it in a context UI", Snackbar.LENGTH_LONG).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }
    }
}
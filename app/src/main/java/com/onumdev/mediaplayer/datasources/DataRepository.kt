package com.onumdev.mediaplayer.datasources

import android.content.Context
import com.onumdev.mediaplayer.models.Song
import com.onumdev.mediaplayer.musicengine.MusicReader

object DataRepository {

    lateinit var songList: List<Song>

    fun getSongsInDeviceStorage(context: Context) {
        songList = MusicReader.getMusicLibrary(context)
    }
}
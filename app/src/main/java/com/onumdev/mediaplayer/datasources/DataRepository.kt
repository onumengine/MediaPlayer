package com.onumdev.mediaplayer.datasources

import android.content.Context
import com.onumdev.mediaplayer.models.Song
import com.onumdev.mediaplayer.musicengine.MusicReader

object DataRepository {

    lateinit var songList: List<Song>

    val tracklist = mutableListOf(
        "1985",
        "92 Explorer",
        "Alien Girl",
        "I Left You This Note",
        "Hallelu",
        "Overdose",
        "Paloma",
        "Ko Por Ke",
        "Road Block",
        "Diamonds",
        "Thanksgiving",
    )
    val artistList = mutableListOf("J. Cole",
        "Post Malone",
        "Kendrick Lamar",
        "Pyro The Rapper",
        "Bella Shmurda",
        "Dunni ft. Oxlade",
        "Alpha P",
        "Rexxie ft. Mohbad",
        "Elveektor & CHx",
        "Sam Smith",
        "YBN Cordae",
    )

    fun fetchSongsFromDeviceStorage(context: Context) {
        //songList = MusicReader.fetchAllMusicOnDevice(context).values.toList()
    }

    fun getSongsInDeviceStorage(context: Context) {
        songList = MusicReader.getMusicLibrary(context)
    }
}
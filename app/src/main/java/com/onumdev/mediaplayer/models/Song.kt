package com.onumdev.mediaplayer.models

data class Song(
        val id: Long?,
        val title: String?,
        var artist: String?,
        var album: String?,
        var trackNumber: Int?,
        var genre: String?,
        val filepath: String?
)
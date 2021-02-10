package com.onumdev.mediaplayer.musicengine

import android.content.Context
import android.widget.Toast

object MusicReader {

    fun fetchAllMusicOnDevice(context: Context): MutableMap<Long, String> {
        val resolver = context.contentResolver
        val contentResolverUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = resolver.query(contentResolverUri, null, null, null, null)
        var mapOfSongs = mutableMapOf<Long, String>()

        when {
            cursor == null -> {
                Toast.makeText(context, "Music fetch failed", Toast.LENGTH_SHORT).show()
            }
            !cursor.moveToFirst() -> {
                Toast.makeText(context, "No music on your phone", Toast.LENGTH_SHORT)
            }
            else -> {
                val titleColumn: Int = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
                val idColumn: Int = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
                do {
                    val songTitle = cursor.getString(titleColumn)
                    val songId = cursor.getLong(idColumn)
                    mapOfSongs.put(songId, songTitle)
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return mapOfSongs
    }
}
package com.onumdev.mediaplayer.musicengine

import android.content.Context
import android.provider.MediaStore
import android.widget.Toast
import com.onumdev.mediaplayer.models.Song

object MusicReader {

    fun fetchAllMusicOnDevice(context: Context): MutableMap<Long, String> {
        val resolver = context.contentResolver
        val contentResolverUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
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
                val titleColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
                val idColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
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

    fun getMusicLibrary(context: Context): MutableList<Song> {
        val resolver = context.contentResolver
        val contentResolverUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = resolver.query(contentResolverUri, null, null, null, null)
        var musicLibrary = mutableListOf<Song>()

        when {
            cursor == null -> {
                Toast.makeText(context, "Music fetch failed", Toast.LENGTH_SHORT).show()
            }
            !cursor.moveToFirst() -> {
                Toast.makeText(context, "No music on your phone", Toast.LENGTH_SHORT)
            }
            else -> {
                val titleColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
                val idColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
                val artistColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ARTIST)
                val albumColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)
                val trackNumberColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.CD_TRACK_NUMBER)
                val genreColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.GENRE)
                val filepathColumn: Int = cursor.getColumnIndex(MediaStore.Audio.Media.RELATIVE_PATH)
                do {
                    var latestCreatedSong = Song(
                            id = cursor.getLong(idColumn),
                            title = cursor.getString(titleColumn),
                            artist = cursor.getString(artistColumn),
                            album = cursor.getString(albumColumn),
                            albumTrackNumber = cursor.getInt(trackNumberColumn),
                            genre = cursor.getString(genreColumn),
                            filepath = cursor.getString(filepathColumn),
                    )
                    musicLibrary.add(latestCreatedSong)
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return musicLibrary
    }
}
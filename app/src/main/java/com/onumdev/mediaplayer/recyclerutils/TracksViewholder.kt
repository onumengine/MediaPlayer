package com.onumdev.mediaplayer.recyclerutils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onumdev.mediaplayer.R

class TracksViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var albumArt: ImageView
    var songTitle: TextView
    var artistName: TextView

    init {
        albumArt = itemView.findViewById(R.id.album_art)
        songTitle = itemView.findViewById(R.id.track_title)
        artistName = itemView.findViewById(R.id.artist_name)
    }
}
package com.onumdev.mediaplayer.recyclerutils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onumdev.mediaplayer.R
import com.onumdev.mediaplayer.datasources.DataRepository

class TracksAdapter: RecyclerView.Adapter<TracksViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewholder {
        val viewholderLayout = LayoutInflater.from(parent.context).inflate(R.layout.track_view_holder, parent, false)
        return TracksViewholder(viewholderLayout)
    }

    override fun onBindViewHolder(viewHolder: TracksViewholder, position: Int) {
        viewHolder.apply {
            songTitle.text = DataRepository.songList[position].title
            artistName.text = DataRepository.songList[position].id.toString()
        }
    }

    override fun getItemCount(): Int {
        return DataRepository.songList.size
    }
}
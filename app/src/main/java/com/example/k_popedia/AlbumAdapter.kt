package com.example.k_popedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(private val listAlbum: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        // Gunakan layout yang baru saja kita rapikan
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = listAlbum[position]
        holder.imgAlbum.setImageResource(album.pictAlbum)
        holder.tvAlbumName.text = album.nameAlbum
        holder.tvAlbumYear.text = album.yearAlbum
    }

    override fun getItemCount(): Int = listAlbum.size

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Sesuaikan ID dengan item_member.xml yang kamu kirim tadi
        val imgAlbum: ImageView = itemView.findViewById(R.id.pictAlbum)
        val tvAlbumName: TextView = itemView.findViewById(R.id.namaAlbum)
        val tvAlbumYear: TextView = itemView.findViewById(R.id.tahunAlbum)
    }
}
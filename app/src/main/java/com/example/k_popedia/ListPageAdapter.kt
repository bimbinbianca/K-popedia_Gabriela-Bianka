package com.example.k_popedia

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListPageAdapter(private val listGroup: ArrayList<Group>) :
    RecyclerView.Adapter<ListPageAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_about_group, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val group = listGroup[position]

        holder.imgPhotoGroup.setImageResource(group.pict)
        holder.tvNameGroup.text = group.name
        holder.tvDescriptionGroup.text = group.description
        holder.tvAchievement.text = group.achievement

        val memberAdapter = MemberAdapter(group.listMembers)
        holder.rvMember.apply {
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            adapter = memberAdapter
            isNestedScrollingEnabled = false
        }

        // Setup Album Adapter
        val albumAdapter = AlbumAdapter(group.listAlbums)
        holder.rvAlbum.apply {
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            adapter = albumAdapter
            isNestedScrollingEnabled = false
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intentDetail = Intent(context, AboutPage::class.java)
            intentDetail.putExtra("KEY_GROUP", listGroup[holder.bindingAdapterPosition])
            context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listGroup.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhotoGroup: ImageView = itemView.findViewById(R.id.imageView)
        val tvNameGroup: TextView = itemView.findViewById(R.id.pageGroupName)
        val tvDescriptionGroup: TextView = itemView.findViewById(R.id.pageGroupDescription)
        val  tvAchievement: TextView = itemView.findViewById(R.id.pageGroupAchievement)
        val rvMember: RecyclerView = itemView.findViewById(R.id.rv_member)
        val rvAlbum: RecyclerView = itemView.findViewById(R.id.rv_album)
    }
}
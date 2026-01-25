package com.example.k_popedia

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListGroupAdapter(private val listGroup: ArrayList<Group>) : RecyclerView.Adapter<ListGroupAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_groups,
                parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val group = listGroup[position]
        holder.imgPhoto.setImageResource(group.pict)
        holder.tvName.text = group.name
        holder.tvDescription.text = group.description

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, AboutGroup::class.java)
            intentDetail.putExtra("KEY_GROUP", listGroup[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listGroup.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_group_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}
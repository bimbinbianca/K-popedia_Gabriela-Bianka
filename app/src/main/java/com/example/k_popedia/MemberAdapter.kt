package com.example.k_popedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val listMember: ArrayList<Member>) :
    RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val (name, photo, birthday) = listMember[position]
        holder.imgMember.setImageResource(photo)
        holder.tvMemberName.text = name
        holder.memberBirthday.text = birthday
    }

    override fun getItemCount(): Int = listMember.size

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Pastikan ID ini (pictMember & namaMember) sama dengan yang ada di item_member.xml
        val imgMember: ImageView = itemView.findViewById(R.id.pictMember)
        val tvMemberName: TextView = itemView.findViewById(R.id.namaMember)
        val memberBirthday: TextView = itemView.findViewById(R.id.birthdayMember)
    }
}
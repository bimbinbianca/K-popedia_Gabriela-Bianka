package com.example.k_popedia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGroup: RecyclerView
    private val list = ArrayList<Group>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvName: TextView = findViewById(R.id.tv_names)
        val tvEmail: TextView = findViewById(R.id.tv_email)

        val pref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        tvName.text = pref.getString("NAME", "Nama User")
        tvEmail.text = pref.getString("EMAIL", "Email User")

        findViewById<MaterialCardView>(R.id.card_profile).setOnClickListener {
            startActivity(Intent(this, AboutPage::class.java))
        }

        findViewById<Button>(R.id.about_page).setOnClickListener {
            startActivity(Intent(this, MyProfile::class.java))
        }

        rvGroup = findViewById(R.id.rv_groups)
        rvGroup.setHasFixedSize(true)


        list.addAll(getListGroups())
        showRecyclerList()
    }

    private fun getListGroups(): ArrayList<Group> {
        val dataGroup = resources.getStringArray(R.array.data_group)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataPict = resources.obtainTypedArray(R.array.data_photo)
        val dataAchievement = resources.getStringArray(R.array.data_achievement_detail)

        val listGroup = ArrayList<Group>()

        for (i in dataGroup.indices) {
            val group = Group(
                dataGroup[i],
                dataDesc[i],
                dataPict.getResourceId(i, -1),
                dataAchievement[i],
                arrayListOf(), arrayListOf()
            )
            listGroup.add(group)
        }
        dataPict.recycle()
        return listGroup
    }
    private fun showRecyclerList() {
        rvGroup.layoutManager = LinearLayoutManager(this)
        rvGroup.adapter = ListGroupAdapter(list)
    }
}
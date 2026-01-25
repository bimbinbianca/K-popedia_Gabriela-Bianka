package com.example.k_popedia

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class AboutGroup : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private val listGroup = ArrayList<Group>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.item_try)

        viewPager = findViewById(R.id.viewPager)

        listGroup.clear()
        listGroup.addAll(getListGroups())

        setupViewPager()
    }

    private fun getListGroups(): ArrayList<Group> {
        val dataName = resources.getStringArray(R.array.data_group)
        val dataDescription = resources.getStringArray(R.array.data_description_detail)
        val dataPhoto = resources.obtainTypedArray(R.array.data_layout)
        val dataAchievement = resources.getStringArray(R.array.data_achievement_detail)

        val listG = ArrayList<Group>()

        for (i in dataName.indices) {
            val listMemberPerGrup = ArrayList<Member>()
            val normalizedName = dataName[i].lowercase().replace(" ", "")

            val memberArrayId = resources.getIdentifier("${normalizedName}_member", "array", packageName)
            val photoArrayId = resources.getIdentifier("${normalizedName}_photo", "array", packageName)
            val birthdayArrayId = resources.getIdentifier("${normalizedName}_birthday", "array", packageName)

            if (memberArrayId != 0 && photoArrayId != 0) {
                val names = resources.getStringArray(memberArrayId)
                val photos = resources.obtainTypedArray(photoArrayId)
                val birthday = resources.getStringArray(birthdayArrayId)
                for (j in names.indices) {
                    listMemberPerGrup.add(Member(names[j], photos.getResourceId(j, -1), birthday[j]))
                }
                photos.recycle()
            }

            val listAlbumPerGroup = ArrayList<Album>()
            val albumArrayId = resources.getIdentifier("${normalizedName}_photo_album", "array", packageName)
            val namedAlbumArrayId = resources.getIdentifier("${normalizedName}_name_album", "array", packageName)
            val yearAlbumArrayId = resources.getIdentifier("${normalizedName}_year_album", "array", packageName)

            if (albumArrayId != 0 && namedAlbumArrayId != 0 && yearAlbumArrayId != 0) {
                val albumNames = resources.getStringArray(namedAlbumArrayId)
                val albumPict = resources.obtainTypedArray(albumArrayId)
                val albumYear = resources.getStringArray(yearAlbumArrayId)
                for (k in albumNames.indices) {
                    listAlbumPerGroup.add(Album(albumPict.getResourceId(k, -1), albumNames[k], albumYear[k]))
                }
                albumPict.recycle()
            }

            listG.add(Group(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataAchievement[i], listMemberPerGrup, listAlbumPerGroup))
        }
        dataPhoto.recycle()
        return listG
    }

    private fun setupViewPager() {
        val dataFromIntent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("KEY_GROUP", Group::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Group>("KEY_GROUP")
        }

        val adapter = ListPageAdapter(listGroup)
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        val position = listGroup.indexOfFirst {
            it.name.trim().equals(dataFromIntent?.name?.trim(), ignoreCase = true)
        }

        viewPager.adapter = adapter

        if (position != -1) {
            viewPager.setCurrentItem(position, false)
        }
    }
}
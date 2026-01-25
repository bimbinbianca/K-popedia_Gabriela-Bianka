package com.example.k_popedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AboutPage : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonEdit: Button
    private lateinit var nameProfile: TextView
    private lateinit var emailProfile: TextView
    private lateinit var birthdayProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_page)

        // Inisialisasi View
        nameProfile = findViewById(R.id.nameProfile)
        emailProfile = findViewById(R.id.emailProfile)
        birthdayProfile = findViewById(R.id.birthdayProfile)
        buttonEdit = findViewById(R.id.button_edit)

        buttonEdit.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        val pref = getSharedPreferences("USER_PREF", MODE_PRIVATE)

        nameProfile.text = pref.getString("NAME", "Nama User")
        emailProfile.text = pref.getString("EMAIL", "Email User")
        birthdayProfile.text = pref.getString("BIRTHDAY", "Ulang Tahun User")
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_edit){
            val moveIntent = Intent(this@AboutPage, EditPageUser::class.java)
            startActivity(moveIntent)
        }
    }
}
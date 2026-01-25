package com.example.k_popedia

import android.os.Bundle
import android.view.View
import android.app.DatePickerDialog
import java.util.Calendar
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditPageUser : AppCompatActivity(), View.OnClickListener {

    private lateinit var inputName: TextInputEditText
    private lateinit var inputEmail: TextInputEditText
    private lateinit var inputBirthday: TextInputEditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_page_user)

        inputName = findViewById(R.id.etName)
        inputEmail = findViewById(R.id.etEmail)
        inputBirthday = findViewById(R.id.etBirthday)
        buttonSave = findViewById(R.id.button)

        inputBirthday.setOnClickListener {
            val calendar = Calendar.getInstance()

            val datePicker = DatePickerDialog(
                this,
                { _, year, month, day ->
                    val date = "$day/${month + 1}/$year"
                    inputBirthday.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
        buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button) {
            val name = inputName.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val birthday = inputBirthday.text.toString().trim()

            val pref = getSharedPreferences("USER_PREF", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("NAME", name)
            editor.putString("EMAIL", email)
            editor.putString("BIRTHDAY", birthday)
            editor.apply()

            finish()
        }
    }
}
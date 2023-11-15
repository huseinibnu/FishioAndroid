package com.example.fishiofix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity2 : AppCompatActivity() {
    private lateinit var etUsername : EditText
    private lateinit var btnValidasi: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var username = ""

        etUsername = findViewById(R.id.editText)
        btnValidasi = findViewById(R.id.appCompatButton)

        btnValidasi.setOnClickListener {
            username = etUsername.text.toString().trim()

            if (username.isEmpty()) {
                etUsername.error = "masukkan nama kamu dulu!"
                return@setOnClickListener
            } else {
                val pindahIntent = Intent(this, MainActivity3::class.java)
                pindahIntent.putExtra("username", username) // Mengirim data username
                startActivity(pindahIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }

        }
    }

}
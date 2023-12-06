package com.example.fishiofix

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager


class MainActivity2 : AppCompatActivity() {
    private lateinit var etUsername : EditText
    private lateinit var btnValidasi: AppCompatButton

    // Fungsi untuk menutup keyboard
    private fun closeKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var username = ""

        etUsername = findViewById(R.id.editText)
        btnValidasi = findViewById(R.id.appCompatButton)

        // Atur inputType agar hanya satu baris
        etUsername.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES

        etUsername.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Panggil method untuk menutup keyboard
                closeKeyboard()

                return@setOnKeyListener true
            }
            false
        }


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
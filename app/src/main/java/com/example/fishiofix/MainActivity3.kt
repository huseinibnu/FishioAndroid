package com.example.fishiofix

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // Bagian username
        val username = intent.getStringExtra("username") // Menerima data username

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Selamat Datang, $username" // Mengganti teks berdasarkan username

        // Bagian Card View
        val cardView1 = findViewById<CardView>(R.id.cardView1)

        cardView1.setOnClickListener {
            // Intent untuk mengarahkan ke Activity4
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        // Bagian pop up
        val imageView1 = findViewById<ImageView>(R.id.gambar1)
        val imageView2 = findViewById<ImageView>(R.id.gambar2)

        imageView1.setOnClickListener {
            showCustomDialogBox1()
        }

        imageView2.setOnClickListener {
            showCustomDialogBox2()
        }

    }

    private fun showCustomDialogBox1() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.pop_up_ciri_ikan_segar)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Menampilkan dialog
        dialog.show()

        // Menutup dialog saat mengklik di luar pop-up dan dalam pop-up
        dialog.window?.decorView?.setOnTouchListener { _, event ->
            if (event.action == android.view.MotionEvent.ACTION_DOWN) {
                dialog.dismiss()
                true
            } else {
                false
            }
        }
    }

    private fun showCustomDialogBox2() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.pop_up_tentang_kami)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Menampilkan dialog
        dialog.show()

        // Menutup dialog saat mengklik di luar pop-up dan dalam pop-up
        dialog.window?.decorView?.setOnTouchListener { _, event ->
            if (event.action == android.view.MotionEvent.ACTION_DOWN) {
                dialog.dismiss()
                true
            } else {
                false
            }
        }

    }
}
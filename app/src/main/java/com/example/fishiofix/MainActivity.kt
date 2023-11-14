package com.example.fishiofix

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLanjutkanDisini: Button = findViewById(R.id.lanjutkan_disini)
        btnLanjutkanDisini.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v!=null){
            when(v.id){
                R.id.lanjutkan_disini -> {
                    val pindahIntent = Intent(this, MainActivity2::class.java)
                    startActivity(pindahIntent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }
    
}
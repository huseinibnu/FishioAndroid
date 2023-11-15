package com.example.fishiofix

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mata_ikan)

        // Mengatur teks kombinasi normal dan bold
        // Mendapatkan referensi ke TextView
        val textView = findViewById<TextView>(R.id.unggah)

        // Mengambil teks dari TextView
        val text = textView.text.toString()

        // Membuat SpannableString
        val spannableString = SpannableString(text)

        // Menerapkan gaya teks berbeda pada bagian yang berbeda dari SpannableString
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), // Menerapkan gaya bold
            text.indexOf("mata ikanmu!"), // Indeks awal teks yang akan diberi gaya
            text.indexOf("mata ikanmu!") + "mata ikanmu!".length, // Indeks akhir teks yang akan diberi gaya
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE // Jenis flag
        )

        // Menetapkan SpannableString ke TextView
        textView.text = spannableString

        // Mengatur bagian menu
        val menu = findViewById<ImageView>(R.id.menu)

        menu.setOnClickListener(){
            Log.d("MyApp", "Menu Clicked")
            showBottomDialog()
        }
    }

    public fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout)

        val minimize = dialog.findViewById<ImageView>(R.id.minimize)
        val berandaLayout = dialog.findViewById<LinearLayout>(R.id.layoutBeranda)
        val mataLayout = dialog.findViewById<LinearLayout>(R.id.layoutMata)
        val mataInsangLayout = dialog.findViewById<LinearLayout>(R.id.layoutMataInsang)
        val insangLayout = dialog.findViewById<LinearLayout>(R.id.layoutInsang)
        val ciriLayout = dialog.findViewById<LinearLayout>(R.id.layoutCiri)
        val tentangLayout = dialog.findViewById<LinearLayout>(R.id.layoutAbout)

        mataLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.second))

        minimize.setOnClickListener {
            dialog.dismiss()
        }

        berandaLayout.setOnClickListener {
            val pindahIntent = Intent(this, MainActivity3::class.java)
            startActivity(pindahIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        mataLayout.setOnClickListener {
            dialog.dismiss()
        }

        mataInsangLayout.setOnClickListener {
            val pindahIntent = Intent(this, MataInsangActivity::class.java)
            startActivity(pindahIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        insangLayout.setOnClickListener {
            val pindahIntent = Intent(this, InsangActivity::class.java)
            startActivity(pindahIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        ciriLayout.setOnClickListener {
            dialog.dismiss()
            showCustomDialogBox1()
        }

        tentangLayout.setOnClickListener {
            dialog.dismiss()
            showCustomDialogBox2()
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
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
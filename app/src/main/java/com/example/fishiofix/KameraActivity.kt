package com.example.fishiofix

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.animation.ArgbEvaluator
import android.view.animation.DecelerateInterpolator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class KameraActivity : AppCompatActivity() {

    private val darkStatusBar = true
    private lateinit var popup_window_background: RelativeLayout
    private lateinit var popup_window_view_with_border: RelativeLayout
    private lateinit var gallery: ImageView
    private lateinit var kamera: ImageView
    private lateinit var imageView: ImageView
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_kamera)

        // Initialize the views after setContentView
        popup_window_background = findViewById(R.id.popup_window_background)
        popup_window_view_with_border = findViewById(R.id.popup_window_view_with_border)

        // Set the Status bar appearance for different API levels
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // If you want a dark status bar, set darkStatusBar to true
                if (darkStatusBar) {
                    this.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                this.window.statusBarColor = Color.TRANSPARENT
                setWindowFlag(this, false)
            }
        }

        // Fade animation for the background of Popup Window
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        // Fade animation for the Popup Window
        popup_window_view_with_border.alpha = 0f
        popup_window_view_with_border.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // Mengatur akses untuk kamera dan gallery
        kamera = findViewById(R.id.kamera)
        gallery = findViewById(R.id.gallery)
        imageView = findViewById(R.id.imageView)

        kamera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 200)
        }

        gallery.setOnClickListener{
            var intent: Intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 100)
        }
    }

    // Fungsi untuk menyimpan gambar ke dalam galeri dan mendapatkan URI
    private fun saveImageToGallery(bitmap: Bitmap?): Uri? {
        bitmap?.let {
            // Simpan gambar ke dalam galeri
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val imageFile = File(imagesDir, "temp_image.jpg")

            try {
                FileOutputStream(imageFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }

                // Tambahkan gambar ke galeri
                MediaScannerConnection.scanFile(
                    this,
                    arrayOf(imageFile.toString()),
                    arrayOf("image/jpeg"),
                    null
                )

                // Dapatkan URI dari file yang disimpan
                return Uri.fromFile(imageFile)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            // Menangani hasil pemilihan gambar dari galeri
            var uri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            imageView.setImageBitmap(bitmap)
        } else if (requestCode == 200) {
//            bitmap = data?.extras?.get("data") as Bitmap
//            imageView.setImageBitmap(bitmap)
            // Menangani hasil dari kamera
            val imageBitmap = data?.extras?.get("data") as Bitmap?

            // Simpan gambar ke dalam galeri untuk mendapatkan URI
            val uri = saveImageToGallery(imageBitmap)

            // Gunakan URI untuk membaca Bitmap
            uri?.let {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                imageView.setImageBitmap(bitmap)
            }
        }

    }

    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }
}

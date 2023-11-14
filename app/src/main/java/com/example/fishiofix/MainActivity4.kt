package com.example.fishiofix

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity4 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                // Ganti dengan Intent ke activity lain
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            R.id.nav_eye -> {
                // Ganti dengan Intent ke activity lain
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            R.id.nav_gills -> {
                // Ganti dengan Intent ke activity lain
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            R.id.nav_ciri -> {
                // Ganti dengan Intent ke activity lain
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            R.id.nav_about -> {
                // Ganti dengan Intent ke activity lain
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}


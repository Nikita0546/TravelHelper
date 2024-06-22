package com.example.travelhelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun Klik(view: View) {
        val engIntent = Intent(this, Countries::class.java)
        startActivity(engIntent)
  }
    fun Kliknot(view: View) {
        val engIntent = Intent(this, notes::class.java)
        startActivity(engIntent)
    }
    fun klikmap(view: View) {
        val engIntent = Intent(this, map::class.java)
        startActivity(engIntent) }
}

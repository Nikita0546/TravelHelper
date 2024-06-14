package com.example.travelhelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Countries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
    }
    fun KlikRU(view: View) {
        val engIntent = Intent(this, CountriesRU::class.java)
        startActivity(engIntent)
    }
//    fun KlikJP(view: View) {
//        val engIntent = Intent(this, CountriesJP::class.java)
//        startActivity(engIntent)
//    }
}
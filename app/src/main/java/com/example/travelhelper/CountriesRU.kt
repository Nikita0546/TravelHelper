package com.example.travelhelper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CountriesRU : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_countries_ru)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val itemsList: RecyclerView = findViewById(R.id.itemslistRU)
        val items = arrayListOf<rooms>()

        items.add(rooms(1, "roomru1", "однокомнатной квартира", "Центр Москвы", "аааааааааааааааа", "50000 руб/1 ночь"))
        items.add(rooms(1, "roomru2", "однокомнатной квартира", "восток Москвы", "аааааааааааааааа", "25000 руб/1 ночь"))
        items.add(rooms(1, "roomru3", "однокомнатной квартира", "запад Москвы", "аааааааааааааааа", "35000 руб/1 ночь"))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = Adapter(items, this)
    }
}
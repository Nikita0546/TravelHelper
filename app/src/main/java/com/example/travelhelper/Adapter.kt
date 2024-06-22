package com.example.travelhelper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var items: List<rooms>, var context: Context): RecyclerView.Adapter<Adapter.MyViewHolder>(){

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val price: TextView = view.findViewById(R.id.price)
        val btn: Button = view.findViewById(R.id.butroomruost)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rooms, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageId = context.resources.getIdentifier(
            items[position].image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)
        holder.title.text = items[position].title
        holder.desc.text = items[position].desc
        holder.price.text = items[position].price
        holder.btn.setOnClickListener{
            val intent = Intent(context, roomru::class.java)

            intent.putExtra("itemTitle", items[position].title)
            intent.putExtra("itemText", items[position].text)
            intent.putExtra("itemPrice", items[position].price)

            context.startActivity(intent)
        }
    }
}
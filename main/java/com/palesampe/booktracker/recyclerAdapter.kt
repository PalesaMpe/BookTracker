package com.palesampe.booktracker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext

class recyclerAdapter(val context: Context, val titles: MutableList<String>,
                      val authors: MutableList<String>): RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {
    val icons = mutableListOf<Int>()
    var selected = mutableListOf<Int>()

    private var positionArray:ArrayList<Boolean> = ArrayList(titles.size)
    init { for (i in titles.indices) {
    positionArray.add(false)
}
    }
    var isChecked = false

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)


         return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: recyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemAuthor.text = authors[position]


    }


    private fun selection(holder: recyclerAdapter.ViewHolder, position: Int) {
        isChecked = true
        lateinit var sharedPreferences: SharedPreferences
    }




    override fun getItemCount(): Int {
        return titles.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //var itemImage: ImageView
        var itemTitle: TextView
        var itemAuthor: TextView
        //var itemCheck: CheckBox
        val sharedPref = itemView.getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE)

        init{
            itemTitle = itemView.findViewById(R.id.cardtitle)
            itemAuthor = itemView.findViewById(R.id.authorTitle)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                val intent = Intent(itemView.context, ViewBook::class.java)
                intent.putExtra("bookIndex", position)
                context.startActivity(intent)
   //             Toast.makeText(itemView.context,titles[position],Toast.LENGTH_SHORT).show()
            }

            }
        }
    }



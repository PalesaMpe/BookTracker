package com.palesampe.booktracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.palesampe.booktracker.*

class bookAdapter (userList: List<UserModel>, internal var context: Context)
    : RecyclerView.Adapter<bookAdapter.BookViewHolder>()
{
    internal var userList: List<UserModel> =  ArrayList()
    init{
        this.userList = userList
    }
    inner class BookViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.edtName)
        var password: TextView = view.findViewById(R.id.edtPassword)
        var loginBtn: Button = view.findViewById(R.id.signupBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_login, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val users = userList[position]
        holder.name.text = users.name
      //  holder.password.text = users.password

        holder.loginBtn.setOnClickListener{
         //   val i = Intent(context, HomePage::class.java)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}
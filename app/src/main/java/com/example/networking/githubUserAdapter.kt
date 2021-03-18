package com.example.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.android.synthetic.main.item_row.view.*

class githubUserAdapter(val users: ArrayList<githubuser>) :
    RecyclerView.Adapter<githubUserAdapter.githubViewHolder>() {


    class githubViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        fun Bind(githubuser: githubuser) {

            itemView.login.text = "Username : ${githubuser.login}"
            itemView.score.text = githubuser.score.toString()
            itemView.Tid.text = githubuser.html_url
            Picasso.get().load(githubuser.avatar_url).fit().into(itemView.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): githubViewHolder =
        githubViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )


    override fun onBindViewHolder(holder: githubViewHolder, position: Int) {
        holder.Bind(users[position])
    }

    override fun getItemCount() = users.size
}
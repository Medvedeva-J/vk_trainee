package com.example.listviewkotpractice

import Item
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.vk2.R
import com.squareup.picasso.Picasso

class ListAdapter(context: Context, dataArrayList: List<Item>) :
    ArrayAdapter<Item?>(context, R.layout.list_item, dataArrayList!!) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        var view = view
        val item = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val image = view!!.findViewById<ImageView>(R.id.thumbnail)
        val name = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val price = view.findViewById<TextView>(R.id.price)

        Picasso.get().load(item!!.thumbnail).into(image);
        name.text = item.title
        description.text = item.description
        price.text = "${item.price} $"

        return view
    }
}
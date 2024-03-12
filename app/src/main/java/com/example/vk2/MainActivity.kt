package com.example.vk2

import Item
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.listviewkotpractice.ListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.serialization.json.Json
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var currentPage = MutableLiveData(1)
    var limit = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val queue = Volley.newRequestQueue(this)
        val listView = findViewById<ListView>(R.id.listView)
        val pageTextView = findViewById<TextView>(R.id.current_page)

        currentPage.observe(this@MainActivity, Observer {
            val stringRequest = StringRequest(
                Request.Method.GET, "https://dummyjson.com/products?skip=${(it - 1) * limit}&limit=${limit}",
                { response ->
                    var array = Json.decodeFromString<List<Item>>(JSONObject(response).getString("products"))
                    var adapter = ListAdapter(this@MainActivity, array)

                    if (array.size > 0) {
                        pageTextView.text = it.toString()
                        listView.adapter = adapter
                    } else {
                        currentPage.value = currentPage.value!! - 1
                        Snackbar.make(
                            findViewById(R.id.cl),
                            "Это последняя страница!",
                            1000
                        ).show()
                    }
                },
                { Log.e("ERROR","That didn't work!") })

            queue.add(stringRequest)
        })
    }

    fun nextPage(view: View?) {
        currentPage.value = currentPage.value!! + 1
    }

    fun prevPage(view: View?) {
        if (currentPage.value!! > 1) {
            currentPage.value = currentPage.value!! - 1
        } else {
            Snackbar.make(
                findViewById(R.id.cl),
                "Это первая страница!",
                1000
            ).show()
        }
    }
}


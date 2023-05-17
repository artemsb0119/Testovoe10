package com.example.testovoe10

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class BalanceActivity : AppCompatActivity() {

    private lateinit var activity: Activity
    private lateinit var imageViewFon2: ImageView
    private lateinit var crypta: List<Crypta>

    private lateinit var rv_table: RecyclerView
    private lateinit var adapter: BalanceAdapter
    private lateinit var textViewNow: TextView

    private lateinit var sharedPreferences: SharedPreferences

    private var balance = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        textViewNow= findViewById(R.id.textViewNow)
        rv_table = findViewById(R.id.rv_table)
        rv_table.layoutManager = LinearLayoutManager(this)

        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/10/fon2.png")
            .into(imageViewFon2)

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        balance = sharedPreferences.getFloat("balance", 0f)
        textViewNow.setText(String.format("%.2f", balance)+"$")
        activity = this
        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/10/crypta.json").readText()
                val gson = Gson()
                crypta = gson.fromJson(data, Array<Crypta>::class.java).toList()

                activity.runOnUiThread {
                    adapter = BalanceAdapter(crypta, balance)
                    rv_table.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
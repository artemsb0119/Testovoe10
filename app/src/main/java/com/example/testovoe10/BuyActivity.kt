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
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BuyActivity : AppCompatActivity() {

    private lateinit var activity: Activity
    private lateinit var imageViewFon2: ImageView
    private lateinit var buy: List<Buy>

    private lateinit var rv_table: RecyclerView
    private lateinit var adapter: BuyAdapter
    private lateinit var textViewNow: TextView

    private lateinit var sharedPreferences: SharedPreferences

    private var balance = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

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
                val data = URL("http://135.181.248.237/10/buy.json").readText()
                val gson = Gson()
                buy = gson.fromJson(data, Array<Buy>::class.java).toList()
                for (i in 0..9) {
                    val value = sharedPreferences.getInt("key_$i", 0)
                    buy.get(i).kol = value
                }
                activity.runOnUiThread {
                    adapter = BuyAdapter(buy, balance)
                    rv_table.adapter = adapter
                    adapter.setOnItemClickListener(object : BuyAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            if (balance >= buy.get(position).cost) {
                                val currentDateTime = LocalDateTime.now()
                                val formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm")
                                val formattedDateTime = currentDateTime.format(formatter)
                                val result = formattedDateTime.toString()+"  "+buy.get(position).name+"   "+buy.get(position).cost+"$\n"
                                val lastText = sharedPreferences.getString("allText", "")
                                balance -= buy.get(position).cost // Округление значения до двух знаков после запятой
                                textViewNow.text = String.format("%.2f", balance)+"$"
                                val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
                                editor.putFloat("balance", balance)
                                editor.putInt("key_$position", buy.get(position).kol + 1)
                                editor.putString("allText", result+lastText)
                                editor.apply()
                                buy[position].kol = buy.get(position).kol + 1 // Обновление значения в списке buy
                                adapter.notifyDataSetChanged()
                            }
                        }
                    })
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
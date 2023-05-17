package com.example.testovoe10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class HistoryActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var textViewRes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        imageViewFon2 = findViewById(R.id.imageViewFon2)
        textViewRes = findViewById(R.id.textViewRes)

        Glide.with(this)
            .load("http://135.181.248.237/10/fon2.png")
            .into(imageViewFon2)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        textViewRes.text = sharedPreferences.getString("allText", "")
    }
}
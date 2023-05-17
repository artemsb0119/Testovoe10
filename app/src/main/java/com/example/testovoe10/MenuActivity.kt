package com.example.testovoe10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class MenuActivity : AppCompatActivity() {

    private lateinit var buttonBalance: AppCompatButton
    private lateinit var buttonBuy: AppCompatButton
    private lateinit var buttonHistory: AppCompatButton
    private lateinit var buttonSettings: AppCompatButton

    private lateinit var imageViewFon2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        buttonBalance = findViewById(R.id.buttonBalance)
        buttonBuy = findViewById(R.id.buttonBuy)
        buttonHistory = findViewById(R.id.buttonHistory)
        buttonSettings = findViewById(R.id.buttonSettings)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/10/fon2.png")
            .into(imageViewFon2)

        buttonBalance.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            startActivity(intent)
        }
        buttonBuy.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
        }
        buttonHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}
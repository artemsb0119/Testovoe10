package com.example.testovoe10

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class SettingsActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var buttonClear: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        buttonClear = findViewById(R.id.buttonClear)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/10/fon2.png")
            .into(imageViewFon2)

        buttonClear.setOnClickListener {
            val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
            editor.putBoolean("is_first_start", true)
            editor.putFloat("balance", 0f)
            for (i in 0..9) {
                editor.putInt("key_$i", 0)
            }
            editor.putString("allText", "")
            editor.apply()
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
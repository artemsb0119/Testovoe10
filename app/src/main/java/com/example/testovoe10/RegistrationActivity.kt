package com.example.testovoe10

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeat: EditText
    private lateinit var edittextBalanse: EditText
    private lateinit var imageViewFon2: ImageView

    private lateinit var buttonBegin: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var isGood = true

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeat = findViewById(R.id.editTextRepeat)
        edittextBalanse = findViewById(R.id.edittextBalanse)
        buttonBegin = findViewById(R.id.buttonBegin)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        Glide.with(this)
            .load("http://135.181.248.237/10/fon2.png")
            .into(imageViewFon2)

        buttonBegin.setOnClickListener {

            if (TextUtils.isEmpty(editTextName.text)) {
                editTextName.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextName.setBackgroundResource(R.drawable.rounded_edittext)
            }
            if (TextUtils.isEmpty(editTextEmail.text)) {
                editTextEmail.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextEmail.setBackgroundResource(R.drawable.rounded_edittext)
            }
            if (TextUtils.isEmpty(editTextPassword.text)) {
                editTextPassword.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextPassword.setBackgroundResource(R.drawable.rounded_edittext)
            }
            if (TextUtils.isEmpty(editTextRepeat.text)) {
                editTextRepeat.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextRepeat.setBackgroundResource(R.drawable.rounded_edittext)
            }
            if (TextUtils.isEmpty(edittextBalanse.text)) {
                edittextBalanse.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                edittextBalanse.setBackgroundResource(R.drawable.rounded_edittext)
            }
            if (!TextUtils.equals(editTextPassword.text,editTextRepeat.text)) {
                editTextPassword.setBackgroundResource(R.drawable.rounded_edittext_red)
                editTextRepeat.setBackgroundResource(R.drawable.rounded_edittext_red)
            } else {
                editTextPassword.setBackgroundResource(R.drawable.rounded_edittext)
                editTextRepeat.setBackgroundResource(R.drawable.rounded_edittext)
            }
            if (!TextUtils.isEmpty(editTextName.text) && !TextUtils.isEmpty(
                    editTextEmail.text) && !TextUtils.isEmpty(editTextPassword.text) && !TextUtils.isEmpty(editTextPassword.text)
                && !TextUtils.isEmpty(edittextBalanse.text)&& TextUtils.equals(editTextPassword.text,editTextRepeat.text)) {
                val balance = edittextBalanse.text.toString().toFloat()
                val editor = getSharedPreferences("my_preferences", Context.MODE_PRIVATE).edit()
                editor.putBoolean("is_first_start", false)
                editor.putFloat("balance", balance)
                editor.apply()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
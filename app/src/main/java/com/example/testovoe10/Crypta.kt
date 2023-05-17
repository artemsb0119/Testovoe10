package com.example.testovoe10

import com.google.gson.annotations.SerializedName

data class Crypta(
    @SerializedName("name") val name : String,
    @SerializedName("cost") val cost : Float
)
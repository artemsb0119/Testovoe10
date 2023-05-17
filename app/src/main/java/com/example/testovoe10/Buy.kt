package com.example.testovoe10

import com.google.gson.annotations.SerializedName

data class Buy(
    @SerializedName("name") val name : String,
    @SerializedName("cost") val cost : Float,
    @SerializedName("kol") var kol : Int
)

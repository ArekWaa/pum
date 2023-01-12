package com.example.restcountiresapp

import com.squareup.moshi.Json

data class RecyclerViewItems(
    @Json(name="name") val country: RecyclerViewItemsName,
    @Json(name="capital") val capital: List<String>?,
    @Json(name="flags") val flag: RecyclerViewItemsFlag)

data class RecyclerViewItemsName(
    @Json(name="common") val common: String
)

data class RecyclerViewItemsFlag(
    @Json(name="png") val flag: String
)
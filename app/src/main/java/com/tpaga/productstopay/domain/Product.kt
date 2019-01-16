package com.tpaga.productstopay.domain

import com.squareup.moshi.Json

data class Product(
    @field:Json(name = "id")
    var id: String,
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "value")
    var value: String

)

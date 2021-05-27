package com.chardon.faceval.client.web.entity

import java.util.*

data class User(
    var id: String,
    var display_name: String,
    var password: String,
    var gender: Boolean?,
    var status: String?,
    var dateAdded: Date,
)

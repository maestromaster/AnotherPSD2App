package com.i.apiclient.model.oauth

data class Key(
    val kty: String,
    val n: String,
    val e: String,
    val use: String,
    val alg: String,
    val x5t: String
)
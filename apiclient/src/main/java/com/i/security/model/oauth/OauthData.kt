package com.i.security.model.oauth

import com.google.gson.annotations.SerializedName

data class OauthData (
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_in")
    val expiresIn: Int,

    val scope: String,

    @SerializedName("token_type")
    val tokenType: String,

    val keys: List<Key>,

    @SerializedName("client_id")
    val clientId: String
    )
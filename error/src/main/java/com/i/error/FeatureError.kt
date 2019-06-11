package com.i.errormanager

enum class FeatureError(val errorMessage: String) {
    UNKNOWN_ERROR("An unknown error"),

    // Authentication
    USERNAME_IS_EMPTY("Username is empty"),
    PASSWORD_IS_EMPTY("Password is empty"),

    // Home
    AUTHENTICATION_ERROR("Authentication error"),

    // Network
    NO_NETWORK("No network available"),
    HTTP_ERROR("Http error"),
    EMPTY_BODY("Empty body"),
}

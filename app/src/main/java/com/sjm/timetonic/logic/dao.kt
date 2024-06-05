package com.sjm.timetonic.logic

data class AppKeyResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val appkey: String
)

data class OauthKeyResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val oauthkey : String
)

data class SessionKeyResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val sesskey : String
)

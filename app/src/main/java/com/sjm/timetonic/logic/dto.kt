package com.sjm.timetonic.logic

// Data Transfer Objects for the HTTP responses
data class AppKeyResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val appkey: String,
)

data class OauthKeyResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val oauthkey: String,
    val o_u: String,
)

data class SessionKeyResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val sesskey: String,
)

data class AllBooksResponse(
    val status: String,
    val errorCode: String,
    val errorMsg: String,
    val allBooks: AllBooks,
)

data class AllBooks(val books: List<Book>)

data class Book(val ownerPrefs: OwnerPrefs)

data class OwnerPrefs(
    val title: String,
    val oCoverImg: String,
)

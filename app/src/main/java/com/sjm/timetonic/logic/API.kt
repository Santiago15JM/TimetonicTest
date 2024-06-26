package com.sjm.timetonic.logic

import com.sjm.timetonic.config.RetrofitHelper

// App implementation of api functions
// Calls the functions provided by retrofit with the api definitions, then returns the body of the http response
object API {
    private val api = RetrofitHelper.getInstance()

    suspend fun createAppKey(): AppKeyResponse? {
        return api.createAppKey().body()
    }

    suspend fun createOauthkey(user: String, password: String, appKey: String): OauthKeyResponse? {
        return api.createOauthkey(user = user, password = password, appKey = appKey).body()
    }

    suspend fun createSesskey(oauthUser: String, oauthkey: String): SessionKeyResponse? {
        return api.createSesskey(oauthUser = oauthUser, oauthkey = oauthkey).body()
    }

    suspend fun getAllBooks(oauthUser: String, sessionKey: String): AllBooksResponse? {
        return api.getAllBooks(oauthUser = oauthUser, user = oauthUser, sessionKey = sessionKey).body()
    }
}

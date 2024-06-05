package com.sjm.timetonic.logic

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiDefinitions {
    @POST
    fun createAppKey(
        @Query("req") req: String = "createAppkey",
        @Query("appname") appname: String = "library-app",
    ): Response<AppKeyResponse>

    @POST
    fun createOauthkey(
        @Query("req") req: String = "createOauthkey",
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("appkey") appKey: String,
    ): Response<OauthKeyResponse>

    @POST
    fun createSesskey(
        @Query("req") req: String = "createSesskey",
        @Query("o_u") oauthUser: String,
        @Query("oauthkey") oauthkey: String
    ): Response<SessionKeyResponse>
}

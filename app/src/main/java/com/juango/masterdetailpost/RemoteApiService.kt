package com.juango.masterdetailpost

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("/posts")
    fun getPost(): Call<GetPostsResponse>

    @GET("/comments")
    fun getDetailComments(@Query("postId") postId: Int): Call<GetCommentsResponse>

}

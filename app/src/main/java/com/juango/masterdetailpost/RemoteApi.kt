package com.juango.masterdetailpost

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteApi(private val apiService: RemoteApiService) {

    fun getPosts(onPostsReceived: (Result<List<Post>>) -> Unit) {
        apiService.getPost().enqueue(object : Callback<GetPostsResponse> {
            override fun onFailure(call: Call<GetPostsResponse>, error: Throwable) {
                onPostsReceived(Failure(error))
            }

            override fun onResponse(
                call: Call<GetPostsResponse>,
                response: Response<GetPostsResponse>
            ) {
                val data = response.body()

                if (data == null || data.posts.isNullOrEmpty()) {
                    onPostsReceived(Failure(NullPointerException("No data available!")))
                } else {
                    onPostsReceived(Success(data.posts))
                }
            }
        })
    }

    fun getDetailComments(postId: Int, onCommentsReceived: (Result<List<Comment>>) -> Unit) {
        apiService.getDetailComments(postId).enqueue(object : Callback<GetCommentsResponse> {
            override fun onFailure(call: Call<GetCommentsResponse>, error: Throwable) {
                onCommentsReceived(Failure(error))
            }

            override fun onResponse(
                call: Call<GetCommentsResponse>,
                response: Response<GetCommentsResponse>
            ) {
                val data = response.body()

                if (data == null || data.comments.isNullOrEmpty()) {
                    onCommentsReceived(Failure(NullPointerException("No response body!")))
                } else {
                    onCommentsReceived(Success(data.comments))
                }
            }
        })
    }

}
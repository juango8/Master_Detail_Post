package com.juango.masterdetailpost

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteApi(private val apiService: RemoteApiService) {

    fun getPosts(onPostsReceived: (List<Post>, Throwable?) -> Unit) {
        apiService.getPost().enqueue(object : Callback<GetPostsResponse> {
            override fun onFailure(call: Call<GetPostsResponse>, error: Throwable) {
                onPostsReceived(emptyList(), error)
            }

            override fun onResponse(
                call: Call<GetPostsResponse>,
                response: Response<GetPostsResponse>
            ) {
                val data = response.body()

                if (data == null || data.posts.isNullOrEmpty()) {
                    onPostsReceived(emptyList(), NullPointerException("No response body!"))
                } else {
                    onPostsReceived(data.posts, null)
                }
            }
        })
    }

    fun getDetailComments(postId: Int, onCommentsReceived: (List<Comment>, Throwable?) -> Unit) {
        apiService.getDetailComments(postId).enqueue(object : Callback<GetCommentsResponse> {
            override fun onFailure(call: Call<GetCommentsResponse>, error: Throwable) {
                onCommentsReceived(emptyList(), error)
            }

            override fun onResponse(
                call: Call<GetCommentsResponse>,
                response: Response<GetCommentsResponse>
            ) {
                val data = response.body()

                if (data == null || data.comments.isNullOrEmpty()) {
                    onCommentsReceived(emptyList(), NullPointerException("No response body!"))
                } else {
                    onCommentsReceived(data.comments, null)
                }
            }
        })
    }

}
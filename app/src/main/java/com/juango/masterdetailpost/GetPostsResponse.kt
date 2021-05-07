package com.juango.masterdetailpost

import kotlinx.serialization.Serializable

@Serializable
data class GetPostsResponse(val posts: List<Post> = listOf())

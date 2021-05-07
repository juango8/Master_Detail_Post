package com.juango.masterdetailpost

import kotlinx.serialization.Serializable

@Serializable
data class GetCommentsResponse(val comments: List<Comment> = listOf())

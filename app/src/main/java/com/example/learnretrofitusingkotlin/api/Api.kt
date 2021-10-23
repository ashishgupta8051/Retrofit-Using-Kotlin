package com.example.learnretrofitusingkotlin.api

import com.example.learnretrofitusingkotlin.model.Post
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("posts/1")
    suspend fun getPost() : Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number:Int
    ) : Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId:Int,
        @Query("sort") sort:String,
        @Query("_order") order:String
    ):Response<List<Post>>

    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId:Int,
        @QueryMap option:HashMap<String,String>
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ):Response<Post>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("https://jsonplaceholder.typicode.com") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Response<Post>

}
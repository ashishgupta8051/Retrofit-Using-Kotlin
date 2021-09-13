package com.example.learnretrofitusingkotlin.repository

import com.example.learnretrofitusingkotlin.api.RetrofitInstance
import com.example.learnretrofitusingkotlin.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost() : Response<Post>{
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number:Int):Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPost(userId:Int,sort:String,order:String):Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId,sort,order)
    }

    suspend fun getCustomPost2(usrId:Int,option:HashMap<String,String>):Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost2(usrId,option)
    }

    suspend fun getPushPost(post:Post) : Response<Post>{
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun getPushPost2(userId: Int,id:Int,title:String,body:String) : Response<Post>{
        return RetrofitInstance.api.pushPost2(userId,id,title,body)
    }
}
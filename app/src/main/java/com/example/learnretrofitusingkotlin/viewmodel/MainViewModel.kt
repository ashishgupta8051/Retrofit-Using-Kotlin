package com.example.learnretrofitusingkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnretrofitusingkotlin.model.Post
import com.example.learnretrofitusingkotlin.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2 : MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPost : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomPost2 : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val pushResponse : MutableLiveData<Response<Post>> = MutableLiveData()
    val pushResponse2 : MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }

    fun getCustomPost(userId: Int,sort: String,order: String){
        viewModelScope.launch {
            val response = repository.getCustomPost(userId,sort, order)
            myCustomPost.value = response
        }
    }

    fun getCustomPost2(userId: Int,option:HashMap<String,String>){
        viewModelScope.launch {
            val response = repository.getCustomPost2(userId,option)
            myCustomPost2.value = response
        }
    }

    fun getPushPost(post:Post){
        viewModelScope.launch {
            val response = repository.getPushPost(post)
            pushResponse.value = response
        }
    }

    fun getPushPost2(userId: Int,id:Int,title:String,body:String){
        viewModelScope.launch {
            val response = repository.getPushPost2(userId,id,title,body)
            pushResponse2.value = response
        }
    }
}
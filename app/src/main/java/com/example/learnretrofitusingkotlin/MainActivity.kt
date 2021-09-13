package com.example.learnretrofitusingkotlin

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnretrofitusingkotlin.adapter.DataAdapter
import com.example.learnretrofitusingkotlin.model.Post
import com.example.learnretrofitusingkotlin.repository.Repository
import com.example.learnretrofitusingkotlin.viewmodel.MainViewModel
import com.example.learnretrofitusingkotlin.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel:MainViewModel
    private lateinit var editText:EditText
    private lateinit var button:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dataAdapter:DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this,viewModelFactory)
            .get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        dataAdapter = DataAdapter(this)
        recyclerView.adapter = dataAdapter

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)

        mainViewModel.getPost()
        mainViewModel.myResponse.observe(this, Observer {
            response ->
            if (response.isSuccessful){
                val buffer = StringBuffer()
                buffer.append("ID : "+response.body()?.id.toString()+"\n\n")
                buffer.append("USER ID : "+response.body()?.userId.toString()+"\n\n")
                buffer.append("TITLE : "+response.body()?.title.toString()+"\n\n")
                buffer.append("BODY : "+response.body()?.body.toString()+"\n\n")
                //Show alert dialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("ID : 1 Data")
                builder.setMessage(buffer.toString())
                builder.setPositiveButton("Cancel"){ dialog,which ->
                    builder.setTitle(null)
                    builder.setMessage(null)
                    dialog.dismiss()
                }
                builder.setCancelable(false).show()
            }else{
                Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
            }
        })

        button.setOnClickListener {
            if (editText.text.toString().isEmpty()){
                editText.requestFocus()
                editText.error = "Enter number"
            }else{
                mainViewModel.getPost2(Integer.parseInt(editText.text.toString()))
                mainViewModel.myResponse2.observe(this, Observer {
                        response ->
                    if (response.isSuccessful){
                        val buffer = StringBuffer()
                        buffer.append("ID : "+response.body()?.id.toString()+"\n\n")
                        buffer.append("USER ID : "+response.body()?.userId.toString()+"\n\n")
                        buffer.append("TITLE : "+response.body()?.title.toString()+"\n\n")
                        buffer.append("BODY : "+response.body()?.body.toString()+"\n\n")

                        //Show alert dialog
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Get Data By ID : ${response.body()?.id.toString()}")
                        builder.setMessage(buffer.toString())
                        builder.setPositiveButton("Cancel"){ dialog,which ->
                            builder.setTitle(null)
                            builder.setMessage(null)
                            dialog.dismiss()
                        }
                        builder.setCancelable(false).show()
                    }else{
                        Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
                    }
                })

                mainViewModel.getCustomPost(Integer.parseInt(editText.text.toString()),editText.text.toString(),"desc")
                mainViewModel.myCustomPost.observe(this, Observer {
                    response ->
                    if (response.isSuccessful){
                        response.body()?.let { it1 -> dataAdapter.getData(it1) }
                    }else{
                        Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }

        val post = Post(1,2,"Title","Body")
        mainViewModel.getPushPost(post)
        mainViewModel.pushResponse.observe(this, Observer {
                response ->
            if (response.isSuccessful){
                Log.e("REQUEST TYPE","POST (By using model class)")
                Log.e("Id",response.body()?.id.toString())
                Log.e("UserId",response.body()?.userId.toString())
                Log.e("title",response.body()?.title.toString())
                Log.e("body",response.body()?.body.toString())
                Log.e("Response Status",response.code().toString())
                Log.e(TAG,"-----------------------------------------------------------------")
            }else{
                Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.getPushPost2(2,21,"Ashish Gupta","Hii my name is ashish gupta")
        mainViewModel.pushResponse2.observe(this, Observer {
            response ->
            if (response.isSuccessful){
                Log.e("REQUEST TYPE","POST (By using values)")
                Log.e("Id",response.body()?.id.toString())
                Log.e("UserId",response.body()?.userId.toString())
                Log.e("title",response.body()?.title.toString())
                Log.e("body",response.body()?.body.toString())
                Log.e("Response Status",response.code().toString())
                Log.e(TAG,"-----------------------------------------------------------------")
            }else{
                Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
            }
        })


    /*  button.setOnClickListener {
            if (editText.text.toString().isEmpty()){
                editText.requestFocus()
                editText.error = "Enter number"
            }else{
                mainViewModel.getCustomPost(Integer.parseInt(editText.text.toString()), editText.text.toString(),"desc")
                mainViewModel.myCustomPost.observe(this, Observer {
                        response ->
                    if (response.isSuccessful){
                        textView.text = response.body().toString()
                        response.body()?.forEach {
                            response ->
                            Log.e("Id",response.id.toString())
                            Log.e("UserId",response.userId.toString())
                            Log.e("title",response.title)
                            Log.e("body",response.body)
                            Log.e("Log","----------------------------------------------")
                        }
                    }else{
                        Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }*/

    /*button.setOnClickListener {
            if (editText.text.toString().isEmpty()){
                editText.requestFocus()
                editText.error = "Enter number"
            }else{
                var option:HashMap<String,String> = HashMap()
                option["_sort"] = editText.text.toString()
                option["_order"] = "desc"
                mainViewModel.getCustomPost2(Integer.parseInt(editText.text.toString()),option)
                mainViewModel.myCustomPost2.observe(this, Observer {
                        response ->
                    if (response.isSuccessful){
                        textView.text = response.body().toString()
                        response.body()?.forEach {
                            Log.e("Id",it.id.toString())
                            Log.e("UserId",it.userId.toString())
                            Log.e("title",it.title)
                            Log.e("body",it.body)
                            Log.e("Log","----------------------------------------------")
                        }
                    }else{
                        Toast.makeText(this,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }*/


    }
}
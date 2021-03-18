package com.example.networking

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.R.layout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URI.create
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


lateinit var linearLayoutManager: LinearLayoutManager
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        button.setOnClickListener {
            val s = "https://api.github.com/search/users?q=${username.text}"
            updatedata(s)
        }
    }

    private fun updatedata(url: String) {

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(url)
            .build()

                GlobalScope.launch {
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }
//                val arraylist = parseJSON(result)

                val gson = GsonBuilder().create()
                val apiResult = gson.fromJson(response.body?.string(), ApiResult::class.java)
                val githubUserAdapter = withContext(Dispatchers.IO){githubUserAdapter(apiResult.items)}

                    launch(Dispatchers.Main) {
                    linearLayoutManager = LinearLayoutManager(baseContext)
                    rvUsers.layoutManager = linearLayoutManager
                    rvUsers.adapter = githubUserAdapter
                }}
            }

//    class Networkclass : AsyncTask<String, Void, String>() {
//        override fun doInBackground(vararg params: String?): String {
//
//            val stringurl = params[0];
//            try {
//                val url = URL(stringurl)
//                val htpurlconnection = url.openConnection()
//                val inputStream = htpurlconnection.getInputStream()
//                val scanner = Scanner(inputStream)
//                scanner.useDelimiter("\\A")
//                if (scanner.hasNext()) {
//
//                    val s = scanner.next()
//                    return s
//                }
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return "failed to load"
//        }
//
//        override fun onPostExecute(result: String) {
//            super.onPostExecute(result)
//
//            val arraylist = parseJSON(result)
//
//            val githubUserAdapter = githubUserAdapter(arraylist)
//            linearLayoutManager = LinearLayoutManager(baseContext)
//            rvUsers.layoutManager = linearLayoutManager
//            rvUsers.adapter = githubUserAdapter
//      }
//    }

    fun parseJSON(result: String): ArrayList<githubuser> {

        val githubusers = ArrayList<githubuser>()
        try {
            val root = JSONObject(result)
            val jsonArray = root.getJSONArray("items")

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val login = jsonObject.getString("login")
                val id = jsonObject.getInt("id")
                val html_url = jsonObject.getString("html_url")
                val score = jsonObject.getInt("score")
                val avatar = jsonObject.getString("avatar_url")

                val githubUser = githubuser(login, id, html_url, score, avatar)
                githubusers.add(githubUser)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return githubusers
    }
}
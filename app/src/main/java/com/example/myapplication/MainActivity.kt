package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.api.YandexAPI
import com.example.myapplication.model.Answer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://predictor.yandex.net"
    val KEY = "pdct.1.1.20230408T133402Z.d442f7df36e78e57.a5e55d2b9a20ceaea12e2fbac308115f21718fa6"
    val LANG = "en"
    val LIMIT = 5
    lateinit var editText: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.result)
        editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
               // TODO("Not yet implemented")
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               // TODO("Not yet implemented")
            }
            override fun afterTextChanged(s: Editable?) {
                doRequest()
            }
        })
    }
    fun doRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(YandexAPI::class.java)
        api.getComplete(KEY,editText.text.toString(),LANG, LIMIT).enqueue(
            object: Callback<Answer> {
                override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
                    if(response.isSuccessful) {
                        val result = response.body()?.text
                        if(result != null) {
                            textView.text =result.joinToString("\n")
                        }
                    }
                }
                override fun onFailure(call: Call<Answer>, t: Throwable) {
                    Log.d("RRR", t.message.toString())
                }

            }
        )
    }
}
package com.example.myapplication.api


import com.example.myapplication.model.Answer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexAPI {
    /*
    https://predictor.yandex.net/api/v1/predict.json/complete?
    key=pdct.1.1.20230408T133402Z.d442f7df36e78e57.a5e55d2b9a20ceaea12e2fbac308115f21718fa6&
    q=hello+wo&
    lang=en
    &limit5
     */
    @GET("/api/v1/predict.json/complete")
    fun getComplete(@Query("key") key222: String,
                    @Query("q") q: String,
                    @Query("lang") lang: String,
                    @Query("limit") limit: Int) : Call<Answer>
}
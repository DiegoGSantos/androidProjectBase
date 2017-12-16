package com.androidprojectbase.restclient

import com.androidprojectbase.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by diegosantos on 12/16/17.
 */
interface Service {

//    @retrofit2.http.Headers(" : ")
//    @retrofit2.http.POST("transaction/app")
//    fun restCall(@retrofit2.http.Body parameter: Parameter): io.reactivex.Observable<Response<ResponseBody>>

    companion object Factory {
        fun create(): Service {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(logging)

            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .client(httpClient.build())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(Service::class.java);
        }
    }
}
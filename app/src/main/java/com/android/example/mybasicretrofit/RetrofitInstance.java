package com.android.example.mybasicretrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
// Singleton retrofit class for network instance
    private RetrofitInstance(){

    }

    private static RetrofitInstance retrofitInstance = null;

    public static  RetrofitInstance getRetrofitInstance(){

        if (retrofitInstance == null){
            RetrofitInstance retrofitInstance = new RetrofitInstance();
            return  retrofitInstance;
        }
       return retrofitInstance;
    }

    public static TodoApi callApi(){

        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TodoApi.class);
    }

}

package com.android.example.mybasicretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface TodoApi {
// class to call api's
    @GET("/todos")
    Call<List<Todo>> getTodos() ;

}

package com.android.example.mybasicretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.example.mybasicretrofit.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private RecyclerView recyclerView;

    private TodoAdapter todoAdapter;

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main.xml binding to MainActivity
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        // recycleview components initialised
        setupRecycleView();

         TodoApi todoApi = RetrofitInstance.getRetrofitInstance().callApi();
         try {
             Call<List<Todo>> response =  todoApi.getTodos();

             response.enqueue(new Callback<List<Todo>>() {
                 @Override
                 public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                     if (response.body()!=null){
                        activityMainBinding.progressBar.setVisibility(View.GONE);
                         Log.d(TAG,"Response Body size "+response.body().size());
                         todoAdapter = new TodoAdapter(response.body(),MainActivity.this);
                         recyclerView.setAdapter(todoAdapter);
                     }
                 }

                 @Override
                 public void onFailure(Call<List<Todo>> call, Throwable t) {
                     activityMainBinding.progressBar.setVisibility(View.GONE);
                     Log.d(TAG,"Response not successful");
                 }
             });
         } catch (Exception ex){
             activityMainBinding.progressBar.setVisibility(View.INVISIBLE);
             Log.e(TAG, ""+ex.getMessage());
         }

    }

    private void setupRecycleView(){
        recyclerView = activityMainBinding.rvTodos;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        activityMainBinding.progressBar.setVisibility(View.VISIBLE);
    }
}
package com.example.quoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quoteapp.APIs.ApiClient;
import com.example.quoteapp.APIs.ApiInterface;
import com.example.quoteapp.Adapter.PoetryAdapter;
import com.example.quoteapp.Model.PoetryModel;
import com.example.quoteapp.Response.GetPoetryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;
    List<PoetryModel> poetryModel = new ArrayList<>();
    ApiInterface apiInterface;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        poetryModel.add(new PoetryModel("1", "hello world", "shade", "24-july-2002"));
        initialization();
        setSupportActionBar(toolbar);
        getdata();

    }

    private void initialization() {
        recyclerView = findViewById(R.id.poetry_recyclerView);
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
        toolbar = findViewById(R.id.toolbar_main);
    }

    private void setAdapter(List<PoetryModel> poetryModel) {
        poetryAdapter = new PoetryAdapter(this, poetryModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }

    private void getdata() {

        Toast.makeText(MainActivity.this, "IN getdata method", Toast.LENGTH_SHORT).show();
        apiInterface.getpoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                Toast.makeText(MainActivity.this, "IN onResponse method", Toast.LENGTH_SHORT).show();

                try {
                    Toast.makeText(MainActivity.this, "IN TRY BLOCK", Toast.LENGTH_SHORT).show();

                    if (response != null) {
                        Toast.makeText(MainActivity.this, "RESPONSE NOT NULL", Toast.LENGTH_SHORT).show();

                        if (response.body().getStatus().equals("1")) {
                            Toast.makeText(MainActivity.this, "Status is 1", Toast.LENGTH_SHORT).show();
                            setAdapter(response.body().getData());


                        } else {
                            Toast.makeText(MainActivity.this, "RESPONSE IS NULL", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error while fetching" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    Log.e("exp: ", e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_LONG).show();

                Log.e("failure: ", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_poetry_menu) {
            Intent intent = new Intent(this, AddPoetry.class);
            startActivity(intent);
            Toast.makeText(this, "addperoperty", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }


    }
}
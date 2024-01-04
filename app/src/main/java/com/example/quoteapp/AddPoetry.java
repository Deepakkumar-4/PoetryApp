package com.example.quoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quoteapp.APIs.ApiClient;
import com.example.quoteapp.APIs.ApiInterface;
import com.example.quoteapp.Response.DeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPoetry extends AppCompatActivity {

    Toolbar toolbar;
    EditText poetry, poet_name;

    Button submit;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);
        intialize();
        setupToolbar();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = poetry.getText().toString();
                String name = poet_name.getText().toString();

                if(data.equals("")){
                    poetry.setError("Feild is Empty");
                }
                else{
                    if (name.equals("")) {
                        poet_name.setError("Feild is Empty");
                        }
                    else{
                        callapi(data,name);
                        finish();
                    }
                }

            }
        });

    }

    private void intialize(){
        toolbar = findViewById(R.id.toolbar_update);
        poetry = findViewById(R.id.poetry_text);
        poet_name= findViewById(R.id.poetname_text);
        submit = findViewById(R.id.update_poetry_btn);

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void callapi(String poetryData,String poetname){

        apiInterface.addpoetry(poetryData,poetname).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try{

                }catch (Exception e){
                    Toast.makeText(AddPoetry.this, "Exception Failure", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(AddPoetry.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
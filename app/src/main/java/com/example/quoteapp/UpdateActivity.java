package com.example.quoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quoteapp.APIs.ApiClient;
import com.example.quoteapp.APIs.ApiInterface;
import com.example.quoteapp.Adapter.PoetryAdapter;
import com.example.quoteapp.Model.PoetryModel;
import com.example.quoteapp.Response.DeleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText poetryUpdate;

    Button submit;
    ApiInterface apiInterface;
    int id;
    String poetryData;

    List<PoetryModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        intialize();
        setupToolbarUpdate();
        
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetry_data = poetryUpdate.getText().toString();
                if (poetry_data.equals("")) {
                    Toast.makeText(UpdateActivity.this, "poetry is empty", Toast.LENGTH_SHORT).show();
                }else{
                    callapi(poetryData,id+"");
                    Toast.makeText(UpdateActivity.this, "poetry Not empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
    private void intialize(){
        toolbar = findViewById(R.id.toolbar_update);
        poetryUpdate = findViewById(R.id.poetry_text_update);
        submit = findViewById(R.id.submit_update);

        poetryData = getIntent().getStringExtra("poetry");
        id= getIntent().getIntExtra("id",0);
        poetryUpdate.setText(poetryData);

        Retrofit retrofit = ApiClient.getClient();
       apiInterface = retrofit.create(ApiInterface.class);

    }

    private void callapi(String pData,String pid){

        apiInterface.updatepoetry(pData, pid).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try{
                    if(response.body().getStatus().equals("1")){
                        Toast.makeText(UpdateActivity.this, "Status 1", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(UpdateActivity.this, "Updation Failure", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(UpdateActivity.this, "Exception Failure", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupToolbarUpdate(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}
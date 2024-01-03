package com.example.quoteapp.APIs;

import com.example.quoteapp.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ApiInterface {

    @GET("getpoetry.php")
    Call<GetPoetryResponse> getpoetry();

}

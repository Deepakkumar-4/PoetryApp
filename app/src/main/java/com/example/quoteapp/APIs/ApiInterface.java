package com.example.quoteapp.APIs;

import com.example.quoteapp.Response.DeleteResponse;
import com.example.quoteapp.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface  ApiInterface {

    @GET("getpoetry.php")
    Call<GetPoetryResponse> getpoetry();

    @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<DeleteResponse> deletpoetry(@Field("poetry_id") String poetry_id);

    @FormUrlEncoded
    @POST("addpoetry.php")
    Call<DeleteResponse> addpoetry(@Field("poetry") String data, @Field("poet_name") String name);
    @FormUrlEncoded
    @POST("updatepoetry.php")
    Call<DeleteResponse> updatepoetry(@Field("poetry") String poetry, @Field("id") String id);



}

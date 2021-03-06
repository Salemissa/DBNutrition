package com.example.myapp1;

import com.example.myapp1.model.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    public static final String ENDPOINT = "https://api.github.com";
    @GET("users/{username}/following")
    Call<List<GithubUser>> getFollowing(@Path("username") String username);
    @POST("users/new")
    Call<GithubUser> createUser(@Body GithubUser user);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
}

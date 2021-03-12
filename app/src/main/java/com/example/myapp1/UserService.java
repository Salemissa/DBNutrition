package com.example.myapp1;

import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.GithubUser;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.User;
import com.example.myapp1.model.UserForm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    public static final String ENDPOINT = "http://192.168.1.129:7777/";
//    @GET("users")
//    Call<List<User>> getUsers();
 // @POST("register")
  //Call<User> createUser(@Body UserForm user);
   //  @GET("allPriseenCharges")
    //Call<List<PriseenCharge>>getPriseenCharge();
    @POST("addPriseenCharge")
    Call<PriseenCharge> createPrise(@Body PriseenCharge priseenCharge);

   /* @POST("addDepistage")
    Call<Depistage> createDepistage(@Body Depistage depistage);*/


    @POST("addListDepistage")
    Call<Depistage> createDepistage(@Body List<Depistage> depistages);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}



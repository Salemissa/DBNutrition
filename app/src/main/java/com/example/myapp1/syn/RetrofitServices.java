package com.example.myapp1.syn;

import com.example.myapp1.model.ApprocheCommunataire;
import com.example.myapp1.model.Commune;
import com.example.myapp1.model.Depistage;
import com.example.myapp1.model.Localite;
import com.example.myapp1.model.Moughata;
import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.Structure;
import com.example.myapp1.model.SuviSousSurvillance;
import com.example.myapp1.model.USB;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitServices {
    public static final String ENDPOINT = "http://173.249.20.209:8080/PCIMA-0.0.1/";

      @GET("allMoughatas")
      Call<List<Moughata>> getMoughataas();
      @GET("allCommune")
       Call<List<Commune>> getCommunes();
       @GET("allstructure")
       Call<List<Structure>> getStructures();

         @GET(" alllocalites")
         Call<List<Localite>> getLocalites();
       @GET(" allusb")
       Call<List<USB>> getUSBs();



    //    @GET("users")
//    Call<List<User>> getUsers();
    // @POST("register")
    //Call<User> createUser(@Body UserForm user);
    //  @GET("allPriseenCharges")
    //Call<List<PriseenCharge>>getPriseenCharge();
  ///addApprocheList
    //addPriseenChargeList
    //addSuviSousList
    @POST("addPriseenCharge")
    Call<PriseenCharge> createPrise(@Body PriseenCharge priseenCharge);

    @POST("addDepistage")
    Call<Depistage> createDepistage(@Body Depistage depistage);

    @POST("addApproche")
    Call<ApprocheCommunataire> createApprocheCommunataire(@Body ApprocheCommunataire approcheCommunataire);

    @POST("addSuviSous")
    Call<SuviSousSurvillance> createDepistage(@Body SuviSousSurvillance sousSurvillance);


    @POST("addListDepistage")
    Call<Depistage> createDepistage(@Body List<Depistage> depistages);

    @POST("addApprocheList")
    Call<ApprocheCommunataire> createApprocheCommunataire(@Body List<ApprocheCommunataire> approcheCommunataires);

    @POST("addPriseenChargeList")
    Call<PriseenCharge> createPrise(@Body List<PriseenCharge> priseenCharges);

    @POST("addSuviSousList")
    Call<SuviSousSurvillance> createSuviSous(@Body List<SuviSousSurvillance> sousSurvillances);

    @FormUrlEncoded
    @POST("uploadDocument/{id}")
    Call <Depistage>uploadImage(
            //@Header("Cookie") String sessionIdAndRz,
            @Field("image") String file,
            @Path("id") Long id
            // @Part("isAny") RequestBody isAny
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

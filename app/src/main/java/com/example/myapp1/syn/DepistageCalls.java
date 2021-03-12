package com.example.myapp1.syn;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;


import androidx.annotation.Nullable;
import com.example.myapp1.model.Depistage;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepistageCalls {
    public interface CallbacksDepistage {
        void onResponse(@Nullable Depistage Depistage);
        void onFailure();
    }
    public interface CallbacksRapport {
        void onResponse(@Nullable Depistage Depistage);
        void onFailure();
    }
    public static void addDepistage(CallbacksDepistage callbacks, List<Depistage> depistage){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksDepistage> callbacksWeakReference = new WeakReference<CallbacksDepistage>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<Depistage> call =retrofitServices.createDepistage(depistage);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<Depistage>() {


            @Override
            public void onResponse(Call<Depistage> call, Response<Depistage> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<Depistage> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();

            }
        });

    }

    public static void addRapport(CallbacksRapport callbacks, byte[] file, Long id){

        String rapport = Base64.encodeToString(file, Base64.DEFAULT);


        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksRapport> callbacksWeakReference = new WeakReference<CallbacksRapport>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<Depistage> call =retrofitServices.uploadImage(rapport,id);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<Depistage>() {


            @Override
            public void onResponse(Call<Depistage> call, Response<Depistage> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<Depistage> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();

            }
        });

    }
}

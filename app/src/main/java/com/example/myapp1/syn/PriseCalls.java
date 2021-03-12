package com.example.myapp1.syn;

import android.util.Log;

import androidx.annotation.Nullable;


import com.example.myapp1.model.PriseenCharge;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriseCalls {
    public interface CallbacksPrises {
        void onResponse(List<PriseenCharge> priseenCharges);
        void onFailure();
    }

    public interface CallbacksPrise {
        void onResponse(@Nullable PriseenCharge priseenCharges);
        void onFailure();
    }
    public interface CallbacksPriseList {
        void onResponse(@Nullable PriseenCharge priseenCharge);
        void onFailure();
    }

    public static void allPrise(CallbacksPrises callbacks){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksPrises> callbacksWeakReference = new WeakReference<CallbacksPrises>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<List<PriseenCharge>> call =null;//userService.getPriseenCharge();
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<PriseenCharge>>() {


            @Override
            public void onResponse(Call<List<PriseenCharge>> call, Response<List<PriseenCharge>> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<PriseenCharge>> call, Throwable t) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();

            }
        });
    }


    public static void addPrise(CallbacksPrise callbacks, PriseenCharge priseenCharge){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksPrise> callbacksWeakReference = new WeakReference<CallbacksPrise>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        RetrofitServices retrofitServices = RetrofitServices.retrofit.create(RetrofitServices.class);

        // 2.3 - Create the call on Github API
        Call<PriseenCharge> call =retrofitServices.createPrise(priseenCharge);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<PriseenCharge>() {


            @Override
            public void onResponse(Call<PriseenCharge> call, Response<PriseenCharge> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                if(response.isSuccessful()){
                    Log.i("OK", response.message());
                }else{
                    Log.i("REPONSE", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<PriseenCharge> call, Throwable t) {
                Log.e("ERROR ", t.getMessage().toString()+"Probleme");
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();

            }
        });

    }

}

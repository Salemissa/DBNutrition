package com.example.myapp1;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapp1.model.PriseenCharge;
import com.example.myapp1.model.User;
import com.example.myapp1.model.UserForm;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersCalls {


    // 1 - Creating a callback
    public interface CallbacksUsers {
        void onResponse(List<User> users);
        void onFailure();
    }
    public interface CallbacksUser {
        void onResponse(User user);
        void onFailure();
    }

    public interface CallbacksPrises {
        void onResponse(List<PriseenCharge> priseenCharges);
        void onFailure();
    }

    public interface CallbacksPrise {
        void onResponse(@Nullable PriseenCharge priseenCharges);
        void onFailure();
    }


    // 2 - Public method to start fetching users following by Jake Wharton
    public static void fetchUserFollowing(CallbacksUsers callbacks){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksUsers> callbacksWeakReference = new WeakReference<CallbacksUsers>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        UserService userService = UserService.retrofit.create(UserService.class);

        // 2.3 - Create the call on Github API
        Call<List<User>> call =null;
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                Log.e("ERROR CAMPAGNES", t.getMessage());
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
                ;
            }
        });

    }


    public static void addUser(CallbacksUser callbacks,UserForm user){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksUser> callbacksWeakReference = new WeakReference<CallbacksUser>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        UserService userService = UserService.retrofit.create(UserService.class);

        // 2.3 - Create the call on Github API
        Call<User> call =null; //userService.createUser(user);
        // 2.4 - Start the call
        ((Call) call).enqueue(new Callback<User>() {


            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
                Log.i("Ok","ok");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERR",t.getMessage());
            }
        });

    }


    public static void allPrise(CallbacksPrises callbacks){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksPrises> callbacksWeakReference = new WeakReference<CallbacksPrises>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        UserService userService = UserService.retrofit.create(UserService.class);

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


    public static void addPrise(CallbacksPrise callbacks,PriseenCharge priseenCharge){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<CallbacksPrise> callbacksWeakReference = new WeakReference<CallbacksPrise>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        UserService userService = UserService.retrofit.create(UserService.class);

        // 2.3 - Create the call on Github API
        Call<PriseenCharge> call =userService.createPrise(priseenCharge);
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

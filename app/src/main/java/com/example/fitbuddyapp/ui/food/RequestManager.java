package com.example.fitbuddyapp.ui.food;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;

import com.example.fitbuddyapp.Main2Activity;
import com.example.fitbuddyapp.ui.food.Listeners.RecipeResponseListener;
import com.example.fitbuddyapp.ui.food.Models.RecipeApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRecipes(RecipeResponseListener listener,String query) {
        CallRecipes callRecipes = retrofit.create(CallRecipes.class);
        Call<RecipeApiResponse> call = callRecipes.callRecipe(query,"0","3","6be561f3ef744693bde32798363338a9","0","0","0");

        call.enqueue(new Callback<RecipeApiResponse>() {
            @Override
            public void onResponse(Call<RecipeApiResponse> call, Response<RecipeApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    private interface CallRecipes {
        @GET("recipes/complexSearch")
        Call<RecipeApiResponse> callRecipe(
                @Query("query")String query,
                @Query("minProtein")String minProtein,
                @Query("number") String number,
                @Query("apiKey") String apiKey,
                @Query("minFat")String minFat,
                @Query("minCalories")String minCalories,
                @Query("minCarbs")String minCarbs
        );

    }
}

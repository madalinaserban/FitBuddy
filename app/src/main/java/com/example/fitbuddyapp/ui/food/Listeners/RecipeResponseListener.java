package com.example.fitbuddyapp.ui.food.Listeners;

import com.example.fitbuddyapp.ui.food.Models.RecipeApiResponse;

public interface RecipeResponseListener {

    void didFetch(RecipeApiResponse response,String message);
    void didError(String message);

}

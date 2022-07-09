package com.example.fitbuddyapp.ui.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitbuddyapp.R;
import com.example.fitbuddyapp.ui.food.Models.Nutrient;
import com.example.fitbuddyapp.ui.food.Models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    Context context;
    List<Result> list;

    public RecipeAdapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        if(list.get(position).nutrition.nutrients.size()!=0)
        for (int i=0;i<list.get(position).nutrition.nutrients.size();i++) {

            if(list.get(position).nutrition.nutrients.get(i).name.equals("Calories"))
                holder.textView_kcal.setText
                        ("Kcal: "+(int)list.get(position).nutrition.nutrients.get(i).amount);

            else if(list.get(position).nutrition.nutrients.get(i).name.equals("Protein"))
                holder.textView_protein.setText
                        ("Protein: "+(int)list.get(position).nutrition.nutrients.get(i).amount);

            else if(list.get(position).nutrition.nutrients.get(i).name.equals("Fat"))
                holder.textView_fat.setText
                        ("Fat: "+(int)list.get(position).nutrition.nutrients.get(i).amount);

            else if(list.get(position).nutrition.nutrients.get(i).name.equals("Carbohydrates"))
                holder.textView_carbs.setText
                        ("Carbs: "+(int)list.get(position).nutrition.nutrients.get(i).amount);
        }

        Picasso.get().load(list.get(position).image).into(holder.imageView_food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecipeViewHolder extends RecyclerView.ViewHolder {
    CardView recipe_list_container;
    TextView textView_title, textView_protein, textView_carbs, textView_fat, textView_kcal;
    ImageView imageView_food;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);


        recipe_list_container = itemView.findViewById(R.id.recipe_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_protein = itemView.findViewById(R.id.textView_protein);
        textView_carbs = itemView.findViewById(R.id.textView_carbs);
        textView_fat = itemView.findViewById(R.id.textView_fat);
        textView_kcal = itemView.findViewById(R.id.textView_kcal);
        imageView_food = itemView.findViewById(R.id.imageView_food);
    }
}

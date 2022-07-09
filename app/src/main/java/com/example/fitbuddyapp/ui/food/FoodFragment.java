package com.example.fitbuddyapp.ui.food;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fitbuddyapp.R;
import com.example.fitbuddyapp.databinding.FragmentFoodBinding;
import com.example.fitbuddyapp.ui.food.Listeners.RecipeResponseListener;
import com.example.fitbuddyapp.ui.food.Models.RecipeApiResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public FragmentFoodBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ProgressDialog dialog;
    RequestManager manager;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;

    public FoodFragment() {
        // Required empty public constructor
    }


    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        dialog = new ProgressDialog(getActivity());
//        dialog.setTitle("Loading");
//        manager = new RequestManager(getActivity());
//        manager.getRecipes(recipeResponseListener,"chicken");
//        dialog.show();
    }

    private final RecipeResponseListener recipeResponseListener = new RecipeResponseListener() {
        @Override
        public void didFetch(RecipeApiResponse response, String message) {
            dialog.dismiss();
        recyclerView = getActivity().findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recipeAdapter= new RecipeAdapter(getActivity(),response.results);
        recyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    };

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_food, container, false);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_food, container, false);
        binding = FragmentFoodBinding.inflate(getLayoutInflater());

        ImageButton button= (ImageButton) view.findViewById(R.id.imgButton_Search);
        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                EditText input=view.findViewById(R.id.editText_toSearch);
            String searchField =input.getText().toString();
                dialog = new ProgressDialog(getActivity());
                dialog.setTitle("Loading");
                manager = new RequestManager(getActivity());
                manager.getRecipes(recipeResponseListener, searchField);
                dialog.show();
            }
        });






        // Inflate the layout for this fragment
        return view;
    }
}
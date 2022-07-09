package com.example.fitbuddyapp.ui.daily_macros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fitbuddyapp.databinding.FragmentDailyMacrosBinding

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DailyMacronutrientsFragment : Fragment() {

    private lateinit var binding: FragmentDailyMacrosBinding
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyMacrosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var user_data = db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener{ result->

            var age =Integer.parseInt(result.getString("age"))
            var height =Integer.parseInt(result.getString("height"))
            var weight =Integer.parseInt(result.getString("weight"))
           var gender=result.getString("gender")
            var activity=result.getString("how active")
            var auxHeight: Float
            auxHeight=height.toFloat()/100
            auxHeight*=auxHeight

            var kcal=weight*10+6.25*height-5*age
            var bmr=kcal
            if(gender.equals("male"))
                kcal=kcal+5
            else
                kcal=kcal-161

            if(activity.equals("3"))
                kcal=kcal*1.8
            else if(activity.equals("2"))
                kcal = kcal*1.5
            else kcal=kcal*1.2


            var protein=(kcal/4)*25/100
            var carbs=(kcal/4)*55/100
            var fats =(kcal/9)*20/100

            binding.textResultBMR.text=bmr.toInt().toString()
            binding.textResultDailyKcal.text= kcal.toInt().toString()
            binding.textResultDailyProtein.text=protein.toInt().toString()
            binding.textResultDailyCarbs.text=carbs.toInt().toString()
            binding.textResultDailyFat.text=fats.toInt().toString()

        }

    }

}
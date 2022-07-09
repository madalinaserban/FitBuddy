package com.example.fitbuddyapp.authentication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var userEmail: String
    private lateinit var userAge: String
    private lateinit var userWeight: String
    private lateinit var userHeight: String
    private lateinit var userID: String
    private lateinit var createAccountInputsArray: Array<EditText>
    private lateinit var binding: FragmentProfileBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var documentsnapshot: DocumentSnapshot
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        //binding.feedBtn.setOnClickListener{
         //   view?.findNavController()?.navigate(R.id.action_profileFragment_to_itemFragment)
        //}

        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            var user_data = db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener{ result->
                binding.textUsername.text =result.getString("name")

                var age =Integer.parseInt(result.getString("age"))
                var height =Integer.parseInt(result.getString("height"))
                var weight =Integer.parseInt(result.getString("weight"))

                var auxHeight: Float
                auxHeight=height.toFloat()/100
                auxHeight*=auxHeight

                var BMI=weight.toFloat()/auxHeight

                binding.textUserAge.text= age.toString()+" years"
                binding.textUserHeight.text=height.toString()+" cm"
                binding.textUserWeight.text=weight.toString()+" kg"
                binding.textUserBMI.text=BMI.toString().subSequence(0,4)
                binding.textResultBMI.text=BMIResult(BMI)

                view.findViewById<View?>(R.id.loadingPanel).isVisible=false;
            }

        }


    fun BMIResult(BMI:Float): String{
        if(BMI<16)
            return "Severe Thinness"
        if(BMI>=16&&BMI<17)
            return "Moderate Thinness"
        if(BMI>=17&&BMI<18.5)
            return "Mild Thinness"
        if(BMI>=18.5&&BMI<25)
            return "Normal"
        if(BMI>=25&&BMI<30)
            return "Overweight"
        if(BMI>=30&&BMI<35)
            return "Obese Class I"
        if(BMI>=35&&BMI<40)
            return "Obese Class II"
        else
            return "Obese Class III"}
    }




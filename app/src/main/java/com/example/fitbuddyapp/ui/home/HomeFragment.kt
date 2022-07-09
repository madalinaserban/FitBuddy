package com.example.fitbuddyapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentHomeBinding
import com.example.fitbuddyapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var userEmail: String
    private lateinit var userAge: String
    private lateinit var userWeight: String
    private lateinit var userHeight: String
    private lateinit var userID: String
    private lateinit var createAccountInputsArray: Array<EditText>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var documentsnapshot: DocumentSnapshot
    val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val bundle = arguments
        //if (bundle == null) {
           // Log.d("Confirmation", "Fragment didn't recive info")
          //  return
        //}
        //val args = Main2ActivityArgs.fromBundle(bundle)
       // if (args.idUser.isNullOrBlank()) {
         //   binding.textUsername.text = getString(R.string.noname)
        //}
       // else {
            var user_data = db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener { result ->
                binding.textUsername.text = result.getString("name")
                Log.d("Confirmation", "Here")
                var age = Integer.parseInt(result.getString("age"))
                var height = Integer.parseInt(result.getString("height"))
                var weight = Integer.parseInt(result.getString("weight"))
                if(result.getString(("gender"))=="male")
                {
                    binding.imageProfile.setImageDrawable(getResources().getDrawable(R.drawable.man))

                }
                if(result.getString(("gender"))=="female")
                {
                    binding.imageProfile.setImageDrawable(getResources().getDrawable(R.drawable.woman))

                }
                else if(result.getString(("gender"))=="other")
                {
                    binding.imageProfile.setImageDrawable(getResources().getDrawable(R.drawable.user))
                }


                var auxHeight: Float
                auxHeight = height.toFloat() / 100
                auxHeight *= auxHeight

                var BMI = weight.toFloat() / auxHeight

                binding.textUserAge.text = age.toString() + " years"
                binding.textUserHeight.text = height.toString() + " cm"
                binding.textUserWeight.text = weight.toString() + " kg"
                binding.textUserBMI.text = BMI.toString().subSequence(0, 4)
                binding.textResultBMI.text = BMIResult(BMI)

                view?.findViewById<View?>(R.id.loadingPanel)?.isVisible = false;
            //}

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

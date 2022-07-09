package com.example.fitbuddyapp.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentSelectYourGenderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SelectYourGenderFragment : Fragment() {
    private lateinit var binding:FragmentSelectYourGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSelectYourGenderBinding.inflate(layoutInflater)
        binding.imgMale.setOnClickListener{
            val directions=SelectYourGenderFragmentDirections.actionSelectYourGenderFragmentToHowActiveAreYou()
            findNavController().navigate(directions)
            val db= Firebase.firestore
           db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).update("gender","male")
        }

        binding.imgFemale.setOnClickListener{
            val directions=SelectYourGenderFragmentDirections.actionSelectYourGenderFragmentToHowActiveAreYou()
            findNavController().navigate(directions)
            val db= Firebase.firestore
           db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).update("gender","female")
        }
  binding.tvSkip.setOnClickListener(){
      val directions=SelectYourGenderFragmentDirections.actionSelectYourGenderFragmentToHowActiveAreYou()
      findNavController().navigate(directions)
  }
        return binding.root
    }
}
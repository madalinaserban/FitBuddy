package com.example.fitbuddyapp.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentHowActiveAreYouBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HowActiveAreYou : Fragment() {
    private lateinit var binding:FragmentHowActiveAreYouBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHowActiveAreYouBinding.inflate(layoutInflater)
        binding.button.setOnClickListener{
            val directions=HowActiveAreYouDirections.actionHowActiveAreYouToMain2Activity()
            findNavController().navigate(directions)
            val db= Firebase.firestore
            db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).update("how active","1")
        }

        binding.button2.setOnClickListener{
            val directions=HowActiveAreYouDirections.actionHowActiveAreYouToMain2Activity()
            findNavController().navigate(directions)
            val db= Firebase.firestore
            db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).update("how active","2")
        }
        binding.button3.setOnClickListener{
            val directions=HowActiveAreYouDirections.actionHowActiveAreYouToMain2Activity()
            findNavController().navigate(directions)
            val db= Firebase.firestore
            db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).update("how active","3")
        }
        binding.tvSkip.setOnClickListener(){
            val directions=HowActiveAreYouDirections.actionHowActiveAreYouToMain2Activity()
            findNavController().navigate(directions)
        }

        return binding.root
    }
}
package com.example.fitbuddyapp.authentication

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.fitbuddyapp.R

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_splash,container,false)
        Handler(Looper.myLooper()!!).postDelayed({
if(onBoardingFinished())
{
    findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
}
            else{
    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }

        },3500)
        return view
    }

        private fun onBoardingFinished():Boolean{
            val sharedPref=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
           return sharedPref.getBoolean("Finished",false)

    }
}
package onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentSecondScreenBinding
import com.example.fitbuddyapp.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {
    private lateinit var binding:FragmentThirdScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentThirdScreenBinding.inflate(layoutInflater)
        val viewPager=activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.finish.setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment_to_signInFragment)
            onBoardingFinished()
        }
        return binding.root
    }
    private fun onBoardingFinished()
    {val sharedPref=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor=sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()

    }

}
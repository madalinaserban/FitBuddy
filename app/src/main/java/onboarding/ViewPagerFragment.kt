package onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentViewPagerBinding
import onboarding.screens.FirstScreen
import onboarding.screens.SecondScreen
import onboarding.screens.ThirdScreen

class ViewPagerFragment : Fragment() {
    private lateinit var binding:FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentViewPagerBinding.inflate(layoutInflater)

        val fragmentList= arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter=ViewPagerAdaptor(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter=adapter
        return binding.root
    }


    }

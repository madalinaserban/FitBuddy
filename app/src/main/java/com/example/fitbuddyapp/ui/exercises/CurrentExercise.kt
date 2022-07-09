package com.example.fitbuddyapp.ui.exercises

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.CurrentExerciseFragmentBinding
import com.example.fitbuddyapp.ui.exercises.ExerciseObject.getSignSize

class CurrentExercise:Fragment(R.layout.current_exercise_fragment) {

    private lateinit var binding:CurrentExerciseFragmentBinding
    private val args: CurrentExerciseArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= CurrentExerciseFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title="${ExerciseObject.getTitle(args.positionArg)}"

        if(args.positionArg==0){
            binding.previousExercise.isInvisible=true
        }
        else if(args.positionArg==getSignSize()){
            binding.forwardExercise.isInvisible=true
        }

        binding.currentExerciseImage.setImageResource(ExerciseObject.getImage(args.positionArg))
        binding.currentSignName.text = ExerciseObject.getTitle(args.positionArg)
        binding.currentDifficultyText.text= ExerciseObject.getDifficulty(args.positionArg)
        binding.currentnumberText.text= ExerciseObject.getNumber(args.positionArg).toString()

        binding.previousExercise.setOnClickListener{
            val action=CurrentExerciseDirections.actionCurrentFragmentSelf(positionArg = args.positionArg-1)
            view?.let { Navigation.findNavController(it).navigate(action) }
        }

        binding.forwardExercise.setOnClickListener{
            val action=CurrentExerciseDirections.actionCurrentFragmentSelf(positionArg = args.positionArg+1)
            view?.let { Navigation.findNavController(it).navigate(action) }
        }
    fun onStart() {
        super.onStart()
    }

    fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        }

    fun getShareIntent():Intent {
        var arg= arguments?.let { CurrentExerciseArgs.fromBundle(it) }
        val shareIntent= Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,
                "${ExerciseObject.getTitle(args.positionArg)} - ${ExerciseObject.getDifficulty(args.positionArg)}")
        return shareIntent}

    }

}
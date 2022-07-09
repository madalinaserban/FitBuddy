package com.example.fitbuddyapp.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fitbuddyapp.R
import com.example.fitbuddyapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var userConfirmPassword: String
    private lateinit var userAge: String
    private lateinit var userWeight: String
    private lateinit var userHeight: String
    private lateinit var userName: String
    private lateinit var userID: String
    private lateinit var createAccountInputsArray: Array<EditText>
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?
    ): View? {
        // view?.findViewById<View?>(R.id.bottom_view)?.visibility=Gon;
        binding= FragmentSignUpBinding.inflate(layoutInflater)

       // activity.findViewById<BottomNavigationView>(R.id.bottom_view).visibility=View.GONE;
        createAccountInputsArray= arrayOf(
            binding.etEmail,
            binding.etPassword,
            binding.etConfirmPassword,
        binding.etAge,
        binding.etHeight,
        binding.etWeight)

        binding.btnCreateAccount.setOnClickListener {
            signUp()
        }
        binding.btnSignIn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
//Asta ca sa se logheze automat
       //   val user: FirebaseUser? = firebaseAuth.currentUser
         //user?.let {
           // view?.findNavController()?.navigate(R.id.action_signUpFragment_to_profileFragment)
            // Toast.makeText(activity, "Welcome Back", Toast.LENGTH_SHORT).show()
         //}

    }


    //campurile sa fie completate
    private fun notEmpty(): Boolean{
        val email=binding.etEmail.text
        val password=binding.etPassword.text
        val confirmPassword=binding.etConfirmPassword.text
        val height=binding.etHeight.text
        val weight=binding.etWeight.text
        val age=binding.etAge.text

        return (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()&&height.isNotEmpty()&&weight.isNotEmpty()&&age.isNotEmpty())
    }

    private  fun identicalPasswords():Boolean{

        if(binding.etPassword.text.toString()==binding.etConfirmPassword.text.toString() && notEmpty()){
            return true
        }
        else if(!notEmpty()){
            createAccountInputsArray.forEach { input ->
                if(input.text.toString().trim().isEmpty()){
                    input.error = "${input.hint} is required"
                }
            }
        }
        else
        {       Toast.makeText(activity, "Passwords are not matching", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private fun signUp() {
        if(identicalPasswords()){
            userEmail=binding.etEmail.text.toString()
            userPassword=binding.etPassword.text.toString()
            userConfirmPassword=binding.etConfirmPassword.text.toString()
            userAge=binding.etAge.text.toString()
            userHeight=binding.etHeight.text.toString()
            userWeight=binding.etWeight.text.toString()
            userName=binding.etName.text.toString()

            //creeare user

            val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            var document:DocumentReference
            var userMap: HashMap<String,Object>
            firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        Toast.makeText(activity, "Created Account Successfully!", Toast.LENGTH_SHORT).show()
                        userID= firebaseAuth.currentUser!!.uid
                        val user = hashMapOf(
                            "password" to userPassword,
                            "name" to userName,
                            "email" to userEmail,
                            "age" to userAge,
                            "height" to userHeight,
                            "weight" to userWeight,
                            "gender" to "other",
                          "how active" to "empty"
                        )
                        val db= Firebase.firestore
                        db.collection("users").document(userID).set(user)


                        view?.findNavController()?.navigate(R.id.action_signUpFragment_to_selectYourGenderFragment)

                    } else{
                        Toast.makeText(activity, "Failed to create", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}

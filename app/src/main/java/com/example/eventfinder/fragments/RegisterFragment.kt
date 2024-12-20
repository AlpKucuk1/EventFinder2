package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eventfinder.R
import com.example.eventfinder.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.registerButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_registerFragment_to_eventListFragment)
                    } else {
                        Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}

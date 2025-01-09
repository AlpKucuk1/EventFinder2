package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eventfinder.R
import com.example.eventfinder.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.registerButton.setOnClickListener {
            // Start Animation
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_click)
            binding.registerButton.startAnimation(animation)

            // Kayıt işlemleri
            val firstName = binding.firstNameInput.text.toString()
            val lastName = binding.lastNameInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (firstName.isNotBlank() && lastName.isNotBlank() && username.isNotBlank() &&
                email.isNotBlank() && password.isNotBlank()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Kullanıcı bilgilerini Firestore'a kaydet
                            val userId = auth.currentUser?.uid
                            val userMap = hashMapOf(
                                "firstName" to firstName,
                                "lastName" to lastName,
                                "username" to username,
                                "email" to email
                            )

                            val db = FirebaseFirestore.getInstance()
                            db.collection("users").document(userId!!)
                                .set(userMap)
                                .addOnSuccessListener {
                                    findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(context, "Failed to save user info: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "All fields must not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

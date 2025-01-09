package com.example.eventfinder.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eventfinder.R
import com.example.eventfinder.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val PREFS_NAME = "user_prefs"
    private val REMEMBER_ME_KEY = "remember_me"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        // SharedPreferences başlat
        val sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Remember Me seçeneği daha önce işaretlenmişse, kullanıcıyı ana ekrana yönlendir
        if (sharedPreferences.getBoolean(REMEMBER_ME_KEY, false)) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        // Login butonu için tıklama animasyonu ve işlem
        binding.loginButton.setOnClickListener {
            // Animasyonu başlat
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_click)

            // Animasyon tamamlandıktan sonra geri dönüş sağla
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation?) {
                    // Animasyon başladığında yapılacak işlemler
                }

                override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                    // Animasyon tamamlandı, buton eski haline dönecek
                    binding.loginButton.clearAnimation()
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {
                    // Tekrar eden animasyonlar için yapılacak işlemler
                }
            })

            binding.loginButton.startAnimation(animation)

            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Remember Me durumu kaydediliyor
                            val rememberMeChecked = binding.rememberMeCheckbox.isChecked
                            sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, rememberMeChecked).apply()

                            // Ana ekrana yönlendirme
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        } else {
                            Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Email and password must not be empty", Toast.LENGTH_SHORT).show()
            }
        }


        binding.goToRegisterButton.setOnClickListener {
            // Register butonu için tıklama animasyonu
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_click)
            binding.goToRegisterButton.startAnimation(animation)

            // Kayıt ekranına yönlendirme
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}

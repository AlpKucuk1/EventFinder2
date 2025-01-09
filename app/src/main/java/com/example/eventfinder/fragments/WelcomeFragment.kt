package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eventfinder.R
import com.example.eventfinder.databinding.FragmentWelcomeBinding
import android.view.animation.AnimationUtils

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Continue butonu için animasyonlu tıklama
        binding.navigateToLoginButton.setOnClickListener {
            // Animasyon başlat
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_click)
            binding.navigateToLoginButton.startAnimation(animation)

            // LoginFragment'e yönlendirme
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

    }
        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
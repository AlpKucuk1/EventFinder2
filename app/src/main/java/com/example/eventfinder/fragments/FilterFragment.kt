package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eventfinder.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        // Set up button listeners
        binding.filterByDateButton.setOnClickListener {
            // Implement filter by date logic
        }

        binding.filterByLocationButton.setOnClickListener {
            // Implement filter by location logic
        }

        return binding.root
    }
}

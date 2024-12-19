package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.eventfinder.databinding.FragmentEventDetailBinding

class EventDetailFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailBinding
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)

        // Bind event details
        val event = args.event
        binding.eventNameDetail.text = event.name
        binding.eventLocationDetail.text = event.location
        binding.eventDateDetail.text = "${event.date} at ${event.time}"
        binding.eventDescriptionDetail.text = event.description

        return binding.root
    }
}

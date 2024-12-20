package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.eventfinder.R
import com.example.eventfinder.databinding.FragmentEventListBinding
import com.example.eventfinder.viewmodel.EventViewModel
import com.example.eventfinder.EventDetails

class EventListFragment : Fragment(R.layout.fragment_event_list) {

    private lateinit var binding: FragmentEventListBinding
    private val eventViewModel: EventViewModel by viewModels() // ViewModel scoped to this Fragment
    private lateinit var adapter: EventDetails

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEventListBinding.bind(view)

        adapter = EventDetails { event ->
            val action = EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        eventViewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        // Ensure fetchEvents is called here
        eventViewModel.fetchEvents()
    }

}

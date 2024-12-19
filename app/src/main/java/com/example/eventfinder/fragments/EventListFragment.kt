package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventfinder.R
import com.example.eventfinder.adapters.EventDetails
import com.example.eventfinder.databinding.FragmentEventListBinding
import com.example.eventfinder.models.Event

class EventListFragment : Fragment(R.layout.fragment_event_list) {

    private lateinit var binding: FragmentEventListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize binding
        binding = FragmentEventListBinding.bind(view)

        // Sample events
        val events = listOf(
            Event(1, "Event A", "Location X", "2024-12-01", "10:00 AM", "Description A"),
            Event(2, "Event B", "Location Y", "2024-12-02", "2:00 PM", "Description B"),
        )

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = EventDetails(events) { event ->
            val action = EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
    }
}

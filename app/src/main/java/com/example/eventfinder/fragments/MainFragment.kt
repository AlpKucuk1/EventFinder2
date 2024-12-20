package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventfinder.R
import com.example.eventfinder.EventDetails
import com.example.eventfinder.databinding.FragmentMainBinding
import com.example.eventfinder.Event

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val events = mutableListOf<Event>() // Mutable list for dynamic updates
    private lateinit var adapter: EventDetails

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        // Set up RecyclerView with the EventDetails adapter
        adapter = EventDetails { event ->
            val action = MainFragmentDirections.actionMainFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Load and display events
        loadEvents()

        // Sort button popup
        binding.sortButton.setOnClickListener { sortButtonView ->
            val popupMenu = PopupMenu(requireContext(), sortButtonView)
            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.filter_name -> {
                        filterEvents("Name")
                        true
                    }
                    R.id.filter_city -> {
                        filterEvents("City")
                        true
                    }
                    R.id.filter_date -> {
                        filterEvents("Date")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

    }

    private fun filterEvents(criteria: String) {
        val filteredEvents = when (criteria) {
            "Name" -> events.sortedBy { it.name }
            "City" -> events.sortedBy { it.location }
            "Date" -> events.sortedBy { it.date }
            else -> events
        }
        adapter.submitList(filteredEvents) // Use submitList for ListAdapter
    }

    private fun loadEvents() {
        events.addAll(
            listOf(
                Event(1, "Event A", "Location X", "2024-12-01", "10:00 AM", "Description A"),
                Event(2, "Event B", "Location Y", "2024-12-02", "2:00 PM", "Description B")
            )
        )
        adapter.submitList(events.toList()) // Populate the adapter
    }
}

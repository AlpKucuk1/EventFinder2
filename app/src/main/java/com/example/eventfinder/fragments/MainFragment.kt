package com.example.eventfinder.fragments

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventfinder.R
import com.example.eventfinder.adapters.EventDetails
import com.example.eventfinder.databinding.FragmentMainBinding
import com.example.eventfinder.models.Event

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var events: List<Event>
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        // Load events
        events = loadEvents()

        // Set up RecyclerView with the EventDetails adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = EventDetails(events) { event ->
            val action = MainFragmentDirections.actionMainFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }

        // Sort button popup
        binding.sortButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(requireContext(), view)
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
        binding.recyclerView.adapter = EventDetails(filteredEvents) { event ->
            val action = MainFragmentDirections.actionMainFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
    }

    private fun loadEvents(): List<Event> {
        return listOf(
            Event(1, "Event A", "Location X", "2024-12-01", "10:00 AM", "Description A"),
            Event(2, "Event B", "Location Y", "2024-12-02", "2:00 PM", "Description B"),
        )
    }
}

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast

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

        // Sign out button functionality
        binding.signOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
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
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("events")
            .get()
            .addOnSuccessListener { result ->
                val loadedEvents = result.documents.mapNotNull { it.toObject(Event::class.java) }
                events.clear() // Clear the old list
                events.addAll(loadedEvents) // Add new data
                adapter.submitList(events.toList()) // Update adapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to load events: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

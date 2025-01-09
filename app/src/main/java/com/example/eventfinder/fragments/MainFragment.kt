package com.example.eventfinder.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventfinder.R
import com.example.eventfinder.EventDetails
import com.example.eventfinder.databinding.FragmentMainBinding
import com.example.eventfinder.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val events = mutableListOf<Event>() // Mutable list for dynamic updates
    private lateinit var adapter: EventDetails
    private val PREFS_NAME = "user_prefs"
    private val REMEMBER_ME_KEY = "remember_me"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        // SharedPreferences başlat
        val sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Kullanıcı adını yükle
        loadUsername()

        // Set up RecyclerView with the EventDetails adapter
        adapter = EventDetails { event ->
            val action = MainFragmentDirections.actionMainFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
        val layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        binding.recyclerView.layoutAnimation = layoutAnimationController

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Load and display events
        loadEvents()

        // Search bar ekleme ve dinleme
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchEvents(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

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
            // Oturum durumunu sıfırla
            sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, false).apply()

            // Firebase Authentication'dan çıkış yap
            FirebaseAuth.getInstance().signOut()

            // LoginFragment'e yönlendir
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show()
        }
    }

    // Search özelliği
    private fun searchEvents(query: String) {
        val filteredList = if (query.isNotEmpty()) {
            events.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.location.contains(query, ignoreCase = true)
            }
        } else {
            events
        }
        adapter.submitList(filteredList)
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

    private fun loadUsername() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    val username = document.getString("username")
                    binding.eventsTitle.text = "Welcome, $username" // Kullanıcı adı başlığa eklenir
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to load username", Toast.LENGTH_SHORT).show()
                }
        }
    }
}

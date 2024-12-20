package com.example.eventfinder.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.eventfinder.Event

@BindingAdapter("eventDateTime")
fun bindEventDateTime(textView: TextView, event: Event?) {
    event?.let {
        textView.text = "${it.date} at ${it.time}"
    }
}

@BindingAdapter("formattedEventName")
fun bindFormattedEventName(textView: TextView, name: String?) {
    textView.text = "Event: $name"
}

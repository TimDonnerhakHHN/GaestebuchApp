package com.example.aufgabe3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aufgabe3.model.BookingEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class SharedViewModel : ViewModel() {
    private val _bookingsEntries = MutableStateFlow<List<BookingEntry>>(emptyList())
    val bookingsEntries: StateFlow<List<BookingEntry>> = _bookingsEntries.asStateFlow()

    fun addBookingEntry(name: String, arrivalDate: LocalDate, departureDate: LocalDate) {
        viewModelScope.launch {
            _bookingsEntries.update { currentList ->
                currentList + BookingEntry(name, arrivalDate, departureDate)
            }
        }
    }

    fun deleteBookingEntry(entry: BookingEntry) {
        viewModelScope.launch {
            _bookingsEntries.update { currentList ->
                currentList.filter { it != entry }
            }
        }
    }
}
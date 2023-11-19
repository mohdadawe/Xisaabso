package com.gabiley.xisaabso.MainViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


@OptIn(FlowPreview::class)
class MainViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(allPersons)

    val persons = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { text, persons ->
            if(text.isBlank()) {
                persons
            }
            else {
                delay(2000L)
                persons.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000),
            _persons.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

data class Person(
    val FirstName: String?,
    val lastName: String?,
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$FirstName$lastName",
            "$FirstName $lastName",
            "${FirstName!!.first()} ${lastName!!.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}


private val allPersons = listOf(
    Person(
        FirstName = "Philipp",
        lastName = "Lackner"
    ),
    Person(
        FirstName = "Beff",
        lastName = "Jezos"
    ),
    Person(
        FirstName = "Chris P.",
        lastName = "Bacon"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jeve",
        lastName = "Stops"
    ),

    Person(
        FirstName = "Jave",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jave",
        lastName = "Stops"
    ),
    Person(
        FirstName = "Jave",
        lastName = "Stops"
    ),
)

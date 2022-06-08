package com.example.proyecto1.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.local.Locations
import com.example.proyecto1.data.notes.Notes
import com.example.proyecto1.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class NotesViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {


    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    val notes = repository.getAllNotes()


    fun setTextFieldValue(value: String) {
        _textFieldValue.value = value
    }

    fun insertNotes() {
        viewModelScope.launch {
            if (textFieldValue.value.isBlank()) {
                return@launch
            }
            repository.insertNotes(Notes(textFieldValue.value,"aa","19-9-2020"))
        }
    }
}
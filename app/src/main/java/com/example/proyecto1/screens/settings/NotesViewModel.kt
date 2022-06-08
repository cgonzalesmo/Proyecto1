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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {


    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    private val _textFieldDesValue = mutableStateOf("")
    val textFieldDesValue: State<String> = _textFieldDesValue

    private val _idCurrent = mutableStateOf(1)
    val idCurrent: State<Int> = _idCurrent


    val notes = repository.getAllNotes()


    fun setTextFieldValue(value: String) {
        _textFieldValue.value = value
    }
    fun setTextFieldDesValue(value: String) {
        _textFieldDesValue.value = value
    }
    fun setIdCurrent(value: Int){
        _idCurrent.value = value
    }

    fun insertNotes() {
        viewModelScope.launch {
            if (textFieldValue.value.isBlank()) {
                return@launch
            }
            repository.insertNotes(Notes(textFieldValue.value,textFieldDesValue.value,getCurrentDateTime().toString()))
        }
    }
    fun editNotes() {
        viewModelScope.launch {
            repository.updateNotes(Notes(textFieldValue.value,textFieldDesValue.value,getCurrentDateTime().toString(),idCurrent.value))
        }
    }
    fun deleteNotes(){
        viewModelScope.launch {
            repository.deleteNotes(Notes("","","",idCurrent.value))
        }
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}
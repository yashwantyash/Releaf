//package com.releaf.releaf.models.journal
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.releaf.releaf.repository.JournalRepository
//import kotlinx.coroutines.launch
//
//class JournalViewModel(private val repository: JournalRepository) : ViewModel(){
//    private var _journals:List<Journal> = emptyList()
//    val allJournals : List<Journal> get() = _journals
//
//    fun getAllJournalByDate(){
//        viewModelScope.launch {
//            _journals = repository.getAllJournalByDate()
//        }
//    }
//}
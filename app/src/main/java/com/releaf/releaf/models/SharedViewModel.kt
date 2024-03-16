package com.releaf.releaf.models

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel(){

    private var _JournalChecks: JournalCheckBoxModel? = null
    fun setJournalChecksData(journalCheck: JournalCheckBoxModel){
        _JournalChecks = journalCheck
    }

    fun getJournalChecks(): JournalCheckBoxModel? {
        return _JournalChecks
    }
}
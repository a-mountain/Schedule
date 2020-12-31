package com.example.schedule.app.ui.currentday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentDayViewModel(private val repository: ScheduleRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val schedule = repository.getCurrentSchedule()
            dayState.postValue(schedule?.today())
        }
    }

    private val dayState = MutableLiveData<Day?>()
    val day: LiveData<Day?> get() = dayState

}

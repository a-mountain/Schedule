package com.example.schedule.app.ui.schedulesetup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.data.ConnectionError
import com.example.schedule.data.GroupNotFound
import com.example.schedule.domain.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleSetupViewModel(private val repository: ScheduleRepository) : ViewModel() {

    private val errorState = MutableLiveData<String>()
    val error: LiveData<String> get() = errorState

    fun setupSchedule(groupName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.newSchedule(groupName)
            } catch (e: GroupNotFound) {
               errorState.postValue("Група ${e.groupName} - відсутня")
            } catch (e: ConnectionError) {
                errorState.postValue("Інтернет підлючення - відсутнє")
            }
        }
    }
}
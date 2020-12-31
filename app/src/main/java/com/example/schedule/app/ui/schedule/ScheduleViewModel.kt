package com.example.schedule.app.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class ScheduleViewModel(private val repository: ScheduleRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            schedule = repository.getCurrentSchedule()
            daysState.postValue(schedule?.getScheduleForTwoWeeks(LocalDate.now()))
        }
    }

    private val daysState: MutableLiveData<List<Day>> = MutableLiveData()

    val days: LiveData<List<Day>>
        get() = daysState

    private  var schedule: Schedule? = null
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text

}

val lesson = Lesson(
    Subject(
        mutableMapOf(LessonType.Lecture to Teacher("Перевалов М.О.")),
        "Програмування"
    ),
    LessonType.Lecture,
    1,
    "каб. 202-18",
    LocalTime.of(10, 25),
    LocalTime.of(12, 0)
)

val days: List<Day> = listOf(
    Day(
        LocalDate.now(),
        DaySchedule(
            1,
            LocalDate.now().dayOfWeek,
            listOf(
                lesson,
                lesson
            )
        )
    )
)
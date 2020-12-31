package com.example.schedule.app.di

import com.example.schedule.app.ui.schedule.ScheduleViewModel
import com.example.schedule.app.ui.currentday.CurrentDayViewModel
import com.example.schedule.app.ui.schedulesetup.ScheduleSetupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel { ScheduleViewModel(get()) }
    viewModel { CurrentDayViewModel(get()) }
    viewModel { ScheduleSetupViewModel(get()) }
}
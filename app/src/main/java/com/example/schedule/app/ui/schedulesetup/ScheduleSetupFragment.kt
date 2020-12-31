package com.example.schedule.app.ui.schedulesetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.schedule.R
import com.example.schedule.app.lib.showMessageDialog
import com.example.schedule.app.lib.viewBinding
import com.example.schedule.databinding.FragmentScheduleSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleSetupFragment : Fragment(R.layout.fragment_schedule_settings) {

    private val scheduleSetupViewModel: ScheduleSetupViewModel by viewModel()
    private val binding: FragmentScheduleSettingsBinding by viewBinding(
        FragmentScheduleSettingsBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnOK.setOnClickListener {
            scheduleSetupViewModel.setupSchedule(binding.etGroupName.text.toString())
        }
        scheduleSetupViewModel.error.observe(viewLifecycleOwner) {
            showMessageDialog(requireContext(), it)
        }
    }
}
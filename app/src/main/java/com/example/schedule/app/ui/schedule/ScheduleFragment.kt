package com.example.schedule.app.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.R
import com.example.schedule.app.lib.showMessageDialog
import com.example.schedule.app.lib.viewBinding
import com.example.schedule.databinding.FragmentScheduleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private val scheduleViewModel: ScheduleViewModel by viewModel()
    private val binding: FragmentScheduleBinding by viewBinding(FragmentScheduleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()
        binding.rvDays.layoutManager = LinearLayoutManager(context)
        scheduleViewModel.days.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvDays.adapter = ScheduleAdapter(it, context)
            } else {
                showMessageDialog(requireContext(), "Завантажте групу")
            }
        }
    }
}
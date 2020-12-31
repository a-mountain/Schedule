package com.example.schedule.app.ui.currentday

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.R
import com.example.schedule.app.lib.showMessageDialog
import com.example.schedule.app.lib.toDateString
import com.example.schedule.app.lib.toFormatString
import com.example.schedule.databinding.FragmentCurrentDayBinding
import com.example.schedule.app.lib.viewBinding
import com.example.schedule.app.ui.common.LessonsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentDayFragment : Fragment(R.layout.fragment_current_day) {

    private val currentDayViewModel: CurrentDayViewModel by viewModel()
    private val binding: FragmentCurrentDayBinding by viewBinding(FragmentCurrentDayBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currentDayViewModel.day.observe(viewLifecycleOwner) {
            val day = it
            if (day != null) {
                binding.tvDayDate.text = day.date.toDateString()
                binding.tvDayOfWeek.text = day.schedule.dayOfWeek.toFormatString()
                binding.tvWeekNum.text = "Тиждень ${day.schedule.weekNumber}"
                val rvLessons = binding.rvLessons
                rvLessons.layoutManager = LinearLayoutManager(context)
                rvLessons.adapter = LessonsAdapter(day.schedule.lessons)
                rvLessons.setHasFixedSize(true)
                rvLessons.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            } else {
                showMessageDialog(requireContext(), "Завантажте групу")
            }
        }
    }
}
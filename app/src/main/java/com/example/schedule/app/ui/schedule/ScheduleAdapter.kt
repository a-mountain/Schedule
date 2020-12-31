package com.example.schedule.app.ui.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R
import com.example.schedule.app.lib.toDateString
import com.example.schedule.app.lib.toFormatString
import com.example.schedule.app.ui.common.LessonsAdapter
import com.example.schedule.databinding.DayBinding
import com.example.schedule.domain.Day
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

class ScheduleAdapter(private val days: List<Day>, private val context: Context) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = days.size

    private fun createViewHolder(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DayBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: DayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val day = days[position]
            binding.layoutDayInfo.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.design_default_color_primary
                )
            )
            binding.tvDayOfWeek.text = day.schedule.dayOfWeek.toFormatString()
            binding.tvWeekNum.text = "Тиждень ${day.schedule.weekNumber}"
            binding.tvDayDate.text =  day.date.toDateString()
            setupRecyclerView(day)
        }

        private fun setupRecyclerView(day: Day) {
            val rv = binding.rvLessons
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = LessonsAdapter(day.schedule.lessons)
            rv.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}

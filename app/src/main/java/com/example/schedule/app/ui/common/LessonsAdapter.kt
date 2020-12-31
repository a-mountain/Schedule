package com.example.schedule.app.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.LessonBinding
import com.example.schedule.domain.Lesson
import com.example.schedule.domain.LessonType

class LessonsAdapter(private val lessons: List<Lesson>) :
    RecyclerView.Adapter<LessonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = lessons.size

    private fun createViewHolder(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LessonBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: LessonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val lesson = lessons[position]
            binding.tvLessonType.text = when (lesson.lessonType) {
                LessonType.Lecture -> "Лекція"
                LessonType.Practise -> "Практика"
                LessonType.Lab -> "Лабораторна"
                else -> " "
            }
            binding.tvLessonTime.text =
                "${lesson.start.hour}:${lesson.start.minute}-${lesson.end.hour}:${lesson.end.minute}"
            binding.tvNumLesson.text = lesson.lessonNumber.toString()
            binding.tvRoom.text = lesson.room
            binding.tvSubjectName.text = lesson.subject.title
            binding.tvTeacher.text = lesson.teacher.name
        }
    }
}

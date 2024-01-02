package com.example.studysmart.ui.presentation.task

import android.content.ClipDescription
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.util.Priority

sealed class TaskEvent {
    data class OnTitleChange(val title:String) : TaskEvent()
    data class OnDescriptionChange(val description: String) :TaskEvent()
    data class OnDateChange(val millis: Long?) :TaskEvent()
    data class OnPriorityChange(val priority: Priority) :TaskEvent()
    data class onRelatedSubjectSelect(val subject: Subject) :TaskEvent()
    data object SaveTask : TaskEvent()
    data object OnIsCompleteChange : TaskEvent()
    data object DeleteTask : TaskEvent()
}
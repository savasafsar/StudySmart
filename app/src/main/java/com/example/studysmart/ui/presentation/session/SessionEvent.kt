package com.example.studysmart.ui.presentation.session

import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import javax.xml.datatype.Duration

sealed class SessionEvent {
    data class OnRelatedSubjectChange(val subject: Subject) : SessionEvent()
    data class SaveSession(val duration: Long) : SessionEvent()
    data class OnDeleteSessionButtonClick(val session: Session) : SessionEvent()

    data object DeleteSession :SessionEvent()
    data object CheckSubjectId :SessionEvent()
    data class UpdateSubjectIdAndRelatedSubject(
        val subjectId : Int,
        val relatedToSubject: String,
    ) :SessionEvent()

}
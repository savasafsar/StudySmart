package com.example.studysmart.ui.presentation.session

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.repository.SessionRepository
import com.example.studysmart.domain.repository.SubjectRepository
import com.example.studysmart.util.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    subjectRepository: SubjectRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SessionState())
    val state = combine(
        _state,
        subjectRepository.getAllSubjects(),
        sessionRepository.getAllSessions()
    ) { state , subjects , sessions->
        state.copy(
            subjects = subjects,
            sessions = sessions
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = SessionState()
    )
    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()
    fun onEvent(event: SessionEvent) {
        when(event) {
            is SessionEvent.SaveSession -> insertSession(event.duration)
            is SessionEvent.OnRelatedSubjectChange -> {
                _state.update {
                    it.copy(
                        relatedToSubject = event.subject.name,
                        subjectId = event.subject.subjectId
                    )
                }
            }
            SessionEvent.CheckSubjectId -> {}
            SessionEvent.DeleteSession -> {}
            is SessionEvent.OnDeleteSessionButtonClick -> {}
            is SessionEvent.UpdateSubjectIdAndRelatedSubject -> {}
        }
    }

    private fun insertSession(duration: Long) {

        viewModelScope.launch {
            if (duration<36) {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar("Single session can not be less than 36 seconds.",)
                )
                return@launch
            }
            try {
            sessionRepository.insertSession(
               session = Session(
                   sessionSubjectId = state.value.subjectId ?: -1,
                   relatedToSubject = state.value.relatedToSubject ?: "",
                   duration = duration,
                   date = Instant.now().toEpochMilli(),
                   sessionId = 0
               )
            )
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar("Session saved successfully.",)
                )
            }catch (e:Exception) {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar(
                        "Couldn't save session.${e.message}",
                      duration= SnackbarDuration.Long
                    )
                )
            }
        }
    }
}
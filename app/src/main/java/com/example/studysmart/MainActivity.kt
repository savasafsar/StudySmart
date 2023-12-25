package com.example.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import com.example.studysmart.ui.presentation.dashboard.DashboardScreen
import com.example.studysmart.ui.presentation.dashboard.DashboardScreenTopBar
import com.example.studysmart.ui.presentation.session.SessionScreen
import com.example.studysmart.ui.presentation.subject.SubjectScreen
import com.example.studysmart.ui.presentation.task.TaskScreen
import com.example.studysmart.ui.theme.StudySmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudySmartTheme {
              SubjectScreen()
            }
        }
    }
}
val subjects = listOf(
    Subject("English", 10f, Subject.subjectCardColors[0],0),
    Subject("Physics", 10f, Subject.subjectCardColors[1],0),
    Subject("Maths", 10f, Subject.subjectCardColors[2],0),
    Subject("Geology", 10f, Subject.subjectCardColors[3],0),
    Subject("Fine Arts", 10f, Subject.subjectCardColors[4],0),
)

val tasks = listOf(
    Task("Prepare notes","",0L,0,"",false,0,1),
    Task("Do Homework","",0L,1,"",true,0,1),
    Task("Go Coaching","",0L,2,"",false,0,1),
    Task("Assignment","",0L,0,"",false,0,1),
    Task("Write Poew","",0L,0,"",true,0,1),

    )

val sessions = listOf(
    Session(relatedToSubject = "English", date = 0L, duration = 2, sessionSubjectId =0, sessionId =0  ),
    Session(relatedToSubject = "Physics", date = 0L, duration = 2, sessionSubjectId =0, sessionId =0  ),
    Session(relatedToSubject = "Maths", date = 0L, duration = 2, sessionSubjectId =0, sessionId =0  ),
    Session(relatedToSubject = "English", date = 0L, duration = 2, sessionSubjectId =0, sessionId =0  ),
)
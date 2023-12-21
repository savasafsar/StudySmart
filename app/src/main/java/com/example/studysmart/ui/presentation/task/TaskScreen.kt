package com.example.studysmart.ui.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.presentation.components.TaskCheckBox

@Composable
fun TaskScreen() {
    Scaffold(
        topBar = {
            TaskScreenTopBar(
                isComplete = true,
                isTaskExits = false,
                checkBoxBorderColor = Color.Red,
                onBackButtonClick = { },
                onDeleteButtonClick = { },
                onCheckBoxClick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            OutlinedTextField(
                
                value = "",
                onValueChange = {},
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreenTopBar(
    isComplete: Boolean,
    isTaskExits: Boolean,
    checkBoxBorderColor: Color,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onCheckBoxClick: () -> Unit,

    ) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = onBackButtonClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Navigate Back"
            )
        }
    }, title = { Text(
        text = "Task",
        style = MaterialTheme.typography.bodySmall,

    ) },
        actions = {
            if (isTaskExits) {
                TaskCheckBox(
                    borderColor = checkBoxBorderColor,
                    isComplete = isComplete,
                    onCheckBoxClick = onCheckBoxClick
                )
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Task"
                    )
                }

            }
        }
    )
}
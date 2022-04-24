package com.alex.miet.mobile.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.miet.mobile.MietApp
import com.alex.miet.ui_common.theme.MietScheduleTheme
import com.miet.alex.data.entities.LessonItem
import com.miet.alex.data.repositories.GroupsRepository
import com.miet.alex.data.repositories.LessonsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var lessonsRepository: LessonsRepository

    @Inject
    lateinit var groupRepository: GroupsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MietApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            MietScheduleTheme {

                val coroutineScope = rememberCoroutineScope()
                var groups by remember { mutableStateOf(emptyList<LessonItem>()) }

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    coroutineScope.launch {
                        lessonsRepository.loadLessons("ИВТ-13")
                        lessonsRepository.observeLessons("ИВТ-13").collect {
                            groups = it
                        }
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(
                            groups,
                            itemContent = {
                                GroupItem(it.disciplineName)
                            })
                    }
                }
            }
        }
    }
}


@Composable
fun GroupItem(group: String) {
    Row {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = group, style = typography.h6)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MietScheduleTheme {

    }
}
package com.alex.miet.mobile.ui

import android.os.Bundle
import androidx.activity.compose.setContent
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
import androidx.core.app.ComponentActivity
import com.alex.miet.miet_api.MietApiService
import com.alex.miet.ui_common.theme.MietScheduleTheme
import com.miet.alex.data.entities.GroupItem
import com.miet.alex.data.mappers.GroupNameMapper
import com.miet.alex.data.repositories.GroupsDataSource
import com.miet.alex.data.repositories.GroupsRepository
import com.miet.alex.data.repositories.GroupsStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val scheduleRepo = ScheduleRemoteDataSourceImpl(MietApiService.create(OkHttpClient()))
//        val lessonsRepository = LessonsRepository(MietApiService.create(OkHttpClient()))


        val api = MietApiService.create(OkHttpClient())
        val dataSource = GroupsDataSource(api, GroupNameMapper())
        val store = GroupsStore()

        val groupsRepository = GroupsRepository(dataSource, )

        setContent {
            MietScheduleTheme {

                val coroutineScope = rememberCoroutineScope()
                var groups by remember { mutableStateOf(emptyList<GroupItem>()) }

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    coroutineScope.launch {
                        groupsRepository.loadGroups().onEach { result ->
                            groups = result
                        }
                    }
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(
                            groups,
                            itemContent = {
                                GroupItem(it.group)
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
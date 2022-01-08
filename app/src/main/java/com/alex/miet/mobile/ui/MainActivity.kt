package com.alex.miet.mobile.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.alex.miet.miet_api.MietApiService
import com.alex.miet.mobile.network.ScheduleRemoteDataSourceImpl
import com.alex.miet.mobile.ui.theme.MietScheduleTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduleRepo = ScheduleRemoteDataSourceImpl(MietApiService.create(OkHttpClient()))
        setContent {
            MietScheduleTheme {

                val coroutineScope = rememberCoroutineScope()
                var groups by remember { mutableStateOf(emptyList<String>()) }

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    coroutineScope.launch {
                        scheduleRepo.getGroupNames().collect { result ->
                            result.onSuccess {
                                groups = it
                            }
                        }
                    }
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(
                            groups,
                            itemContent = {
                                GroupItem(it)
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
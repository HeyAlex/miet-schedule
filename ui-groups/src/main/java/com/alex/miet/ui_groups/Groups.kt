package com.alex.miet.ui_groups

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import com.alex.miet.ui_common.theme.MietScheduleTheme
import com.miet.alex.data.repositories.GroupsRepository
import kotlinx.coroutines.launch

@Composable
fun Groups(groupRepo: GroupsRepository, loadGroup: (groupId: Long) -> Unit) {
    MietScheduleTheme {

//        val coroutineScope = rememberCoroutineScope()
//        var groups by remember { mutableStateOf(emptyList<String>()) }
//
//        // A surface container using the 'background' color from the theme
//        Surface(color = MaterialTheme.colors.background) {
//            coroutineScope.launch {
//                groupRepo.load().collect { result ->
//                    result.onSuccess {
//                        groups = it
//                    }
//                }
//            }
//            LazyColumn(
//                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//            ) {
//                items(
//                    groups,
//                    itemContent = {
//                        GroupItem(it)
//                    })
//            }
//        }
    }
}
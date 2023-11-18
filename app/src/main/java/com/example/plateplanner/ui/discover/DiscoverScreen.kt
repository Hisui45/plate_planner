package com.example.plateplanner.ui.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
){


//    Timber.tag("find").d(viewModel.answer.toString())
    DiscoverContent()
//    val search
    val response: MutableState<String> = remember { mutableStateOf("") }
    val query =  remember { mutableStateOf("") }
    SearchBar(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        query = query.value,
        active = false,
        onSearch = { viewModel.sendRequest(query.value)} ,
        onActiveChange = {  },
        onQueryChange = {query.value = it},
    ){

    }

    Text(viewModel.answer)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverContent(){

}
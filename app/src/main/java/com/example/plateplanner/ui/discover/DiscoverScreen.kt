package com.example.plateplanner.ui.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber


@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
//    Timber.tag("find").d(uiState.recipes.toString())
    DiscoverContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverContent(){
    SearchBar(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        query = "",
        active = false,
        onSearch = {} ,
        onActiveChange = {  },
        onQueryChange = {},
    ){

    }
}
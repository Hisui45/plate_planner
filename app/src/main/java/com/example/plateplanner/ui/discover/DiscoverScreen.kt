package com.example.plateplanner.ui.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
){
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
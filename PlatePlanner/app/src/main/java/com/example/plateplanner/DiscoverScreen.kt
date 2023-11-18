package com.example.plateplanner

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable


@Composable
fun DiscoverScreen(){
    DiscoverContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverContent(){
    SearchBar(
        query = "",
        active = false,
        onSearch = {} ,
        onActiveChange = {  },
        onQueryChange = {},
    ){

    }
}
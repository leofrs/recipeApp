package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainView(modifier: Modifier) {
    val mainViewModel: MainViewModel = viewModel()
    val viewState by mainViewModel.categoriesState
    
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> { Text(text = "Error Occurred") }

            else -> {
                DisplayItems(categories = viewState.list)
            }
        }
    }
}

@Composable
fun DisplayItems(categories: List<Category>){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories){
            // A lambda function pega cada item dentro de "categories"
            // e separa de forma individual em "category"
            // Após isso, passa para a função Items de forma individual cada item caegory
            category -> Items(categories = category)
        }
    }
}

@Composable
fun Items(categories: Category) {
    Column {
        Image(
            painter = rememberAsyncImagePainter(model = categories.strCategoryThumb, ),
            contentDescription = "Image of each item menu"
        )
        Text(
            "${categories.strCategory}", 
            color = Color.Black, 
            fontStyle = FontStyle.Italic, 
            fontWeight = FontWeight.Bold)
    }
}
package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class RecipeStore(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)
class MainViewModel : ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeStore())
    val categoriesState: State<RecipeStore> = _categoriesState
    init {
        fetchCategories()
    }
    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            }catch (e:Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error found: ${e.message}",
                )
            }
        }
    }
}



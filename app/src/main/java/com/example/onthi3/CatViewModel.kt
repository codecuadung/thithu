package com.example.onthi3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatViewModel:ViewModel() {
    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> = _cats
    private val catAptService = CatAptService.create()

    init {
        fetchCats()
    }

    private fun fetchCats() {
       viewModelScope.launch {
           try{
               val fetchedCats = catAptService.getCats()
               _cats.value = fetchedCats
           }catch (e: Exception){
               e.printStackTrace()
           }
       }
    }

}
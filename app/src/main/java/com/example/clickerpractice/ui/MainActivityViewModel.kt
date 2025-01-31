package com.example.clickerpractice.ui

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.clickerpractice.Constants

class MainActivityViewModel(private val application: Application) : AndroidViewModel(application) {

    data class ClickerUIState(
        val countOfClicks: Int = 0,
    )

    private val _uiState = MutableLiveData(ClickerUIState())
    val uiState: LiveData<ClickerUIState>
        get() = _uiState

    fun loadClicker() {
        _uiState.value = _uiState.value?.copy(countOfClicks = getCountOfClicks())
    }

    fun onClickMeClicked() {
        _uiState.value =
            _uiState.value?.copy(countOfClicks = _uiState.value?.countOfClicks?.plus(1) ?: 0)
        _uiState.value?.countOfClicks?.let { saveCountOfClicks(it) }
    }


    private fun getCountOfClicks(): Int {
        val sharedPreferences =
            application.getSharedPreferences(Constants.COUNT_CLICKS_KEY_SP, Context.MODE_PRIVATE)

        return sharedPreferences.getInt(Constants.COUNT_CLICKS_KEY_SP, 0)
    }

    private fun saveCountOfClicks(countOfClicks: Int) {
        val sharedPreferences =
            application.getSharedPreferences(Constants.COUNT_CLICKS_KEY_SP, Context.MODE_PRIVATE)

        sharedPreferences.edit {
            putInt(Constants.COUNT_CLICKS_KEY_SP, countOfClicks)
            apply()
        }
    }

}
package com.silentdev.whetherkmm.persentation.home_page

import cafe.adriel.voyager.core.model.ScreenModel
import com.silentdev.whetherkmm.core.util.DELHI_LATITUDE
import com.silentdev.whetherkmm.core.util.DELHI_LONGITUDE
import com.silentdev.whetherkmm.domain.repository.WeatherRepositoryImpl
import com.silentdev.whetherkmm.persentation.state.WeatherUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeScreenViewModel : ScreenModel, KoinComponent {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val repository: WeatherRepositoryImpl by inject()

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> get() = _uiState

    init {
        loadWeather(DELHI_LATITUDE, DELHI_LONGITUDE)
    }


    private fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWeatherData(
                longitude = lon,
                latitude = lat
            ).collectLatest {
                _uiState.value = it
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
        viewModelScope.cancel()

    }
}
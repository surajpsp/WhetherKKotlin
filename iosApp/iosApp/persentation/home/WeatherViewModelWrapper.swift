//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Suraj Sharma on 4/7/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Combine
import Shared

@MainActor
final class WeatherViewModelWrapper: ObservableObject {
    private let vm: WeatherViewModel = WeatherViewModelProvider().provideWeatherViewModel()
        private var job: Kotlinx_coroutines_coreJob? = nil

    @Published var uiState: WeatherUiState = WeatherUiState.Idle()

        init() {
            // start observing
            job = vm.observeUiState { [weak self] state in
                // marshal back to main thread
                Task { @MainActor in
                    self?.uiState = state
                }
            }
            loadWeather(lat: 28.6448 , lon: 77.2167)
        }


        deinit {
            // stop observing
            job?.cancel(cause: nil)
            vm.clearTask()
        }

        func loadWeather(lat: Double, lon: Double) {
            vm.loadWeather(lat: lat, lon: lon)
        }
}


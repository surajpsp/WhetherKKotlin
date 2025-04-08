//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Suraj Sharma on 4/7/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Combine
import Shared
import KMPNativeCoroutinesCombine
import KMPNativeCoroutinesAsync

@MainActor
final class WeatherViewModelWrapper: ObservableObject {
    private let viewModel: WeatherViewModel = WeatherViewModelProvider().provideWeatherViewModel()
    private var cancellables = Set<AnyCancellable>()
    
    // Expose UI state to SwiftUI
    @Published private(set) var uiState: WeatherUiState = WeatherUiState.Idle()
    
    init(repository: WeatherRepositoryImpl) {
        observeUiState()
    }
    
    // Observe Kotlin StateFlow via Combine
    private func observeUiState() {
        // Observe state changes
    
        createPublisher(for: viewModel.uiState)
                    .receive(on: DispatchQueue.main)
                    .sink { [weak self] state in
                        self?.uiState = state
                    }
                    .store(in: &cancellables)
    }
    
    func loadWeather(lat: Double, lon: Double) {
        viewModel.loadWeather(lat: lat, lon: lon)
    }
    
    deinit {
        cancellables.forEach { $0.cancel() }
        viewModel.clearTask() // Ensure coroutine cleanup (add this method to the Kotlin ViewModel)
    }
}


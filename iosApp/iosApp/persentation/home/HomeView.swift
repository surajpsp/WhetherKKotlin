import SwiftUI
import Shared

struct HomeView: View {
    @StateObject private var vm = WeatherViewModelWrapper()

    var body: some View {
        ZStack {
            // Background
            Image("home_backgrond")
                .resizable()
                .scaledToFill()
                .ignoresSafeArea()
                .blur(radius: 4)

            // Foreground content
            VStack(alignment: .leading, spacing: 16) {
                switch vm.uiState {
                case is WeatherUiState.Idle, is WeatherUiState.Loading:
                    // Loading state
                    Spacer()
                    HStack { Spacer() }
                    ProgressView("Loading…")
                        .progressViewStyle(CircularProgressViewStyle())
                        .frame(maxWidth: .infinity, alignment: .center)
                    Spacer()

                case let success as WeatherUiState.Success:
                    let info = success.weather
                    // Location
                    
                    VStack {
                        Text(info.current.location)
                            .font(.title2)
                            .bold()
                        // Temperature
                        Text(info.current.temperatureCelsius)
                            .font(.largeTitle)
                            .bold()
                        // Weather description
                        Text(info.current.weatherInfo)
                            .foregroundColor(.gray)
                    }
                    

                    // Stats row
                    HStack(spacing: 16) {
                        WeatherStat(title: "UV Index", value: info.current.uv)
                        WeatherStat(title: "Humidity", value: info.current.humidity)
                        WeatherStat(title: "Precipitation", value: info.current.precipitation)
                    }

                    // Forecast header
                    Text("Next days")
                        .font(.headline)
                        .padding(.top, 8)

                    // Forecast list
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 12) {
                            ForEach(info.forecast, id: \.date) { day in
                                ForecastCard(day: day)
                            }
                        }
                    }

                case let error as WeatherUiState.Error:
                    // Error state
                    Spacer()
                    Text("Error: \(error.message)")
                        .foregroundColor(.red)
                        .multilineTextAlignment(.center)
                    Button("Retry") {
                        vm.loadWeather(lat: 28.61, lon: 77.23)
                    }
                    .padding(.top, 8)
                    Spacer()

                default:
                    EmptyView()
                }
            }
            .padding(24)
        }
        .onAppear {
            vm.loadWeather(lat: 28.61, lon: 77.23) // your default coords
        }
    }

    @ViewBuilder
    private func WeatherStat(title: String, value: String) -> some View {
        VStack {
            Text(title)
                .font(.subheadline)
                .foregroundColor(.gray)
            Text(value)
                .font(.headline)
        }
        .frame(maxWidth: .infinity)
    }
}

struct ForecastCard: View {
    let day: ForecastDay

    var body: some View {
        VStack(spacing: 8) {
            // You could map day.weatherInfo to SF Symbols here
            Image(systemName: iconName(for: day.weatherInfo))
                .font(.title2)
            Text(day.temperatureCelsius)
                .bold()
            Text(day.date)
                .font(.caption)
        }
        .padding()
        .background(Color.white.opacity(0.2))
        .cornerRadius(12)
    }

    private func iconName(for description: String) -> String {
        switch description.lowercased() {
        case let d where d.contains("cloud"):
            return "cloud.fill"
        case let d where d.contains("rain"):
            return "cloud.rain.fill"
        case let d where d.contains("sun"):
            return "sun.max.fill"
        default:
            return "questionmark"
        }
    }
}

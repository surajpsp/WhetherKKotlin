import SwiftUI

struct HomeView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("Hyderabad")
                .font(.title2)
                .bold()
            Text("25°C")
                .font(.largeTitle)
                .bold()
            Text("Mostly sunny")
                .foregroundColor(.gray)

            HStack {
                WeatherStat(title: "UV Index", value: "7 High")
                WeatherStat(title: "Humidity", value: "61%")
                WeatherStat(title: "Precipitation", value: "4mm")
            }

            Text("Next days").bold()

            ForecastRow(temp: "22°", day: "Friday, 1 Nov", icon: "cloud")
            ForecastRow(temp: "19°", day: "Sunday, 3 Nov", icon: "cloud.rain")
            ForecastRow(temp: "25°", day: "Saturday, 2 Nov", icon: "cloud")
            ForecastRow(temp: "20°", day: "Monday, 4 Nov", icon: "cloud.rain")
        }
        .padding()
    }

    func WeatherStat(title: String, value: String) -> some View {
        VStack {
            Text(title).foregroundColor(.gray)
            Text(value).bold()
        }.frame(maxWidth: .infinity)
    }

    func ForecastRow(temp: String, day: String, icon: String) -> some View {
        HStack {
            Image(systemName: icon)
            Text(temp).bold()
            Spacer()
            Text(day)
        }
    }
}

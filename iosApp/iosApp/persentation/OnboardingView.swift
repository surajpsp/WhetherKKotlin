import SwiftUI

struct OnboardingView: View {
    var body: some View {
        VStack(spacing: 20) {
            Spacer()
            Text("Never get caught in the rain again")
                .font(.title)
                .multilineTextAlignment(.center)
            Text("Stay ahead of the weather with our accurate forecasts")
                .font(.subheadline)
                .foregroundColor(.gray)
                .multilineTextAlignment(.center)

            Spacer()
            Button(action: {
                // Navigate to HomeView
            }) {
                Text("Get started")
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(12)
            }.padding(.horizontal)
        }
        .padding()
        .background(Color.blue.opacity(0.1))
    }
}

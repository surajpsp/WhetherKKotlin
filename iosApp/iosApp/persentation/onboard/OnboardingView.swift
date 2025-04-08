import SwiftUI

struct OnboardingView: View {
    let onContinue: () -> Void
    
    var body: some View {
        ZStack{
            Image("onboard_background")
                .resizable()
                .scaledToFill()
                .ignoresSafeArea()
            
            VStack(alignment: .center ,spacing: 20) {
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
                    onContinue()
                }) {
                    Text("Get started")
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(12)
                }.padding(.horizontal)
            }.padding(.vertical,68)
                .padding(.horizontal, 16)
        }
        
    }
}

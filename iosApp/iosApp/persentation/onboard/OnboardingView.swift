import SwiftUI

struct OnboardingView: View {
    let onContinue: () -> Void
    
    var body: some View {
        ZStack{
            Image("onboard_background")
                .resizable()
                .scaledToFill()
                .ignoresSafeArea()
            
            VStack(alignment: .leading ,spacing: 0) {
                Spacer()
                Text("Never get caught in the rain again")
                    .font(.largeTitle)
                    .bold()
                    .padding(.horizontal)
                    .multilineTextAlignment(.leading)
                    .opacity(0.7)
                Text("Stay ahead of the weather with our accurate forecasts")
                    .font(.headline)
                    .padding(.horizontal)
                    .multilineTextAlignment(.leading)
                    .opacity(0.7)
                
                Spacer().frame(height: 74)
                
                Button(action: {
                    // Navigate to HomeView
                    onContinue()
                }) {
                    Text("Get started")
                        .font(.body)
                        .bold()
                        .padding()
                        .frame(maxWidth: .infinity)
                        .foregroundColor(.white)
                        
                }
                .padding(.horizontal)
                    .background(Color.blue)
                    .cornerRadius(16)
                
            }.padding(.vertical,68)
            .padding(.horizontal, 16)
                .frame(alignment: .bottom)
        }
        
    }
}

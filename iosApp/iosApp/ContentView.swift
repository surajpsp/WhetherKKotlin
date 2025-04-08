import SwiftUI
import Shared

struct ContentView: View {
//    @State private var showContent = false
    
    @State private var showOnboarding = true

        var body: some View {
            if #available(iOS 16.0, *) {
                NavigationStack {
                    if showOnboarding {
                        OnboardingView(onContinue: {
                            showOnboarding = false
                        })
                        .transition(.move(edge: .leading))
                    } else {
                        HomeView()
                            .transition(.move(edge: .trailing))
                    }
                }
            } else {
                // Fallback on earlier versions
            }
        }
    
//    var body: some View {
//        VStack {
//            Button("Click me!") {
//                withAnimation {
//                    showContent = !showContent
//                }
//            }
//
//            if showContent {
//                VStack(spacing: 16) {
//                    Image(systemName: "swift")
//                        .font(.system(size: 200))
//                        .foregroundColor(.accentColor)
//                    Text("SwiftUI: \(Greeting().greet())")
//                }
//                .transition(.move(edge: .top).combined(with: .opacity))
//            }
//        }
//        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
//        .padding()
//    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}

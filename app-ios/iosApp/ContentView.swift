import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.mainViewController(
            createUIViewController: { () -> UIViewController in
                let swiftUIView = VStack {
                    Text("I am SwiftUI Text").foregroundColor(.cyan)
                }
                return UIHostingController(rootView: swiftUIView)
            }
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}




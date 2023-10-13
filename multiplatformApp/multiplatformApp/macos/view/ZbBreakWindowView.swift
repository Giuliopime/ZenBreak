//
//  ZbBreakPopupView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 09/09/23.
//

import SwiftUI

struct ZbBreakWindowView: View {
    @Environment(\.colorScheme) var colorScheme
    @ObservedObject var viewModel: ZbViewModel
    
    @State private var percentage: Double = 1.0
    
    var body: some View {
        GeometryReader { geo in
            ZStack {
                Circle()
                    .trim(from: 0, to: percentage)
                    .rotation(.degrees(-90))
                    .stroke(style: StrokeStyle(lineWidth: 8.0, lineCap: .round))
                    .foregroundColor(.black)
                    .frame(
                        width: geo.size.width * 0.7,
                        height: geo.size.height * 0.7
                    )
                    .padding(12)
                    .background(Circle()
                        .foregroundColor(
                            Color(hexString: viewModel.settings.primaryColor)
                        )
                    )
                
                VStack {
                    Text(viewModel.settings.breakMessage)
                        .font(.largeTitle.weight(.bold))
                        .multilineTextAlignment(.center)
                        .lineLimit(nil)
                        .foregroundStyle(Color(hexString: viewModel.settings.textColor))
                    
                    HStack {
                        if (viewModel.settings.breakSkip) {
                            Button(action: {
                                viewModel.skipBreak()
                            }) {
                                Text("Skip")
                                    .foregroundStyle(.black)
                            }
                            .controlSize(.large)
                            .keyboardShortcut("s")
                        }
                        
                        if (viewModel.settings.breakSnooze) {
                            Button(action: {
                                viewModel.snoozeBreak()
                            }) {
                                Text("Snooze")
                                    .foregroundStyle(.black)
                            }
                            .controlSize(.large)
                            .keyboardShortcut("z")
                        }
                    }
                }.frame(
                    width: geo.size.width * 0.4,
                    height: geo.size.height * 0.4
                )
            }
            .frame(width: geo.size.width, height: geo.size.height, alignment: .center)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(
            Color(red: 0.07, green: 0.07, blue: 0.07)
                .opacity(0.7)
        )
        .onAppear {
            print("Duration of \(viewModel.settings.breakDuration / 1000) seconds")
            withAnimation(Animation.linear(duration: Double(viewModel.settings.breakDuration / 1000))) {
                percentage = 0.0
            }
        }
    }
}

struct ZbBreakWindowView_Previews: PreviewProvider {
    static var previews: some View {
        ZbBreakWindowView(viewModel: ZbViewModel())
    }
}

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
                    .frame(
                        width: geo.size.width * 0.7,
                        height: geo.size.height * 0.7
                    )
                    .background(Circle()
                        .padding(4)
                        .foregroundColor(
                            Color(hex: viewModel.settings.primaryColor) ?? Color.secondary
                        )
                    )
                
                VStack {
                    Text(viewModel.settings.breakMessage)
                        .font(.largeTitle.weight(.bold))
                        .multilineTextAlignment(.center)
                        .lineLimit(nil)
                        .foregroundStyle(Color(hex: viewModel.settings.textColor) ?? Color.primary)
                    
                    HStack {
                        if (viewModel.settings.breakSkip) {
                            Button(action: {
                                viewModel.skipBreak()
                            }) {
                                Text("Skip")
                            }
                            .buttonStyle(.borderedProminent)
                            .keyboardShortcut("s")
                        }
                        
                        if (viewModel.settings.breakSnooze) {
                            Button(action: {
                                viewModel.snoozeBreak()
                            }) {
                                Text("Snooze")
                            }
                            .buttonStyle(.borderedProminent)
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
        .onAppear {
            withAnimation(Animation.linear(duration: Double(viewModel.settings.breakDuration / 1000))) {
                percentage = 0.0
            }
        }
        .onDisappear {
            percentage = 1.0
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(
            Color(red: 0.07, green: 0.07, blue: 0.07)
                .opacity(0.7)
        )
    }
}

struct ZbBreakWindowView_Previews: PreviewProvider {
    static var previews: some View {
        ZbBreakWindowView(viewModel: ZbViewModel())
    }
}

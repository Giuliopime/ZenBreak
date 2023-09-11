//
//  ZbBreakPopupView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 09/09/23.
//

import SwiftUI
import sharedComposePopup

struct ZbBreakPopupView: View {
    @ObservedObject var viewModel: ZbViewModel
    
    var body: some View {
        EmptyView()
            .fullScreenCover(isPresented: $viewModel.breakPopupIsShown) {
                
            }
    }
}

struct ZbBreakPopupView_Previews: PreviewProvider {
    static var previews: some View {
        ZbBreakPopupView(viewModel: ZbViewModel())
    }
}

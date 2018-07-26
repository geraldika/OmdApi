package com.example.omdapi.base;

import com.arellomobile.mvp.MvpView;

public interface LoadingView extends MvpView {

    void showLoading();

    void hideLoading();
}

package com.danfsd.weatherforecastapp.ui.main;

import java.util.List;

public interface MainContract {
    interface View {

    }

    interface Presenter {
        List<String> getCityList();
        void setView(MainContract.View view);
    }
}

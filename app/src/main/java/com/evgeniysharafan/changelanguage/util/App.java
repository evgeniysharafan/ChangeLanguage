package com.evgeniysharafan.changelanguage.util;

import android.app.Application;
import android.preference.PreferenceManager;

import com.evgeniysharafan.changelanguage.BuildConfig;
import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.utils.Utils;

public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this, BuildConfig.DEBUG);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}

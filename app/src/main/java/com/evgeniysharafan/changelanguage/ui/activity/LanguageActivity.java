package com.evgeniysharafan.changelanguage.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.changelanguage.util.LocaleUtils;
import com.evgeniysharafan.utils.Fragments;

public class LanguageActivity extends AppCompatActivity {

    private String currentLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentLanguageCode = LocaleUtils.updateAndGetConfiguration().locale.getLanguage();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // when we return from the settings activity we need to check whether the current language has been changed
        if (!LocaleUtils.isCurrentLanguageCode(currentLanguageCode)) {
            onConfigurationChanged(LocaleUtils.updateAndGetConfiguration());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        currentLanguageCode = newConfig.locale.getLanguage();

        invalidateOptionsMenu();

        Fragment fragment = Fragments.getById(getSupportFragmentManager(), R.id.content);
        if (Fragments.isFragmentAdded(fragment)) {
            getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
            getFragmentManager().executePendingTransactions();
        }
    }

}

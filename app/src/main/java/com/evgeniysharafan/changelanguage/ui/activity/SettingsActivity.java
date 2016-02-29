package com.evgeniysharafan.changelanguage.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.changelanguage.ui.fragment.SettingsFragment;
import com.evgeniysharafan.changelanguage.util.LocaleUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, SettingsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocaleUtils.updateAndGetConfiguration();

        setContentView(R.layout.activity_for_fragment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.content, SettingsFragment.newInstance(),
                    SettingsFragment.class.getSimpleName()).commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        invalidateOptionsMenu();

        SettingsFragment settingsFragment = (SettingsFragment) getFragmentManager().findFragmentById(R.id.content);
        if (settingsFragment != null && settingsFragment.isAdded()) {
            settingsFragment.getPreferenceScreen().removeAll();
            settingsFragment.addPreferencesFromResource(R.xml.preferences);
            getFragmentManager().beginTransaction().detach(settingsFragment).attach(settingsFragment).commit();
        }
    }
}

package com.evgeniysharafan.changelanguage.ui.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.changelanguage.util.LocaleUtils;
import com.evgeniysharafan.changelanguage.util.Prefs;
import com.evgeniysharafan.utils.Res;

import static android.preference.Preference.OnPreferenceChangeListener;

public class SettingsFragment extends PreferenceFragment implements OnPreferenceChangeListener {

    private ListPreference language;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // When system restores PreferenceFragment it sets the default language instead of the current one
        // even despite LocaleUtils.updateAndGetConfiguration(); in SettingsActivity's onCreate method.
        // That's why we need to call it here.
        if (savedInstanceState != null) {
            getActivity().onConfigurationChanged(LocaleUtils.updateAndGetConfiguration());
        }

        prepareActionBar();
        findPreferences();
        fillSummaries();

        return view;
    }

    private void prepareActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.action_settings);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void findPreferences() {
        language = (ListPreference) findPreference(Res.getString(R.string.key_language));
        language.setOnPreferenceChangeListener(this);
    }

    private void fillSummaries() {
        language.setSummary(language.getValue());
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (key.equals(language.getKey())) {
            String newLanguage = (String) newValue;
            if (!LocaleUtils.isCurrentLanguage(newLanguage)) {
                Prefs.setLanguage(((String) newValue));
                getActivity().onConfigurationChanged(LocaleUtils.updateAndGetConfiguration());

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

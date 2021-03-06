package com.evgeniysharafan.changelanguage.util;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.utils.Res;
import com.evgeniysharafan.utils.Utils;

import java.util.Locale;

public class LocaleUtils {

    private static final String ENGLISH = Res.getString(R.string.language_english);
    private static final String ENGLISH_LANGUAGE_CODE = Locale.ENGLISH.getLanguage();

    private static final String RUSSIAN = Res.getString(R.string.language_russian);
    private static final String RUSSIAN_LANGUAGE_CODE = "ru";

    private LocaleUtils() {
    }

    /**
     * Gets the current language from settings, sets it as default locale, updates configuration in resources,
     * returns resulting configuration for onConfigurationChanged(Configuration newConfig) method.
     * e.g. onConfigurationChanged(LocaleUtils.updateAndGetConfiguration());
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Configuration updateAndGetConfiguration() {
        Configuration configuration = new Configuration(Res.getConfiguration());
        String language = Prefs.getLanguage();

        if (ENGLISH.equals(language)) {
            configuration.locale = Locale.ENGLISH;
        } else if (RUSSIAN.equals(language)) {
            configuration.locale = new Locale(RUSSIAN_LANGUAGE_CODE);
        } else {
            throw new IllegalStateException("language " + language + " isn't handled");
        }

        if (Utils.hasJellyBeanMr1()) {
            configuration.setLayoutDirection(configuration.locale);
        }

        Locale.setDefault(configuration.locale);
        Res.updateConfiguration(configuration);

        return configuration;
    }

    /**
     * @param language name from settings. English, Spanish, etc.
     */
    public static boolean isCurrentLanguage(@NonNull String language) {
        return isCurrentLanguageCode(getLanguageCode(language));
    }

    /**
     * @param languageCode en, es, etc. e.g Locale.ENGLISH.getLanguage();
     */
    public static boolean isCurrentLanguageCode(@NonNull String languageCode) {
        return Res.getConfiguration().locale.getLanguage().equals(languageCode);
    }

    /**
     * Transforms language (English) to language code (en)
     */
    private static String getLanguageCode(@NonNull String language) {
        if (ENGLISH.equals(language)) {
            return ENGLISH_LANGUAGE_CODE;
        } else if (RUSSIAN.equals(language)) {
            return RUSSIAN_LANGUAGE_CODE;
        } else {
            throw new IllegalStateException("language " + language + " isn't handled");
        }
    }

}

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Configuration updateAndGetConfiguration() {
        Configuration configuration = new Configuration(Res.get().getConfiguration());
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
        Res.get().updateConfiguration(configuration, Res.getDisplayMetrics());

        return configuration;
    }

    public static boolean isCurrentLanguage(@NonNull String language) {
        return isCurrentLanguageCode(getLanguageCode(language));
    }

    public static boolean isCurrentLanguageCode(@NonNull String languageCode) {
        return Res.get().getConfiguration().locale.getLanguage().equals(languageCode);
    }

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

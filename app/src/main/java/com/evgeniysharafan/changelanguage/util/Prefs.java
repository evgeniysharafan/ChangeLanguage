package com.evgeniysharafan.changelanguage.util;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.utils.PrefUtils;
import com.evgeniysharafan.utils.Res;

public final class Prefs {

    private static final String LANGUAGE = Res.getString(R.string.key_language);

    private Prefs() {
    }

    public static String getLanguage() {
        return PrefUtils.getString(LANGUAGE, "");
    }

    public static void setLanguage(String exam) {
        PrefUtils.put(LANGUAGE, exam);
    }

}

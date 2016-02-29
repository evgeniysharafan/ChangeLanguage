# ChangeLanguage
Usually the current application language depends on current system language. This is a very good concept but sometimes customers want to change the current language from the application's settings. In this case we have to use 
crutches. 
This project shows how to implement it without a lot of pain.

In this example the current language changes instantly. Without reentering the screen or the app.
All useful code can be found in [LanguageActivity](app/src/main/java/com/evgeniysharafan/changelanguage/ui/activity/LanguageActivity.java), [SettingsActivity](app/src/main/java/com/evgeniysharafan/changelanguage/ui/activity/SettingsActivity.java), [SettingsFragment](app/src/main/java/com/evgeniysharafan/changelanguage/ui/fragment/SettingsFragment.java), and [LocaleUtils](app/src/main/java/com/evgeniysharafan/changelanguage/util/LocaleUtils.java).

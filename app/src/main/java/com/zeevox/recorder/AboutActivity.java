package com.zeevox.recorder;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    protected SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean mDarkTheme = this.preferences.getBoolean("key_app_theme", false);
        int i;
        if (mDarkTheme) {
            i = R.style.AppThemeBlack;
        } else {
            i = R.style.AppTheme;
        }
        setTheme(i);

        setContentView(R.layout.activity_about);
    }
}

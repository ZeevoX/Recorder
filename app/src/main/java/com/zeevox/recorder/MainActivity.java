package com.zeevox.recorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private final String KEY_THEME = "key_app_theme";
    private boolean mDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String buildType = BuildConfig.BUILD_TYPE;

        /*if (AppConfig.ENABLE_NEW_HOME) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }*/

        this.preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (buildType.equals("weekly") || buildType.equals("debug") || buildType.equals("beta")) {
            String KEY_LAST_BUILD_TYPE = "key_last_build_type";
            String lastBuildType = this.preferences.getString(KEY_LAST_BUILD_TYPE, "release");
            String KEY_SHOWN_NOTICE_FOR_BUILD_TYPE = "key_notice_for_build_type";
            boolean noticeShownForBuildType = this.preferences.getBoolean(KEY_SHOWN_NOTICE_FOR_BUILD_TYPE, false);
            if (!Objects.equals(lastBuildType, BuildConfig.BUILD_TYPE) && !noticeShownForBuildType) {
                this.preferences.edit().putBoolean(KEY_SHOWN_NOTICE_FOR_BUILD_TYPE, true).apply();
                this.preferences.edit().putString(KEY_LAST_BUILD_TYPE, BuildConfig.BUILD_TYPE).apply();
                startActivity(new Intent(MainActivity.this, NoticeActivity.class));
            }
        }

        this.mDarkTheme = this.preferences.getBoolean(KEY_THEME, false);

        //migration on removal of dark theme
        //TODO: Remove all Dark theme references in v0.3

        if (this.mDarkTheme) {
            this.mDarkTheme = false;
            this.preferences.edit().putBoolean(KEY_THEME, this.mDarkTheme).apply();
        }

        setTheme(this.mDarkTheme ? R.style.AppThemeBlack : R.style.AppTheme);

        setContentView(R.layout.activity_recorder);

        //TODO: UNTESTED: TEST ON SDK 26
        if (Build.VERSION.SDK_INT >= 26) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
        }

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecordingActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_theme).setTitle(this.mDarkTheme ? R.string.action_theme_light : R.string.action_theme_dark);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recorder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_theme:
                this.mDarkTheme = !this.mDarkTheme;
                this.preferences.edit().putBoolean(KEY_THEME, this.mDarkTheme).apply();
                finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(this, getClass()));
                overridePendingTransition(0, 0);
                break;

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;

            case R.id.action_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;

            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

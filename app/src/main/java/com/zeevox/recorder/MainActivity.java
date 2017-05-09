package com.zeevox.recorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    protected SharedPreferences preferences;
    private String KEY_THEME = "key_app_theme";
    private boolean mDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.mDarkTheme = this.preferences.getBoolean(KEY_THEME, false);
        int i;
        if (this.mDarkTheme) {
            i = R.style.AppThemeBlack;
        } else {
            i = R.style.AppTheme;
        }
        setTheme(i);

        setContentView(R.layout.activity_recorder);

        /*if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }*/

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startRecording(1, 44100, 96000, Environment.getExternalStorageDirectory() + File.separator + "Recordings" + File.separator + "Recording-1");
                startActivity(new Intent(MainActivity.this, RecordingActivity.class));
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

            case R.id.action_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

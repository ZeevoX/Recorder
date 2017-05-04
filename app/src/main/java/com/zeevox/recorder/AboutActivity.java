package com.zeevox.recorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    protected SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean mDarkTheme = this.preferences.getBoolean("key_app_theme", false);
        /*temp var*/int i;
        if (mDarkTheme) {/*Dark Theme*/i = R.style.AppThemeBlack;} else {/*LightTheme*/i = R.style.AppTheme;}

        //set theme before setcontentview
        setTheme(i);

        setContentView(R.layout.activity_about);

        //20170504 FEEDBACK BUTTON
        final Button feedbackButton = (Button) findViewById(R.id.buttonFeedback);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"incoming+ZeevoX/Recorder@gitlab.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Recorder Issue: ");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Issue:\nDevice:\nHow to reproduce this issue:\nYour name:");

                /* Send it off to the Activity-Chooser */
                getApplicationContext().startActivity(Intent.createChooser(emailIntent, "Send feedback via email..."));
            }
        });
    }
}

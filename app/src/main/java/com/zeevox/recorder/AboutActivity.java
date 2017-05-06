package com.zeevox.recorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        //FEEDBACK BUTTON
        final Button feedbackButton = (Button) findViewById(R.id.buttonFeedback);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set support email
                Uri uri = Uri.parse("mailto:incoming+ZeevoX/Recorder@gitlab.com");
                //set up intent
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                //fill intent with data
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT,
                        "Issue:\n\nSteps on how to reproduce this issue:\n\nYour name:\nYour email address:" +
                        "\n\n\n\n**Do NOT remove the information below!**\n\nApp Version Name: "+BuildConfig.VERSION_NAME+"\nBuild Timestamp (Unix): "+BuildConfig.TIMESTAMP+"\nBuild type: "+BuildConfig.BUILD_TYPE+"\nApp Version Code: "+BuildConfig.VERSION_CODE+"" +
                        "\n\nDevice model: "+ Build.MODEL + "\nDevice manufacturer: " + Build.MANUFACTURER + "\nDevice codename: " + Build.PRODUCT + "\nDevice Build Fingerprint: " + Build.FINGERPRINT + "\nAndroid version: " + Build.VERSION.RELEASE +"\nSDK number: " + Build.VERSION.SDK_INT);
                //launch default email client
                startActivity(emailIntent);
            }
        });

        final TextView timestampTextView = (TextView) findViewById(R.id.textTimeStamp);
        timestampTextView.setText(String.format("%s%s", getString(R.string.about_version_placeholder), BuildConfig.VERSION_NAME));
    }
}

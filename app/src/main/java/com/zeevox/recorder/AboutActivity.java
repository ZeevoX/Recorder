package com.zeevox.recorder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(preferences.getBoolean("key_app_theme", false) ? R.style.AppThemeBlack : R.style.AppTheme);

        setContentView(R.layout.activity_about);

        final ImageView recorderIcon = (ImageView) findViewById(R.id.aboutRecorderIcon);
        recorderIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(30);
                if (i == 7) {
                    startActivity(new Intent(AboutActivity.this, FeedbackActivity.class));
                    i = 0;
                } else {
                    i = i+1;
                }
                return false;
            }
        });
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

                //init feedback message
                String feedbackMessage = getString(R.string.feedback_prefill_content);

                //input data
                feedbackMessage = feedbackMessage.replace("version_name", BuildConfig.VERSION_NAME);
                feedbackMessage = feedbackMessage.replace("build_timestamp", Long.toString(BuildConfig.TIMESTAMP));
                feedbackMessage = feedbackMessage.replace("build_type", BuildConfig.BUILD_TYPE);
                //Set email intent
                emailIntent.putExtra(Intent.EXTRA_TEXT, feedbackMessage);
                /*emailIntent.putExtra(Intent.EXTRA_TEXT,
                        "Issue:\n\nSteps on how to reproduce this issue:\n\nYour name:\nYour email address:" +
                        "\n\n\n\n**Do NOT remove the information below!**\n\nApp Version Name: "+BuildConfig.VERSION_NAME+"\nBuild Timestamp (Unix): "+BuildConfig.TIMESTAMP+"\nBuild type: "+BuildConfig.BUILD_TYPE+"\nApp Version Code: "+BuildConfig.VERSION_CODE+"" +
                        "\n\nDevice model: "+ Build.MODEL + "\nDevice manufacturer: " + Build.MANUFACTURER + "\nDevice codename: " + Build.PRODUCT + "\nDevice Build Fingerprint: " + Build.FINGERPRINT + "\nAndroid version: " + Build.VERSION.RELEASE +"\nSDK number: " + Build.VERSION.SDK_INT);*/
                try {
                    //launch default email client
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException activityNotFoundException) {
                    //DIALOG TO SAY NO EMAIL CLIENT FOUND
                }
            }
        });

        ((TextView) findViewById(R.id.textTimeStamp)).setText(String.format("%s%s", new Object[]{getString(R.string.about_version_placeholder), BuildConfig.VERSION_NAME}));
        /*final TextView timestampTextView = (TextView) findViewById(R.id.textTimeStamp);
        timestampTextView.setText(String.format("%s%s", getString(R.string.about_version_placeholder), BuildConfig.VERSION_NAME));*/
    }
}

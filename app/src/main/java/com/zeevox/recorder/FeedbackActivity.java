package com.zeevox.recorder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FeedbackActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //FEEDBACK BUTTON
        final Button feedbackButton = findViewById(R.id.feedback_send_button);
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

    }
}

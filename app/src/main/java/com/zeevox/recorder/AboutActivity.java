package com.zeevox.recorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(preferences.getBoolean("key_app_theme", false) ? R.style.AppThemeBlack : R.style.AppTheme);

        setContentView(R.layout.activity_about);

        final ImageView recorderIcon = findViewById(R.id.aboutRecorderIcon);
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
        ((TextView) findViewById(R.id.textTimeStamp)).setText(String.format("%s%s", new Object[]{getString(R.string.about_version_placeholder), BuildConfig.VERSION_NAME}));
    }

    /*
    private void dialogLicences() {

        //Licences dialog, see https://github.com/PSDev/LicensesDialog
        final Notices notices = new Notices();
        notices.addNotice(new Notice("Recorder", "https://play.google.com/store/apps/details?id=com.zeevox.recorder", "Copyright (c) 2016-2017 Timothy \"ZeevoX\" Langer", new MITLicense()));
        //notices.addNotice(new Notice("MaterialDialogs API", "https://github.com/afollestad/material-dialogs", "Copyright (c) 2014-2016 Aidan Michael Follestad", new MITLicense()));
        //notices.addNotice(new Notice("MaterialTapTargetPrompt", "https://github.com/sjwall/MaterialTapTargetPrompt", "Copyright (c) 2016 Samuel Wall", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Android Support Library", "https://developer.android.com/topic/libraries/support-library/index.html","Copyright (C) 2011 The Android Open Source Project", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Android Design Support Library", "https://developer.android.com/training/material/design-library.html", "Copyright (C) 2011 The Android Open Source Project", new ApacheSoftwareLicense20()));

        new LicensesDialog.Builder(this)
                .setNotices(notices)
                .setIncludeOwnLicense(true)
                .build()
                .show();
    }
     */
}

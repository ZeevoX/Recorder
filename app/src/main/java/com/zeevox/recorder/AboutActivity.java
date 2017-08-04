package com.zeevox.recorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView versionNameText = findViewById(R.id.textVersionName);
        String versionName = getString(R.string.about_version_placeholder) + BuildConfig.VERSION_NAME;
        versionNameText.setText(versionName);
        TextView openSourceLicencesText = findViewById(R.id.openSourceButton);
        openSourceLicencesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLicences();
            }
        });
    }

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
}

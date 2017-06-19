package com.zeevox.recorder;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Add a content view from layout to add a toolbar to the Preferences screen.
        setContentView(R.layout.activity_settings);
        //Inflate the list with preferences from our xml file.
        addPreferencesFromResource(R.xml.preferences_settings_activity);
        //Handle taps or clicks on various preferences.
        for(int x = 0; x < getPreferenceScreen().getPreferenceCount(); x++){
            PreferenceCategory lol = (PreferenceCategory) getPreferenceScreen().getPreference(x);
            for(int y = 0; y < lol.getPreferenceCount(); y++){
                Preference pref = lol.getPreference(y);
                pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        String key = preference.getKey();
                        switch (key) {
                            case "key_app_copyright":
                                dialogLicences();
                                break;
                        }
                        return false;
                    }
                });
            }
        }
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

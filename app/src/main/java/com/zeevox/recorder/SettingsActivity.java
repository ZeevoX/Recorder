package com.zeevox.recorder;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Add a content view from layout to add a toolbar to the Preferences screen.
        setContentView(R.layout.activity_settings);
        //Inflate the list with preferences from our xml file.
        addPreferencesFromResource(R.xml.preferences_settings_activity);
        //Handle taps or clicks on various preferences.
    /*for(int x = 0; x < getPreferenceScreen().getPreferenceCount(); x++){
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
    }*/
    }
}

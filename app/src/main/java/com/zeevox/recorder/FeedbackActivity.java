package com.zeevox.recorder;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);



        final EditText issueTitleInput = findViewById(R.id.feedback_issue_input);
        final EditText issueReproduceInput = findViewById(R.id.feedback_issue_reproduce_input);
        final EditText expectedResultInput = findViewById(R.id.feedback_expected_result);
        final EditText actualResultInput = findViewById(R.id.feedback_actual_result);
        final TextInputLayout issueTitleInputLayout = findViewById(R.id.feedback_issue_input_layout);
        final TextInputLayout issueReproduceInputLayout = findViewById(R.id.feedback_issue_reproduce_input_layout);
        final TextInputLayout expectedResultInputLayout = findViewById(R.id.feedback_expected_result_layout);
        final TextInputLayout actualResultInputLayout = findViewById(R.id.feedback_actual_result_layout);

        issueTitleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    //IF/ELSE to prevent no title if user deletes all characters
                    if (issueTitleInput.getText().toString().isEmpty()) {
                        getSupportActionBar().setTitle(R.string.action_send_feedback);
                        issueTitleInputLayout.setError(getString(R.string.feedback_error_empty));
                        requestFocus(issueTitleInputLayout);
                    } else {
                        getSupportActionBar().setTitle(issueTitleInput.getText().toString());
                        issueReproduceInputLayout.setErrorEnabled(false);
                    }
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }
        });

        issueReproduceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (issueReproduceInput.getText().toString().isEmpty()) {
                    issueReproduceInputLayout.setError(getString(R.string.feedback_error_empty));
                    requestFocus(issueReproduceInputLayout);
                } else {
                    issueReproduceInputLayout.setErrorEnabled(false);
                }
            }
        });
        expectedResultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (expectedResultInput.getText().toString().isEmpty()) {
                    expectedResultInputLayout.setError(getString(R.string.feedback_error_empty));
                    requestFocus(expectedResultInputLayout);
                } else {
                    expectedResultInputLayout.setErrorEnabled(false);
                }
            }
        });
        actualResultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (actualResultInput.getText().toString().isEmpty()) {
                    actualResultInputLayout.setError(getString(R.string.feedback_error_empty));
                    requestFocus(actualResultInputLayout);
                } else {
                    actualResultInputLayout.setErrorEnabled(false);
                }
            }
        });

        //FEEDBACK BUTTON
        final Button feedbackButton = findViewById(R.id.feedback_send_button);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set who to send email to
                Uri uri = Uri.parse("mailto:zeevox.dev@gmail.com");
                //Set up email intent
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);

                //Init feedback message
                String feedbackMessage = getString(R.string.feedback_prefill_content);

                //Set email title
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, issueTitleInput.getText().toString());

                //Get reproduction steps
                String issueReproduce = issueReproduceInput.getText().toString();
                //Replace placeholder with data
                feedbackMessage = feedbackMessage.replace("REPRODUCTION_STEPS", issueReproduce);

                //Get expected result
                String expectedResult = expectedResultInput.getText().toString();
                //Replace placeholder with data
                feedbackMessage = feedbackMessage.replace("EXPECTED_RESULT", expectedResult);

                //Get actual result
                String actualResult = actualResultInput.getText().toString();
                //Replace placeholder with data
                feedbackMessage = feedbackMessage.replace("ACTUAL_RESULT", actualResult);

                //Convert Unix timestamp to human-readable date
                Date date = new Date(BuildConfig.TIMESTAMP);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                String timestampFormatted = simpleDateFormat.format(date);

                //Replace device info placeholders with data
                feedbackMessage = feedbackMessage.replace("recorder_version_name", BuildConfig.VERSION_NAME);
                feedbackMessage = feedbackMessage.replace("device_fingerprint", Build.FINGERPRINT);
                feedbackMessage = feedbackMessage.replace("build_timestamp", timestampFormatted);

                //Set email intent
                emailIntent.putExtra(Intent.EXTRA_TEXT, feedbackMessage);

                CheckBox checkNoDublicate = findViewById(R.id.check_no_duplicate);
                CheckBox checkLatestVersion = findViewById(R.id.check_latest_version);
                CheckBox checkGenericTitle = findViewById(R.id.check_generic_title);
                CheckBox checkDeviceInfo = findViewById(R.id.check_device_info);

                if (checkNoDublicate.isChecked() && checkLatestVersion.isChecked() && checkGenericTitle.isChecked() && checkDeviceInfo.isChecked()) {
                    try {
                        //Start default email client
                        startActivity(emailIntent);
                    } catch (ActivityNotFoundException activityNotFoundException) {
                        //TODO: DIALOG TO SAY NO EMAIL CLIENT FOUND
                    }
                } else {
                    dialogInvalid();
                }
            }


        });
    }

    public void dialogInvalid() {
        new MaterialDialog.Builder(this)
                .title("Error")
                .content("Please make sure you have checked all the boxes")
                .positiveText("OK")
                .titleColor(getResources().getColor(R.color.colorAccent))
                .autoDismiss(true)
                .show();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}

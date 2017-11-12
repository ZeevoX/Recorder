package com.zeevox.recorder;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.io.IOException;

public class RecordingActivity extends AppCompatActivity {

    private static final int REQUEST_MULTIPLE_PERMISSIONS_ID = 456;
    private final String KEY_RECORDING_CHANNELS = "recording_channels";
    private final String KEY_RECORDING_SAMPLE_RATE = "recording_sample_rate";
    // --Commented out by Inspection (19/06/2017 14:25):final String KEY_RECORDING_FORMAT = "recording_format";
    //Setup MediaRecorder
    private final MediaRecorder recorder = new MediaRecorder();
    //Temporary file path
    private final String temporaryRecordingFilePath =
            Environment.getExternalStorageDirectory() + File.separator + "temp.tmp";
    //Setup SharedPreferences
    //protected SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    private SharedPreferences sharedPreferences;
    //Variable from settings. how many samples per second.
    private int sampleRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recording);

    /*if (ContextCompat.checkSelfPermission(RecordingActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED && ContextCompat.checkSelfPermission(RecordingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED && ContextCompat.checkSelfPermission(RecordingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
        try {
            startRecording();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        try {
            permissionsCheckRecording();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //EncoderInfo encoderInfo = new EncoderInfo(2, 44100, 16);
    /*EncoderInfo encoderInfo = getInfo();
    String recordingFormat = sharedPreferences.getString(KEY_RECORDING_FORMAT, "m4a");
    FormatM4A formatM4A = new FormatM4A(encoderInfo, Environment.getExternalStorageDirectory());*/

        String KEY_KEEP_SCREEN_ON = "keep_screen_on";
        String KEY_SHOW_ON_LOCKSCREEN = "show_on_lockscreen";

        if (sharedPreferences.getBoolean(KEY_KEEP_SCREEN_ON, true) /*check if to keep screen on*/) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        } else if (sharedPreferences.getBoolean(
                KEY_SHOW_ON_LOCKSCREEN, true /* check from settings if tto show on the lockscreen */)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        }

    /* getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON); */

        final FloatingActionButton stop = findViewById(R.id.recording_pause_resume);
        stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(RecordingActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    private void startRecording() throws IOException {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(temporaryRecordingFilePath);
        recorder.prepare();
        recorder.start(); // Recording is now started
    }

    public void stopRecording() {
        recorder.stop();
        recorder.reset(); // You can reuse the object by going back to setAudioSource() step
        recorder.release(); // Now the object cannot be reused
    }

    public void permissionsCheckRecording() throws IOException {

        int permissionStorageWrite =
                ContextCompat.checkSelfPermission(
                        RecordingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionStorageRead =
                ContextCompat.checkSelfPermission(
                        RecordingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionRecordAudio =
                ContextCompat.checkSelfPermission(RecordingActivity.this, Manifest.permission.RECORD_AUDIO);

        if (permissionRecordAudio == PackageManager.PERMISSION_DENIED
                || permissionStorageRead == PackageManager.PERMISSION_DENIED
                || permissionStorageWrite == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    RecordingActivity.this, Manifest.permission.RECORD_AUDIO)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    RecordingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    RecordingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                //TODO: Show permission explanation
                new MaterialDialog.Builder(this)
                        .content(R.string.dialog_permissions_record_content)
                        .positiveText(R.string.action_ok)
                        .onPositive(
                                new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        permissionsRequestRecording();
                                    }
                                })
                        .show();

            } else {

                //Request needed permissions
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },
                        REQUEST_MULTIPLE_PERMISSIONS_ID);
            }
        } else {
            startRecording();
        }
    }

    public void permissionsRequestRecording() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                },
                REQUEST_MULTIPLE_PERMISSIONS_ID);
    }

  /*public void dialogRename() {
      Calendar c = Calendar.getInstance();
      int year = c.get(Calendar.YEAR);
      int i_month = c.get(Calendar.MONTH);
      int month = i_month + 1;
      int day = c.get(Calendar.DAY_OF_MONTH);
      int hour = c.get(Calendar.HOUR_OF_DAY);
      int minute = c.get(Calendar.MINUTE);
      int second = c.get(Calendar.SECOND);

      final String fileNameDefault = "Recording " + year + "" + month + "" + day + " " + hour + "" + minute + "" + second;
      final String[] name = {""};
      new MaterialDialog.Builder(this)
              .title(R.string.dialog_rename_file_title)
              .content(R.string.dialog_rename_file_content)
              .alwaysCallInputCallback()
              .inputRangeRes(1, 60, R.color.colorAccent)
              .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
              .input("Enter a filename...", fileNameDefault, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                      //PREVENT NULL FILENAME
                      if (input.toString().isEmpty()) {
                          dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                      } else {
                          dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                      }
                      if (input.length() > 60) {
                          dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                      } else {
                          dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                      }
                      name[0] = input.toString();
                  }
              })
              .positiveText(R.string.action_save)
              .canceledOnTouchOutside(false)
              .onPositive(new MaterialDialog.SingleButtonCallback() {
                  @Override
                  public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                      Log.d("Dialog", "positive");
                      File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Recordings");
                      directory.mkdirs();
                      String userInput = name[0];
                      Log.d("File", userInput);
                      File temp = new File(Environment.getExternalStorageDirectory() + File.separator, "temp.tmp");
                      File dest = new File(Environment.getExternalStorageDirectory() + File.separator + "Recordings" + File.separator, userInput + ".3gpp");
                      temp.renameTo(dest);
                  }
              })
              .negativeText(R.string.action_delete)
              .onNegative(new MaterialDialog.SingleButtonCallback() {
                  @Override
                  public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                      Log.d("Dialog", "negative");
                      //DELETES THE TEMP FILE
                      File file = new File(Environment.getExternalStorageDirectory() + File.separator, "temp.tmp");
                      file.delete();
                  }
              })
              .show();
  }*/

}

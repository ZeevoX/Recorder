package com.zeevox.recorder.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class AndroidAudioRecorder implements AudioRecorder {
  /** the audio track we read samples from * */
  private AudioRecord recorder;

  public AndroidAudioRecorder(int samplingRate, boolean isMono) {
    int minBufferSize =
        AudioRecord.getMinBufferSize(
            samplingRate,
            isMono
                ? AudioFormat.CHANNEL_CONFIGURATION_MONO
                : AudioFormat.CHANNEL_CONFIGURATION_STEREO,
            AudioFormat.ENCODING_PCM_16BIT);
    recorder =
        new AudioRecord(
            MediaRecorder.AudioSource.MIC,
            samplingRate,
            isMono
                ? AudioFormat.CHANNEL_CONFIGURATION_MONO
                : AudioFormat.CHANNEL_CONFIGURATION_STEREO,
            AudioFormat.ENCODING_PCM_16BIT,
            minBufferSize);
    recorder.startRecording();
  }

  @Override
  public void dispose() {
    recorder.stop();
    recorder.release();
  }

  @Override
  public void read(short[] samples, int offset, int numSamples) {
    int read = 0;
    while (read != numSamples) {
      read += recorder.read(samples, offset + read, numSamples - read);
    }
  }
}

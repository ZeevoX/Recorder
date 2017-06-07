package com.zeevox.recorder2.encoders;

public class EncoderInfo {
    final int channels;
    final int sampleRate;
    final int bps;

    public EncoderInfo(int channels, int sampleRate, int bps) {
        this.channels = channels;
        this.sampleRate = sampleRate;
        this.bps = bps;
    }
}

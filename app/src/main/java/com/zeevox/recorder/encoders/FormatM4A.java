package com.zeevox.recorder.encoders;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;

import java.io.File;
import java.util.Map;

// --Commented out by Inspection START (19/06/2017 14:25):
//@TargetApi(21)
//class FormatM4A extends MuxerMP4 {
//
//// --Commented out by Inspection START (19/06/2017 14:25):
////    public FormatM4A(EncoderInfo info, File out) {
////        Map<String, MediaCodecInfo> map = MuxerMP4.findEncoder();
////        if (map.isEmpty())
////            throw new RuntimeException("mp4 not supported");
////        MediaFormat format = MuxerMP4.getDefault(map);
////        format.setInteger(MediaFormat.KEY_SAMPLE_RATE, info.sampleRate);
////        format.setInteger(MediaFormat.KEY_CHANNEL_COUNT, info.channels);
////        format.setInteger(MediaFormat.KEY_BIT_RATE, 64000);
// --Commented out by Inspection STOP (19/06/2017 14:25)
//
//        create(info, format, out);
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)
}

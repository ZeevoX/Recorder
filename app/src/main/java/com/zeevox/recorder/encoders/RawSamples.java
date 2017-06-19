package com.zeevox.recorder.encoders;

import android.media.AudioFormat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public class RawSamples {
    public static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

// --Commented out by Inspection START (19/06/2017 14:25):
//    // quite root gives me 20db
//    public static int NOISE_DB = 20;
// --Commented out by Inspection STOP (19/06/2017 14:25)
// --Commented out by Inspection START (19/06/2017 14:25):
//    // max 90 dB detection for android mic
//    public static int MAXIMUM_DB = 90;
// --Commented out by Inspection STOP (19/06/2017 14:25)

    private final File in;

    private InputStream is;
    private byte[] readBuffer;

    private OutputStream os;

// --Commented out by Inspection START (19/06/2017 14:25):
//    RawSamples(File in) {
//        this.in = in;
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

// --Commented out by Inspection START (19/06/2017 14:25):
//    // open for writing with specified offset to truncate file
//    public void open(long writeOffset) {
//        trunk(writeOffset);
//        try {
//            os = new BufferedOutputStream(new FileOutputStream(in, true));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

// --Commented out by Inspection START (19/06/2017 14:25):
//    // open for reading
//    //
//    // bufReadSize - samples count
//    void open(int bufReadSize) {
//        try {
//            readBuffer = new byte[(int) getBufferLen(bufReadSize)];
//            is = new FileInputStream(in);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

// --Commented out by Inspection START (19/06/2017 14:25):
//    // open for read with initial offset and buffer read size
//    //
//    // offset - samples offset
//    // bufReadSize - samples size
//    public void open(long offset, int bufReadSize) {
//        try {
//            readBuffer = new byte[(int) getBufferLen(bufReadSize)];
//            is = new FileInputStream(in);
//            is.skip(offset * (AUDIO_FORMAT == AudioFormat.ENCODING_PCM_16BIT ? 2 : 1));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

// --Commented out by Inspection START (19/06/2017 14:25):
//    int read(short[] buf) {
//        try {
//            int len = is.read(readBuffer);
//            if (len <= 0)
//                return 0;
//            ByteBuffer.wrap(readBuffer, 0, len).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(buf, 0, (int) getSamples(len));
//            return (int) getSamples(len);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

    private void write(short val) {
        try {
            ByteBuffer bb = ByteBuffer.allocate(Short.SIZE / Byte.SIZE);
            bb.order(ByteOrder.BIG_ENDIAN);
            bb.putShort(val);
            os.write(bb.array(), 0, bb.limit());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

// --Commented out by Inspection START (19/06/2017 14:25):
//    public void write(short[] buf) {
//        for (short aBuf : buf) {
//            write(aBuf);
//        }
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

// --Commented out by Inspection START (19/06/2017 14:25):
//    long getSamples() {
//        return getSamples(in.length());
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

    private static long getSamples(long len) {
        return len / (AUDIO_FORMAT == AudioFormat.ENCODING_PCM_16BIT ? 2 : 1);
    }

    private static long getBufferLen(long samples) {
        return samples * (AUDIO_FORMAT == AudioFormat.ENCODING_PCM_16BIT ? 2 : 1);
    }

    private void trunk(long pos) {
        try {
            FileChannel outChan = new FileOutputStream(in, true).getChannel();
            outChan.truncate(getBufferLen(pos));
            outChan.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double getAmplitude(short[] buffer, int offset, int len) {
        double sum = 0;
        for (int i = offset; i < offset + len; i++) {
            sum += buffer[i] * buffer[i];
        }
        return Math.sqrt(sum / len);
    }

// --Commented out by Inspection START (19/06/2017 14:25):
//    public static double getDB(short[] buffer, int offset, int len) {
//        return getDB(getAmplitude(buffer, offset, len));
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

    private static double getDB(double amplitude) {
        // https://en.wikipedia.org/wiki/Sound_pressure
        return 20.0 * Math.log10(amplitude / 0x7FFF);
    }

// --Commented out by Inspection START (19/06/2017 14:25):
//    void close() {
//        try {
//            if (is != null)
//                is.close();
//            is = null;
//
//            if (os != null)
//                os.close();
//            os = null;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)
}

package com.zeevox.recorder.encoders;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// --Commented out by Inspection START (19/06/2017 14:25):
//public class FormatWAV implements Encoder {
//    private int NumSamples;
//    private final EncoderInfo info;
//    private final int BytesPerSample;
//    private RandomAccessFile outFile;
//
//    private final ByteOrder order = ByteOrder.LITTLE_ENDIAN;
//
//// --Commented out by Inspection START (19/06/2017 14:25):
////    public FormatWAV(EncoderInfo info, File out) {
////        this.info = info;
////        NumSamples = 0;
////
////        BytesPerSample = info.bps / 8;
////
////        try {
////            outFile = new RandomAccessFile(out, "rw");
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////
////        save();
////    }
//// --Commented out by Inspection STOP (19/06/2017 14:25)
//
//    private void save() {
//        int SubChunk1Size = 16;
//        int SubChunk2Size = NumSamples * info.channels * BytesPerSample;
//        int ChunkSize = 4 + (8 + SubChunk1Size) + (8 + SubChunk2Size);
//
//        write("RIFF");
//        write(ChunkSize, order);
//        write("WAVE");
//
//        int ByteRate = info.sampleRate * info.channels * BytesPerSample;
//        short AudioFormat = 1; // PCM = 1 (i.e. Linear quantization)
//        int BlockAlign = BytesPerSample * info.channels;
//
//        write("fmt ");
//        write(SubChunk1Size, order);
//        write(AudioFormat, order); //short
//        write((short) info.channels, order); // short
//        write(info.sampleRate, order);
//        write(ByteRate, order);
//        write((short)BlockAlign, order); // short
//        write((short)info.bps, order); // short
//
//        write("data");
//        write(SubChunk2Size, order);
//    }
//
//    private void write(String str) {
//        try {
//            byte[] cc = str.getBytes("UTF-8");
//            ByteBuffer bb = ByteBuffer.allocate(cc.length);
//            bb.order(ByteOrder.BIG_ENDIAN);
//            bb.put(cc);
//            bb.flip();
//
//            outFile.write(bb.array());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void write(int i, ByteOrder order) {
//        ByteBuffer bb = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
//        bb.order(order);
//        bb.putInt(i);
//        bb.flip();
//
//        try {
//            outFile.write(bb.array());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void write(short i, ByteOrder order) {
//        ByteBuffer bb = ByteBuffer.allocate(Short.SIZE / Byte.SIZE);
//        bb.order(order);
//        bb.putShort(i);
//        bb.flip();
//
//        try {
//            outFile.write(bb.array());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void encode(short[] buf, int buflen) {
//        NumSamples += buflen / info.channels;
//        try {
//            ByteBuffer bb = ByteBuffer.allocate(buflen * (Short.SIZE / Byte.SIZE));
//            bb.order(order);
//            for (int i = 0; i < buflen; i++)
//                bb.putShort(buf[i]);
//            bb.flip();
//            outFile.write(bb.array());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void end() {
//        try {
//            outFile.seek(0);
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void close() {
//        try {
//            outFile.close();
//        } catch (IOException e) {
// --Commented out by Inspection STOP (19/06/2017 14:25)
            throw new RuntimeException(e);
        }
    }

// --Commented out by Inspection START (19/06/2017 14:25):
//    public EncoderInfo getInfo() {
//        return info;
//    }
// --Commented out by Inspection STOP (19/06/2017 14:25)

}
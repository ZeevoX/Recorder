package com.zeevox.recorder2.encoders;

interface Encoder {
    void encode(short[] buf, int len);
    void end(); // flush stream. may throw state exceptions
    void close(); // release native resources, should not throw exceptions
}

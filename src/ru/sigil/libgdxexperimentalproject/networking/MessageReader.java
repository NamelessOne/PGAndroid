package ru.sigil.libgdxexperimentalproject.networking;

import android.util.Log;

import java.io.BufferedInputStream;
import java.nio.ByteBuffer;

public class MessageReader {
    private int offset = 0;
    private byte[] data = new byte[0];

    public int readInt(BufferedInputStream dis) {
        offset += 4;
        int res = 0;
        byte[] b = new byte[4];
        try {
            dis.read(b);
            res = ((0xFF & b[0]) << 24) | ((0xFF & b[1]) << 16) |
                    ((0xFF & b[2]) << 8) | (0xFF & b[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public byte readByte(BufferedInputStream dis) {
        offset += 1;
        byte[] b  = new byte[1];
        try {
           dis.read(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b[0];
    }

    public String readString(BufferedInputStream dis) {
        String s = "";
        try {
            int strLength = readInt(dis);
            Log.v("rstringlength", String.valueOf(strLength));
            offset += strLength;
            byte[] b = new byte[strLength];
            dis.read(b);
            s = new String(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public byte[] readByteArray(BufferedInputStream dis) {
        int length = readInt(dis);
        ByteBuffer bb = ByteBuffer.allocate(length);
        //Переписать под использование ByteBuffer
        Log.v("Byte array length mr", String.valueOf(length));
        for (int i = 0; i < length; i++) {
            try {
                bb.put((byte)dis.read());
            } catch (Exception e) {
                Log.v("FAIL!!!1111", String.valueOf(i));
                e.printStackTrace();
            }
        }
        return bb.array();
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

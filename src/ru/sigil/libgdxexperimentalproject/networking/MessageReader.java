package ru.sigil.libgdxexperimentalproject.networking;

import android.util.Log;

import java.io.BufferedInputStream;
import java.nio.ByteBuffer;

public class MessageReader {
    public final int BUFFER_SIZE = 4096;
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
        int iter = length / BUFFER_SIZE;
        int tail = length % BUFFER_SIZE;
        ByteBuffer bb = ByteBuffer.allocate(length);
        //Переписать под использование ByteBuffer
        //byte[] b2 = new byte[tail];
        Log.v("Byte array length mr", String.valueOf(length));
        for (int i = 0; i < length; i++) {
            try {
                //byte[] b = new byte[BUFFER_SIZE];
                bb.put((byte)dis.read());
            } catch (Exception e) {
                Log.v("FAIL!!!1111", String.valueOf(i));
                e.printStackTrace();
            }
        }
        try {
            //dis.read(b2);
            //bb.put(b2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bb.array();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private byte[] merge(final byte[]... arrays) {
        int size = 0;
        for (byte[] a : arrays)
            size += a.length;
        byte[] res = new byte[size];
        int destinationPos = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (i > 0)
                destinationPos += arrays[i - 1].length;
            int length = arrays[i].length;
            System.arraycopy(arrays[i], 0, res, destinationPos, length);
        }
        return res;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

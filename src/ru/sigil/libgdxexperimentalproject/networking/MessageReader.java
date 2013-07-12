package ru.sigil.libgdxexperimentalproject.networking;

import android.util.Log;

import java.io.DataInputStream;

public class MessageReader {
    private int offset = 0;
    private byte[] data = new byte[0];

    public int readInt(DataInputStream dis) {
        offset += 4;
        int res = 0;
        try {
            res = dis.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public byte readByte(DataInputStream dis) {
        offset += 1;
        byte b = 0;
        try {
            b = dis.readByte();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public String readString(DataInputStream dis) {
        String s = "";
        try {
            int strLength = readInt(dis);
            Log.v("rstringlength", String.valueOf(strLength));
            offset += strLength;
            byte[] b = new byte[strLength];
            for (int i = 0; i < strLength; i++) {
                dis.read(b);
            }
            s = new String(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public byte[] readByteArray(DataInputStream dis) {
        int length = readInt(dis);
        byte[] b = new byte[length];
        try {
            dis.read(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
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

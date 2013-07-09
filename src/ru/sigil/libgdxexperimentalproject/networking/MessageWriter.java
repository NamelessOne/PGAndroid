package ru.sigil.libgdxexperimentalproject.networking;

import java.nio.ByteBuffer;

public class MessageWriter {
    public byte[] data = new byte[0];

    public void writeInt(int message) {
        byte[] b = ByteBuffer.allocate(4).putInt(message).array();
        data = merge(data, b);
    }

    public void writeByte(byte message) {
        byte[] b = new byte[1];
        b[0] = message;
        data = merge(data, b);
    }

    public void writeString(String message) {
        writeInt(message.length());
        data = merge(data, message.getBytes());
    }

    public void writeByteArray(byte[] message) {
        data = merge(data, message);
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
}

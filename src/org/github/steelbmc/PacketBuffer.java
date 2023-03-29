package org.github.steelbmc;

import java.nio.ByteBuffer;

public class PacketBuffer {
    public DynamicByteBuffer dbb;

    public PacketBuffer(int size) {
        dbb = new DynamicByteBuffer(size);
    }

    public PacketBuffer() {
        this(1024);
    }

    public int readByte() {
        byte b = dbb.read();
        return b & 0xff;
    }

    public byte readSByte() {
        return dbb.read();
    }

    public float readFByte() {
        byte b = dbb.read();
        int integerpart = b & 0xE0;
        float floatingpart = 1.0f / (b & 0x05);
        return integerpart + floatingpart;
    }

    public int readShort() {
        return -1;
    }

    public float readFShort() {
        return 0.0f;
    }

    public String readString() {
        return "";
    }

    public byte[] readByteArray() {
        return null;
    }

    public void writeByte(int b) {

    }

    public void writeSByte(byte b) {

    }

    public void writeFByte(float b) {

    }

    public void writeShort(int s) {

    }

    public void writeFShort(float s) {

    }

    public void writeString(String s) {

    }

    public void writeByteArray(byte[] arr) {

    }

    public class DynamicByteBuffer {
        public ByteBuffer buf;

        public DynamicByteBuffer(int size) {
            buf = ByteBuffer.allocate(size);
        }

        public void write(byte b) {
            if(!buf.hasRemaining()) {
                reallocate();
            }
           buf.put(b);
        }

        public byte read() {
            return buf.get();
        }

        private void reallocate() {
            int len = 2 * buf.capacity();
            int oldPos = buf.position();
            buf.position(0);
            ByteBuffer temp = ByteBuffer.allocate(len);
            temp.put(buf);
            temp.position(oldPos);
            buf = temp;
        }
    }
}

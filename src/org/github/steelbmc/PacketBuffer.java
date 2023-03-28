package org.github.steelbmc;

import java.nio.ByteBuffer;

public class PacketBuffer {
    public DynamicByteBuffer dbb;

    public PacketBuffer(int size) {
        dbb = new DynamicByteBuffer(size);
    }

    public PacketBuffer() {
        dbb = new DynamicByteBuffer(1024);
    }

    public int readByte() {
        return -1;
    }

    public byte readSByte() {
        return -1;
    }

    public float readFByte() {
        return 0.0f;
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
            ByteBuffer temp = ByteBuffer.allocate(len);
            temp.put(buf);
            buf = temp;
        }
    }
}

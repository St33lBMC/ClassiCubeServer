package org.github.steelbmc;

public class Main {
    public static void main(String args[]) {
        /*CCServer ccServer = new CCServer();
        try {
            ccServer.start(11077);
        } catch(Exception e) {
            System.out.println(e);
        }*/

        /* testing dynamic byte buffer
        PacketBuffer p = new PacketBuffer(3);
        p.dbb.write((byte)8);
        p.dbb.write((byte)4);
        p.dbb.write((byte)2);
        p.dbb.write((byte)2);
        for(int i = 0; i < p.dbb.buf.capacity(); i++) {
            System.out.print(" " + p.dbb.buf.get(i));
        }*/

        /*testing pb gets and writes*/
        PacketBuffer p = new PacketBuffer(1);
        p.dbb.write((byte) 0xff);
        System.out.println(p.readFByte());
    }
}

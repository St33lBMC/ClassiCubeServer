package org.github.steelbmc;

public class Main {
    public static void main(String args[]) {
        /*CCServer ccServer = new CCServer();
        try {
            ccServer.start(11077);
        } catch(Exception e) {
            System.out.println(e);
        }*/
        PacketBuffer p = new PacketBuffer(3);
        p.dbb.write((byte)65);
        p.dbb.write((byte)120);
        p.dbb.write((byte)120);
        p.dbb.write((byte)120);
        for(int i = 0; i < p.dbb.buf.capacity(); i++) {
            System.out.print(" " + p.dbb.buf.get(i));
        }
    }
}

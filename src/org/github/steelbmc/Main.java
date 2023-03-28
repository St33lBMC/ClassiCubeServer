package org.github.steelbmc;

public class Main {
    public static void main(String args[]) {
        CCServer ccServer = new CCServer();
        try {
            ccServer.start(25565);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

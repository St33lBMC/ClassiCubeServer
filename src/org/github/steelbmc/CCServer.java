package org.github.steelbmc;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CCServer {
    public Selector selector;
    public ServerSocketChannel serverSocket;
    public SelectionKey selectionKey;

    public void start(int port) throws java.io.IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", port));
        serverSocket.configureBlocking(false);
        selectionKey = serverSocket.register(selector, serverSocket.validOps(), null);
        log("Server has started");

        //server loop
        while(true) {

            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();

            while(iter.hasNext()) {
                SelectionKey key = iter.next();
                if(key.isAcceptable()) {
                    SocketChannel ccClient = serverSocket.accept();
                    ccClient.configureBlocking(false);
                    ccClient.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + ccClient.getLocalAddress());

                } else if(key.isReadable()) {
                    SocketChannel ccClient = (SocketChannel) key.channel();
                    ByteBuffer in = ByteBuffer.allocate(256);
                    if(ccClient.read(in) != -1) {
                        String result = new String(in.array()).trim();
                        log("Message recieved: " + result);
                        String reply = "";
                        for(int i = 0; i < result.length(); i++) {
                            reply = result.charAt(i) + reply;
                        }
                        Scanner input = new Scanner(System.in);
                        reply = input.nextLine();
                        ccClient.write(ByteBuffer.wrap(reply.getBytes("UTF-8")));
                    } else {
                        log("EOF recieved");
                        ccClient.close();
                    }
                }
                iter.remove();
            }
        }
    }

    public void log(String msg) {
        System.out.println(msg);
    }

}

package main.java.netty.NIO;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SELECTOR {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_READ);
        int readyCount = selector.select(100);
    }
}

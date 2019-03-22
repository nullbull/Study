package netty.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * 建立SocketChannel链接
 */
public class ConnectAsync {
    public static void main(String[] args) throws IOException {
        String host = "www.baidu.com";
        int port = 80;
        InetSocketAddress addr = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();

        sc.configureBlocking(false);
        sc.connect(addr);
        System.out.println("initiating connection");
        while (!sc.finishConnect()) {
            System.out.println("zwt mylove");
        }
        System.out.println("connection establish");
        sc.close();
    }
}

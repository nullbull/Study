package main.java.netty.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelAccept {

    public static final String GEETING = "Hello I must be going.\r\n";

    public static void main(String[] args) throws Exception{
        int port = 1234;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        ByteBuffer buffer = ByteBuffer.wrap(GEETING.getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        while (true) {
            System.out.println("waiting for connections");
            SocketChannel sc = ssc.accept();
            if (sc == null) {
                Thread.sleep(2000);
            } else {
                System.out.println("Incoing connection from: " + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }
    }
}

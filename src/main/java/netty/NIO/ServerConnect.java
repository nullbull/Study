package netty.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerConnect {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8088;
    private static final int TIME_OUT = 3000;

    public static void main(String[] args) {
        selector();
    }
    public static void handleAccept(SelectionKey key)throws IOException{
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
    }
    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        if (sc.read(buf) > 0) {
            buf.flip();
            CharBuffer cb = CharsetHelper.decode(buf);
            System.out.println(cb.toString());
            String answer = "I Love ZWT";
            sc.write(ByteBuffer.wrap(answer.getBytes()));
//            sc.write(CharsetHelper.encode(CharBuffer.wrap(answer)));

        }

    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }

    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select(TIME_OUT) == 0) {
                    System.out.println("======================");
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable()) {
                        handleWrite(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

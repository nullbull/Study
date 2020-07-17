package main.java.netty.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class zwt implements Runnable{


    public static void main(String[] args) {
       zwt zwt = new zwt();
       Thread a = new Thread(zwt);
       a.start();
    }

    @Override
    public void run() {
        System.out.println("???????????????");
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        SocketChannel sc = null;
        Selector selector = null;
        try {
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            //请求连接
            sc.connect(new InetSocketAddress("localhost", 8088));
            selector = Selector.open();
            sc.register(selector, SelectionKey.OP_CONNECT);
            while (true) {
                System.out.println("????");
                int readCount = selector.select();
                if (readCount == 0){
                    System.out.println("???");
                    continue;
                }
                Iterator it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isConnectable()) {
                        if (sc.isConnectionPending()) {
                            if (sc.finishConnect()) {
                                writeBuffer.clear();
                                writeBuffer.put("server, hello".getBytes());
                                writeBuffer.flip();
                                sc.write(writeBuffer);
                                sc.register(selector, SelectionKey.OP_READ);
                            }
                        }
                    }
                    else if (key.isReadable()) {
                        System.out.println("++++++++++++++++++");
                        readBuffer.clear();
                        sc.read(readBuffer);
                        readBuffer.flip();
                        System.out.println(readBuffer);
                        CharBuffer charBuffer = CharsetHelper.decode(readBuffer);
                        System.out.println(charBuffer.toString());
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}

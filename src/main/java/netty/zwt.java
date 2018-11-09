package netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class zwt {
    public static void client(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",8088));
            Selector sc = Selector.open();
            socketChannel.register(sc, SelectionKey.OP_CONNECT);
            sc.select();
            Iterator it = sc.selectedKeys().iterator();

            System.out.println("????????????????????");
            while (true) {
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    System.out.println("????????????????????");

                    if (key.isConnectable()) {
                        if (socketChannel.isConnectionPending()) {
                            System.out.println("????????????????????");
                            if (socketChannel.finishConnect()) {
                                key.interestOps(SelectionKey.OP_READ);
                                int i = 0;
                                    TimeUnit.SECONDS.sleep(1);
                                    String info = "I'm " + i++ + "-th information from client";
                                    buffer.clear();
                                    buffer.put(info.getBytes());
                                    buffer.flip();
                                    socketChannel.write(buffer);
                            }
                        }
                    }
                    else if (key.isReadable()) {
                        System.out.println("--------------");
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer temp = ByteBuffer.allocate(1024);
                        channel.read(temp);
                        temp.flip();
                        while (temp.hasRemaining()) {
                            System.out.print((char)temp.get());
                        }
                        System.out.println();
                    }
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++) {
            service.execute( () -> {
                client();
            });
        }
    }
}

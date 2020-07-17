package main.java.netty.NIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;

public class CHANNEL {
    private static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }

    private static void channelCopy2(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
        }
        buffer.clear();

    }
    public static void main(String[] args) throws IOException {
        /**
         * SocketChannel创建
         */
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("www.baidu.com", 80));
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("www.baidu.com", 80));
        DatagramChannel dc = DatagramChannel.open();
        /**
         * FileChannel创建
         */
        RandomAccessFile raf = new RandomAccessFile("somfile", "r");
        FileChannel fc = raf.getChannel();
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);

        /**
         * 对文件加锁
         */
        FileChannel fileCh = raf.getChannel();
        fileCh.lock();
        fileCh.lock(1, 100, false);
        fileCh.tryLock().isShared();
        /**
         * 判断是否有锁重叠
         */
        fileCh.lock().overlaps(10, 100);

        /**
         * 内存映射
         */
        MappedByteBuffer bf2 = fileCh.map(FileChannel.MapMode.READ_ONLY, 100, 200);
        bf2 = fileCh.map(FileChannel.MapMode.READ_ONLY, 0, fileCh.size());

        bf2.isLoaded();

        /**
         * 将文件加载到内存,代价很高
         */
        bf2.load();

        /**
         * channel to channel
         */
        FileInputStream fis = new FileInputStream("zwt");
        FileChannel ffc = fis.getChannel();
        ffc.transferTo(0, ffc.size(), fileCh);
        ffc.close();
        fileCh.close();

        /**
         * SocketChannel 设置为非堵塞的
         */
        SocketChannel sc1 =  SocketChannel.open();
        sc.configureBlocking(false);
        sc.blockingLock();
        Socket ssss = sc1.socket();

        /**
         * 绑定地址
         */
        ServerSocketChannel sss = ServerSocketChannel.open();
        sss.socket().bind(new InetSocketAddress("www.baidu.com", 80));

    }
}

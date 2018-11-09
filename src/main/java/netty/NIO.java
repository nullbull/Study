package netty;

import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class NIO {
    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };
    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strings.length) {
            return false;
        }
        String s = strings[index++];
        for (int i = 0; i < s.length(); i++) {
            buffer.put(s.charAt(i));
        }
        return true;
    }

    private static void printf(Buffer buffer) {
        System.out.println("pos " + buffer.position() + ", capacity=" + buffer.capacity() + " : " + buffer.toString() );
    }

    public static void main(String[] args)  throws Exception{

        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)) {
            /**
             * 复位
             */
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
        /**
         *
         */
        CharBuffer buffer1 = CharBuffer.allocate(10);
        buffer.position(2).mark().position(6);
        /**
         * put
         */
        buffer.put("z").put("w").put("t").put("m").put("y").put("l").put("o").put("v").put("e");
        char[] zwt = "zwtmylove".toCharArray();
        buffer.put(zwt);
        /**
         * buffer创建
         */
        CharBuffer hh = CharBuffer.allocate(10);
        CharBuffer charBuffer = CharBuffer.wrap(zwt);
        CharBuffer secondBuffer = CharBuffer.wrap(zwt, 2, 4);
        secondBuffer.hasArray();
        /**
         * buffer 复制
         */
        CharBuffer beforeBuffer = CharBuffer.allocate(8);
        beforeBuffer.position(3).limit(7).mark().position(5);
        CharBuffer afterBuffer = beforeBuffer.duplicate();
        beforeBuffer.clear();
        /**
         * buffer 分割
         */
        CharBuffer buffer2 = CharBuffer.allocate(8);
        buffer2.position(3).limit(3);
        CharBuffer sliceBuffer = buffer2.slice();

        /**
         * 大段字节 小端字节 Order
         */
        ByteOrder order = ByteOrder.LITTLE_ENDIAN;
        ByteBuffer orderTest = ByteBuffer.allocate(10).order(order);

        /**
         * 直接缓冲区
         */
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10);
        Boolean isDirect = directBuffer.isDirect();

        /**
         * ByteBuffer 到其他Buffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer1 = byteBuffer.asCharBuffer();
        byteBuffer.put((byte)0).put((byte)'H').put((byte)0).put((byte)'i').put((byte)0).put((byte)'!');
        printf(byteBuffer);
        printf(charBuffer1);

        /**
         * 将byteBuffer转为 原始类型
         */
        long value = byteBuffer.getLong();
        char cc = byteBuffer.getChar();


        /**
         * position方法
         */
        RandomAccessFile randomAccessFile = new RandomAccessFile("filename", "r");
        randomAccessFile.seek (1000);
        FileChannel fileChannel = randomAccessFile.getChannel( );
        System.out.println ("file pos: " + fileChannel.position( ));
        randomAccessFile.seek (500);
        System.out.println ("file pos: " + fileChannel.position( ));
        fileChannel.position (200);
        System.out.println ("file pos: " + randomAccessFile.getFilePointer( ));

    }

}

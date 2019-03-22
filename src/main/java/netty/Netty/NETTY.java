package netty.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class NETTY {
    public static void main(String[] args) throws Exception{
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks", utf8);
        System.out.println((char)buf.getByte(0));
        int readIndex = buf.readerIndex();
        int writeIndex = buf.writerIndex();
        System.out.println("readIndex: " + readIndex + "writeIndex: " + writeIndex);
        buf.setByte(0, (byte)'B');
        System.out.println((char)buf.getByte(0));
        System.out.println("readIndex: " + buf.readerIndex() + "writeIndex: " + buf.writerIndex());
        for (int i = 0; i < 5; i++) {
            buf.readByte();
        }
        System.out.println("readIndex: " + buf.readerIndex() + "writeIndex: " + buf.writerIndex());

        buf.writeByte((byte) '!');
        System.out.println("readIndex: " + buf.readerIndex() + "writeIndex: " + buf.writerIndex());

    }
}

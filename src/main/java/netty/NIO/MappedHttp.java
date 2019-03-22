package netty.NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
/**
 * Dummy HTTP server using MappedByteBuffers.
 * Given a filename on the command line, pretend to be
 * a web server and generate an HTTP response containing
 * the file content preceded by appropriate headers. The
 * data is sent with a gathering write.
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 */
public class MappedHttp
{
    private static final String OUTPUT_FILE = "MappedHttp.out";
    private static final String LINE_SEP = "\r\n";
    private static final String SERVER_ID = "Server: Ronsoft Dummy Server";
    private static final String HTTP_HDR =
            "HTTP/1.0 200 OK" + LINE_SEP + SERVER_ID + LINE_SEP;
    private static final String HTTP_404_HDR =
            "HTTP/1.0 404 Not Found" + LINE_SEP + SERVER_ID + LINE_SEP;
    private static final String MSG_404 = "Could not open file: ";

    public static void main (String [] args) throws Exception
    {
        if (args.length < 1) {
            return;
        }
        String file = args[0];
        ByteBuffer header = ByteBuffer.wrap (bytes (HTTP_HDR));
        ByteBuffer dynhdrs = ByteBuffer.allocate (128);
        ByteBuffer [] gather = { header, dynhdrs, null };
        String contentType = "unknown/unknown";
        long contentLength = -1;
        try {
            FileInputStream fis = new FileInputStream (file);
            FileChannel fc = fis.getChannel( );
            MappedByteBuffer filedata = fc.map(MapMode.READ_ONLY, 0, fc.size( ));
            gather[2] = filedata;
            contentLength = fc.size( );
            contentType = URLConnection.guessContentTypeFromName(file);
        } catch (IOException e) {
// file could not be opened; report problem
            ByteBuffer buf = ByteBuffer.allocate (128);
            String msg = MSG_404 + e + LINE_SEP;
            buf.put (bytes (msg));
            buf.flip( );
// Use the HTTP error response
            gather [0] = ByteBuffer.wrap (bytes (HTTP_404_HDR));
            gather [2] = buf;
            contentLength = msg.length( );
            contentType = "text/plain";
        }
        StringBuffer sb = new StringBuffer( );
        sb.append ("Content-Length: " + contentLength);
        sb.append (LINE_SEP);
        sb.append ("Content-Type: ").append (contentType);
        sb.append (LINE_SEP).append (LINE_SEP);
        dynhdrs.put (bytes(sb.toString( )));
        dynhdrs.flip( );
        FileOutputStream fos = new FileOutputStream(OUTPUT_FILE);
        FileChannel out = fos.getChannel( );
        while (out.write (gather) > 0) {
// Empty body; loop until all buffers are empty
        }
        out.close( );
        System.out.println ("output written to " + OUTPUT_FILE);
    }
    // Convert a string to its constituent bytes
// from the ASCII character set

    /**
     * String 转为Byte数组
     * @param string
     * @return
     * @throws Exception
     */
    private static byte [] bytes (String string)
            throws Exception
    {
        return (string.getBytes ("US-ASCII"));
    }
}
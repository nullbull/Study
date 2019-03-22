package netty.NIO;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Scatter/Gather
 */
public class Marketing {

    private static final String UUU = "zwt.txt";

    private static Random random = new Random(47);
    private static String [] col1 = {
            "Aggregate", "Enable", "Leverage",
            "Facilitate", "Synergize", "Repurpose",
            "Strategize", "Reinvent", "Harness"
    };
    private static String [] col2 = {
            "cross-platform", "best-of-breed", "frictionless",
            "ubiquitous", "extensible", "compelling",
            "mission-critical", "collaborative", "integrated"
    };
    private static String [] col3 = {
            "methodologies", "infomediaries", "platforms",
            "schemas", "mindshare", "paradigms",
            "functionalities", "web services", "infrastructures"
    };
    private static String newline = System.getProperty ("line.separator");
    // The Marcom-atic 9000
    private static ByteBuffer[] utterBS (int howMany)
            throws Exception
    {
        List list = new LinkedList( );
        for (int i = 0; i < howMany; i++) {
            list.add(pickRandom (col1, " "));
            list.add(pickRandom (col2, " "));
            list.add(pickRandom (col3, newline));
        }
        ByteBuffer [] bufs = new ByteBuffer [list.size( )];
        list.toArray(bufs);
        return (bufs);
    }

    private static ByteBuffer pickRandom(String[] ss, String suffix) throws UnsupportedEncodingException {
        String s = ss[random.nextInt(ss.length)];
        int total = s.length() + suffix.length();
        ByteBuffer buf = ByteBuffer.allocate(total);
        buf.put(s.getBytes("UTF-8"));
        buf.put(suffix.getBytes("UTF-8"));
        buf.flip();
        return buf;
    }

    public static void main(String[] args) throws Exception {
        int reps = 10;
        if (args.length > 0) {
            reps = Integer.parseInt(args[0]);
        }
        FileOutputStream fos = new FileOutputStream(UUU);

        GatheringByteChannel gatheringByteChannel = fos.getChannel();

        ByteBuffer[] bs = utterBS(reps);

        while (gatheringByteChannel.write(bs) > 0) {

        }
        System.out.println("xxxxxxxxxxxxxxxxxxx");
        fos.close();



    }
}

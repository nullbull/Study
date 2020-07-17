package main.java.Socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auth justinniu
 * @Date 2018/9/23
 * @Desc
 */
public class ConnectBaidu {
    public static void main(String[] args) {
        try{
            URL u = new URL("http://www.baidu.com");
            URLConnection conn = u.openConnection();
            try (InputStream is = conn.getInputStream();){
                InputStream buffer = new BufferedInputStream(is);
                InputStreamReader reader = new InputStreamReader(buffer);
                int c;
/*                while ((c=reader.read()) != -1) {
                    System.out.println((char)c);
                }*/
                Map<String, List<String>> headerFields = conn.getHeaderFields();
                Iterator it = headerFields.entrySet().iterator();
                while (it.hasNext()) {
                    System.out.println(it.next() + ":" + headerFields.get(it));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            System.finalize();
        }
    }
}

package Java.Collection;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class CountingMapData extends AbstractMap<Integer, String> {
    private int size;
    private static String[] chars = "A B C D E F G H I G K L M N O P Q R S T U V W X Y Z".split(" ");
    public CountingMapData(int size) {
        this.size = size < 0 ? 0 : size;
    }
    private static class Entry implements Map.Entry<Integer, String> {

        @Override
        public Integer getKey() {
            return null;
        }

        @Override
        public String getValue() {
            return null;
        }

        @Override
        public String setValue(String value) {
            return null;
        }
    }
    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
        return null;
    }
}

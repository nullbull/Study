package leetcode;

public class AZ字打印 {

    public static String convert(String s, int numRows) {

        if (s == null || s.isEmpty()) {
            return s;
        }


        if (numRows == 1) {
            return s;
        }
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        int length = s.length();
        int i = 0;
        char[] chars = s.toCharArray();
        while (i < length) {
            for (int idx = 0; i < length && idx < numRows; idx++, i++) {
                sb[idx].append(chars[i]);
            }
            for (int idx = numRows - 2; i< length && idx >= 1; idx--, i++) {
                sb[idx].append(chars[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        for (i = 0; i < numRows; i++) {
            result.append(sb[i]);
        }


        return result.toString();

    }


    public static void main(String[] args) {
        System.out.println(        convert("ABCDEFGHI", 4));
    }
}

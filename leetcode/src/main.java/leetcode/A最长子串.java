package main.java.leetcode;

public class A最长子串 {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int length = s.length();

        int left = 0;
        int right = 0;
        int max = -1;
        int[] bool = new int[128];
        for (int i = 0; i < 128; i++) {
            bool[i] = 0;
        }

        while (right < length) {
            char c = s.charAt(right);
            int repeat = bool[c];
            if (repeat == 1) {
                max = Math.max(max, right - left);
                while (s.charAt(left) != c) {
                    char before = s.charAt(left);
                    bool[before] = 0;
                    left++;
                }
                left++;
            } else {
                bool[c] = 1;
            }
            right++;
        }
        max = Math.max(max, right - left);

        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcxdbbe"));
    }
}

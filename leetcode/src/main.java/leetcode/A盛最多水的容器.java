package leetcode;

public class A盛最多水的容器 {

    public static int maxArea(int[] height) {

        if (height == null || height.length < 2) {
            return 0;
        }

        int max = -1;
//        for (int i = 0; i < height.length; i++) {
//            for (int j = 0; j < height.length; j++) {
//                int min = Math.min(height[i], height[j]);
//                max = Math.max(max, min * Math.abs(i - j));
//            }
//        }
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int i = height[left];
            int j = height[right];
            max = Math.max(max, (right - left) * Math.min(i, j));
            if(i < j) {
                left++;
            } else if (i > j) {
                right--;
            } else {
                left++;
                right--;
            }

        }

        return max;
    }
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}

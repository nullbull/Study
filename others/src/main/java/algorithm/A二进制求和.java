package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/25
 * @Desc
 */
public class A二进制求和 {
    public static String addBinary(String aa, String bb) {
        char a[] = aa.toCharArray();
        char b[] = bb.toCharArray();
        char c[] = new char[100];
        if (a.length < b.length) {
            char temp[] = a;
            a = b;
            b = temp;
        }
        int temp = 0;
        int p = 0;
        for (int i = b.length-1,  j = a.length -1; i >= 0 && j >= 0; i--, j--) {
            int x = b[i] - '0';
            int y = a[j] - '0';
            if (x + y + temp>= 2) {
                c[p++] = (char)((x+y+temp) % 2 + '0');
                temp = (x + y + temp) / 2;
            }
            else
            {
                c[p++] = (char)(x + y + temp + '0');
                temp = 0;

            }
        }
        if (a.length > b.length) {
            for (int i = a.length - b.length - 1; i >= 0; i--) {
                int x = a[i] - '0';
                if(x + temp >= 2){
                    c[p++] =  (char)((x+temp) % 2 + '0');
                    temp = (x + temp) / 2;
                }
                else {
                    c[p++] = (char)(x + temp + '0');
                    temp = 0;
                }
            }
        }
        if (temp >= 1) {
            c[p++] = '1';
        }
       String ans = "";
        for (int i = p-1; i >= 0; i--) {
            ans += c[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(addBinary("100", "110010"));
    }

}

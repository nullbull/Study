import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author justinniu
 * @date 2018/11/16 16:47:07
 */
public class ZWT {

    /**
     * DEFAULT 默认大小
     * 2030×1080 刘海屏等大屏分辨率 DEFALUT × 1.09
     * SW600PD 平板电脑分辨率支持 DEFALUT × 1.035
     * 源文件目录的dimens文件名需为dimens.xml
     * 如选择当前目录，请输入回车
     */
    private static double DEFAULT_2030_1080 = 1.09;
    private static double DEFAULT_SW600 = 1.035;

    private static String DEFAULT_FILE_NAME = "dimens-generation.xml";
    private static List<Integer> TYPE_LIST = Arrays.asList(1, 2, 3);


    /**
     * 将没有/的文件添加/
     * @param path
     * @return
     */
    private static String completePath(String path) {
        if (!path.isEmpty() && path.charAt(path.length() - 1) != '/') {
            path += "/";
        }
        return path;
    }

    /**
     * 生存方法
     * @param type 选择的类型
     * @param SourceFileName 目标文件目录
     * @param path 输入文件目录
     */
    public static void gen(int type, String SourceFileName, String path) {
        SourceFileName = completePath(SourceFileName);
        path = completePath(path);
        /**
         * 以此文件夹下的dimens.xml文件内容为初始值参照
         */
        File file = new File(SourceFileName + "dimens.xml");
        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        try {
            if (type == 1) {
                sw240.append("                <!--  2030*1080 -->           \r\n");
            }
            else {
                sw240.append("                <!--  DEFAULT -->            \r\n");
            }
            if (type == 3) {
                sw480.append("\r\n ")
                     .append("              <!--  2030*1080 -->              \r\n");
            } else {
                sw480.append("\r\n ")
                     .append("              <!--  SW600 -->              \r\n");
            }

            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    //截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字
                    Double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));
                    //根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行。
                    switch (type) {
                        case 1:
                            sw240.append(start).append(String.format("%.2f", num * DEFAULT_2030_1080)).append(end).append("\r\n");
                            sw480.append(start).append(String.format("%.2f", num * DEFAULT_SW600)).append(end).append("\r\n");
                            break;
                        case 2:
                            sw240.append(start).append(String.format("%.2f", num / DEFAULT_2030_1080)).append(end).append("\r\n");
                            sw480.append(start).append(String.format("%.2f", num / DEFAULT_2030_1080 * DEFAULT_SW600)).append(end).append("\r\n");
                            break;
                        case 3:
                            sw240.append(start).append(String.format("%.2f", num / DEFAULT_SW600)).append(end).append("\r\n");
                            sw480.append(start).append(String.format("%.2f", num / DEFAULT_SW600 * DEFAULT_2030_1080)).append(end).append("\r\n");
                            break;
                    }
                } else {
                    sw240.append(tempString).append("");
                    sw480.append(tempString).append("");
                }
                line++;
            }
            /**
             * 确定文件的目录
             */
            String sw240file = path.isEmpty() ? SourceFileName + DEFAULT_FILE_NAME : path + DEFAULT_FILE_NAME;
            reader.close();
            /**
             * 输出生成的配置文件
             */
            System.out.println(sw240);
            System.out.println(sw480);
            /**
             * 将新的内容，写入到指定的文件中去
             */
            writeFile(sw240file, sw240.toString() + sw480.toString());
            System.out.println("完成");
            System.out.println("生成的文件路径为" + new File(sw240file).getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 验证输入文件目录是否合法, 空白输入合法
     * @param file
     * @param type
     * @return
     */
    private static boolean validFile(String file, int type) {

        if (file.isEmpty()) {
            return true;
        }
        if (!file.isEmpty()&&file.charAt(0) != '/')
            return false;
        if (type == 1) {
            return new File(file.charAt(file.length() - 1) == '/' ? file + "dimens.xml" : file + "/" + "dimens.xml").exists();
        } else {
            return new File(file.isEmpty() ? file + "/" : file).exists();
        }
    }

    /**
     * 验证输入的type是否合法
     * @param type
     * @return
     */
    private static boolean validType(String type) {
        if (type.isEmpty() || type.length() != 1) {
            return false;
        }
        if (!Character.isDigit(type.charAt(0))) {
            return false;
        }
        return TYPE_LIST.contains(Integer.parseInt(type));
    }
    /**
     * 写入方法
     */
    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                " * DEFAULT 默认大小\n" +
         " * 2030×1080 刘海屏等大屏分辨率 DEFALUT × 1.09 \n" +
         " * SW600PD 平板电脑分辨率支持 DEFALUT × 1.035 \n" +
         " * 源文件目录的dimens文件名需为dimens.xml \n" +
         " * 如选择当前目录，请输入回车\n"
         );
        System.out.println("请选择输入的文件类型 dimen/DEFAULT:1  dimen/2030*1080:2  dimen/SW600PD:3");
        int type = 1;
        while (true) {
            String fin = sc.nextLine();
            if (validType(fin)) {
                type = Integer.parseInt(fin);
                break;
            }
            System.out.println("输入错误请重新输入");
        }
        String SourceFileName = "----";
        System.out.println("请输入文件所在的路径(如选择)");
        while (true) {
            SourceFileName = sc.nextLine();
            if (validFile(SourceFileName, 1)) {
                break;
            }
            System.out.println("文件格式不正确或者不存在，请重新输入");
        }

        //sc.nextLine();
        System.out.println("请输入生成文件的路径");
        String path = "";
        while (true) {
            path = sc.nextLine();
            if (validFile(path, 2)) {
                break;
            }
            System.out.println("文件格式不正确或者不存在，请重新输入");
        }
        gen(type, SourceFileName, path);
    }

}

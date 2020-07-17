package main.java.dimen;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author justinniu
 * @date 2018/11/16 16:47:07
 */
public class DimensTool15 {

    /**
     * DEFAULT 默认大小
     * 2030×1080 刘海屏等大屏分辨率 DEFALUT × 1.09
     * SW600PD 平板电脑分辨率支持 DEFALUT × 1.035
     * 源文件目录的dimens文件名需为dimens.xml
     * 如选择当前目录，请输入回车
     */
    private static double DEFAULT_2030_1080 = 1.09;
    private static double DEFAULT_SW600 = 1.035;

    private static String DEFAULT_FILE_NAME = "main/java/dimen/dimens-generation.xml";
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
        System.out.println("SouthFIleName + " + SourceFileName + "|");
        System.out.println("path + " + path + " |");
        File file = new File(SourceFileName + "main/java/dimen/dimens.xml");
        System.out.println(file.getAbsoluteFile());
        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        try {
            if (type == 1) {
                sw240.append("<!-------------------------  2030*1080 --------------------------->\r\n");
            }
            else {
                sw240.append("<!-------------------------  DEFAULT ----------------------------->\r\n");
            }
            if (type == 3) {
                sw480.append("\r\n ")
                        .append("<!-------------------------  2030*1080 --------------------------->\r\n");
            } else {
                sw480.append("\r\n ")
                        .append("<!-------------------------  SW600 ---------------------------> \r\n");
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
        if (!file.isEmpty() && file.charAt(0) != '/')
            return false;
        if (type == 1) {
            return new File(file.charAt(file.length() - 1) == '/' ? file + "main/java/dimen/dimens.xml" : file + "/" + "main/java/dimen/dimens.xml").exists();
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
    static String fin = "";
    static String SourceFileName  = "";
    static String path = "";
    static Scanner sc = null;

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println(
//                " * DEFAULT 默认大小\n" +
//         " * 2030×1080 刘海屏等大屏分辨率 DEFALUT × 1.09 \n" +
//         " * SW600PD 平板电脑分辨率支持 DEFALUT × 1.035 \n" +
//         " * 源文件目录的dimens文件名需为dimens.xml \n" +
//         " * 如选择当前目录，请输入回车\n"
//         );
//        System.out.println("请选择输入的文件类型 dimen/DEFAULT:1  dimen/2030*1080:2  dimen/SW600PD:3");
//        int type = 1;
//        while (true) {
//            String fin = sc.nextLine();
//            if (validType(fin)) {
//                type = Integer.parseInt(fin);
//                break;
//            }
//            Sycdstem.out.println("输入错误，请重新输入");
//        }
//        String SourceFileName = "";
//        System.out.println("请输入Dimens文件所在的路径(回车为当前目录)");
//        while (true) {
//            SourceFileName = sc.nextLine();
//            if (validFile(SourceFileName, 1)) {
//                break;
//            }
//            System.out.println("文件格式不正确或者不存在，请重新输入");
//        }
//
//        //sc.nextLine();
//        System.out.println("请输入生成适配文件的路径(回车为当前目录)");
//        String path = "";
//        while (true) {
//            path = sc.nextLine();
//            if (validFile(path, 2)) {
//                break;
//            }
//            System.out.println("文件格式不正确或者不存在，请重新输入");
//        }


        Scanner sc = new Scanner(System.in);

        boolean a = false;
        boolean b = false;
        boolean c = false;


        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                while (!(boolean)o) {
                    System.out.println("输入错误，请重新输入");
                    System.out.println("您可以输入1, 2, 3来选择");
                    o = validType(fin = sc.nextLine());
                    System.out.println(fin);
                    System.out.println(o);
                }
            }
        };
        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                while (!(boolean)o) {
                    System.out.println("输入错误，请重新输入");
                    System.out.println("您可以输入1, 2, 3来选择");
                    o = validType(fin = sc.nextLine());
                    System.out.println(fin);
                    System.out.println(o);
                }
                return o;
            }
        };
        Consumer consumer1 = new Consumer() {
            @Override
            public void accept(Object o) {
                while (!(boolean) o) {
                    System.out.println("Dimens文件格式不正确或者不存在，请重新输入");
                    o = validFile(path = sc.nextLine(), 1);
                }
            }
        };
        Consumer consumer2 = new Consumer() {
            @Override
            public void accept(Object o) {
                while (!(boolean) o) {
                    System.out.println("输出文件夹格式不正确或者不存在，请重新输入");
                    o = validFile(path = sc.nextLine(), 2);
                }
            }
        };
        Predicate p = new Predicate() {
            @Override
            public boolean test(Object o) {
                return validType((String) o);
            }
        };

        if (args.length == 0) {
            System.out.println("选项不能为空");
        }
        Optional firstInput = null;
        Optional<String> sourcePath = null;
        Optional<String> toPath = null;
        if (args.length == 1) {
            firstInput = Optional.ofNullable(args[0]);
            if (!(a = firstInput.filter(o -> validType((String) o)).isPresent())) {
                a = (boolean)function.apply(a);
            }
            fin = (String)firstInput.get();
            System.out.println(a);
            if (args.length == 2) {
                sourcePath = Optional.ofNullable(args[1]);
                doIfElse(sourcePath.get(), p, consumer1);
                if (args.length == 3) {
                    toPath = Optional.ofNullable(args[2]);
                    doIfElse(toPath.get(), p, consumer2);
                }
                c = true;
            }
            b = true;
            c = true;
        }

//        String fin = (String)firstInput.get();
//        Optional<String> sourcePath = Optional.ofNullable(args[1]);
//        Optional<String> toPath = Optional.ofNullable(args[2]);
//        if (!sourcePath.isPresent()) {
//            b = true;
//        }
//        if (!toPath.isPresent()) {
//            c = true;
//        }
//
//        System.out.println("1: " + fin);
//        System.out.println("2 :" + SourceFileName);
//        System.out.println("3 :" + path);
//
//        a = validType(fin);
////        b = validFile(SourceFileName, 1);
////        c = validFile(SourceFileName, 2);
//        while (!a) {
//            System.out.println("输入错误，请重新输入");
//            System.out.println("您可以输入1, 2, 3来选择");
//            a = validType(fin = sc.nextLine());
//        }
//
//
//        while (!c) {
//            System.out.println("文件格式不正确或者不存在，请重新输入");
//            c = validFile(path = sc.nextLine(), 2);
//        }

        if (a && b && c) {
            int type = Integer.parseInt(fin);
            gen(type, SourceFileName, path);
        }

    }
    private static void doIfElse(Object o, Predicate p, Consumer a) {
        a.accept(p.test(o));
    }

}


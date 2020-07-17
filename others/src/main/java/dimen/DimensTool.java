package dimen;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author justinniu
 * @date 2018/11/16 16:47:07
 */
@SuppressWarnings("unchecked")
public class DimensTool {
    /**
     * DEFAULT 默认大小
     * 2030×1080 刘海屏等大屏分辨率 DEFALUT × 1.09
     * SW600PD 平板电脑分辨率支持 DEFALUT × 1.035
     * 源文件目录的dimens文件名需为dimens.xml
     * 如选择当前目录，请输入回车
     */
    private static double DEFAULT_2030_1080 = 1.09;
    private static double DEFAULT_SW600 = 1.035;

    private static String DEFAULT_FILE_NAME = "dimen/dimens-generation.xml";
    private static String HELP = "--help";
    private static List<Integer> TYPE_LIST = Arrays.asList(1, 2, 3);
    private static List<String> ARGS = Arrays.asList("-type", "-i", "-o");

    private static String fin = "";
    private static String SourceFileName  = "";
    private static String path = "";
    private static Scanner sc = null;

    private static int TYPE_FIRST = 1;
    private static int TYPE_SECOND = 2;
    private static int TYPE_THIRD = 3;


    private enum Error{
        WRONG_COUNT(4, "选项不能为空"),
        WRONG_ARGS(1, "参数命令不正确\r\n, 请输入类似 -a [选项]"),
        WRONG_NUMBER(2, "参数命令重复"),
        WRONG_UNKNOW(3, "未知参数命令");

        private int code;
        private String msg;

        Error(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    private static Consumer consumer = new Consumer() {
        @Override
        public void accept(Object o) {
            while (!(boolean)o) {
                System.out.println("输入错误，请重新输入");
                System.out.println("您可以输入1, 2, 3来选择");
                o = validType(fin = sc.nextLine());
//                System.out.println(fin);
//                System.out.println(o);
            }
        }
    };
    private static Function function = new Function() {
        @Override
        public Object apply(Object o) {
            while (!(boolean)o) {
                System.out.println("输入错误，请重新输入");
                System.out.println("您可以输入1, 2, 3来选择");
                o = validType(fin = sc.nextLine());
//                System.out.println(fin);
//                System.out.println(o);
            }
            return o;
        }
    };
    private static Consumer consumer1 = new Consumer() {
        @Override
        public void accept(Object o) {
            while (!(boolean) o) {
                System.out.println("Dimens文件格式不正确或者不存在，请重新输入");
                o = predicate2.test(SourceFileName = sc.nextLine());
            }
        }
    };
    private static Consumer consumer2 = new Consumer() {
        @Override
        public void accept(Object o) {
            while (!(boolean) o) {
                System.out.println("输出文件夹格式不正确或者不存在，请重新输入");
                o = predicate3.test(path = sc.nextLine());
            }
        }
    };
    private static Predicate predicate1 = new Predicate() {
        @Override
        public boolean test(Object o) {
            return validType((String) o);
        }
    };
    private static Predicate predicate2 = new Predicate() {
        @Override
        public boolean test(Object o) {
            return validFile((String) o, 1);
        }
    };
    private static Predicate predicate3 = new Predicate() {
        @Override
        public boolean test(Object o) {
            return validFile((String) o, 2);
        }
    };

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
     * 生成方法
     * @param type 选择的类型
     * @param SourceFileName 输入文件目录
     * @param path 生成文件目录路径
     */
    public static void gen(int type, String SourceFileName, String path) {
        SourceFileName = completePath(SourceFileName);
        path = completePath(path);
        /**
         * 以此文件夹下的dimens.xml文件内容为初始值参照
         */
        // System.out.println("SouthFIleName + " + SourceFileName + "|");
        //  System.out.println("path + " + path + " |");
        File file = new File(SourceFileName + "dimen/dimens.xml");
        //System.out.println(file.getAbsoluteFile());
        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        try {
            if (type == TYPE_FIRST) {
                sw240.append("<!-------------------------  2030*1080 --------------------------->\r\n");
            }
            else {
                sw240.append("<!-------------------------  DEFAULT ----------------------------->\r\n");
            }
            if (type == TYPE_THIRD) {
                sw480.append("\r\n ")
                        .append("<!-------------------------  2030*1080 --------------------------->\r\n");
            } else {
                sw480.append("\r\n ")
                        .append("<!-------------------------  SW600 ------------------------------->\r\n");
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
                    Double num = Double.parseDouble(tempString.substring(tempString.indexOf(">") + 1,
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
                    sw240.append(tempString).append("\r\n");
                    sw480.append(tempString).append("\r\n");
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
            return new File(file.charAt(file.length() - 1) == '/' ? file + "dimen/dimens.xml" : file + "/" + "dimen/dimens.xml").exists();
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

    /**
     * 根据处理完的TreeMap进行后续处理
     * @param tree
     */
    private static void process(TreeMap<String, String> tree) {
        sc = new Scanner(System.in);
        boolean isFirstArgLegal = false;
        boolean isSecondArgLegal = false;
        boolean isThirdLegal = false;

        Optional firstInput = null;
        Optional<String> sourcePath = null;
        Optional<String> toPath = null;
        int size = tree.size();
        if (size >= 1) {
            firstInput = Optional.ofNullable(tree.get(ARGS.get(0)));
            fin = (String)firstInput.get();
            if (!(isFirstArgLegal = firstInput.filter(o -> validType((String) o)).isPresent())) {
                isFirstArgLegal = (boolean)function.apply(isFirstArgLegal);
            }
            //System.out.println(a);
            if (size >= 2 && null != tree.get(ARGS.get(1))) {
                sourcePath = Optional.ofNullable(tree.get(ARGS.get(1)));
                SourceFileName = sourcePath.get();
                // System.out.println("sourcePath: " + sourcePath.get());
                doIfElse(sourcePath.get(), predicate2, consumer1);
                if (size >= 3) {
                    toPath = Optional.ofNullable(tree.get(ARGS.get(2)));
                    path = toPath.get();
                    doIfElse(toPath.get(), predicate3, consumer2);
                }
                isThirdLegal = true;
            }
            if (size >= 2 && null == tree.get(ARGS.get(1)))
            {
                toPath = Optional.ofNullable(tree.get(ARGS.get(2)));
                path = toPath.get();
                doIfElse(toPath.get(), predicate3, consumer2);
                isSecondArgLegal = true;
            }
            isSecondArgLegal = true;
            isThirdLegal = true;
        }

        if (isFirstArgLegal && isSecondArgLegal && isThirdLegal) {
            int type = Integer.parseInt(fin);
            gen(type, SourceFileName, path);
        }
    }

    /**
     * 验证输入的参数
     * @param args
     * @return
     */
    private static TreeMap<String, String> validArgs(String[] args) {
        int length = args.length;
        if (length == 0) {
            alert(Error.WRONG_COUNT);
        }
        if (args[0].equals(HELP)) {
            System.out.println("DimensTool使用说明\n" +
                    "\n" +
                    "author: justinniu version:2.0\n" +
                    "\n" +
                    "-ty [必填] 输入文件类型 1 Default 2 2030×1080 3 SW600\n" +
                    "\n" +
                    "-i [可选] 输入目录，该目录下需要包含dimens.xml文件\n" +
                    "\n" +
                    "-o [可选] 输出目录\n" +
                    "\n" +
                    "使用示例\n" +
                    "\n" +
                    "java DimensTools -type 1\n" +
                    "根据当前目录下的dimens文件生成到当前目录下\n" +
                    "\n" +
                    "java DimensTools -type 2 -o /home/user/\n" +
                    "根据当前目录下的dimens.xml文件生成到 /home/user目录下\n" +
                    "\n" +
                    "java DimensTool -type 3 -i /home/user/ -o /opt/\n" +
                    "根据/home/user/目录下的dimens.xml文件生成到/opt目录下");
            System.exit(0);
        }
        if (length == 0 || length % 2 == 1 || length > 6) {
            alert(Error.WRONG_COUNT);
        }
        if (Arrays.stream(args).filter(a -> "-".equals(a)).findFirst().isPresent()) {
            alert(Error.WRONG_ARGS);
        }
        Set<String> set = Arrays.stream(args).filter(s -> s.contains("-")).collect(Collectors.toSet());
        if (set.size() != length / 2) {
            alert(Error.WRONG_NUMBER);
        }
        if (!ARGS.containsAll(set)) {
            alert(Error.WRONG_UNKNOW);
        }
        TreeMap<String, String> tree = new TreeMap<>();
        for (int i = 0; i < length; i++) {
            if (i % 2 == 1) {
                tree.put(args[i-1], args[i]);
            }
        }
        return tree;

    }
    private static void alert(Error error) {
        System.out.println(error.getMsg());
        System.out.println("请输入正确的参数\r\n");
        System.out.println("--help 查看帮助\r\n");
        System.out.println("您可以输入类似 -type 1 -i /home/justinniu -o /home/justinniu/generation");
        System.exit(0);
    }
    private static void doIfElse(Object o, Predicate p, Consumer a) {
        a.accept(p.test(o));
    }
    public static void main(String[] args) {
        process(validArgs(args));
    }


}

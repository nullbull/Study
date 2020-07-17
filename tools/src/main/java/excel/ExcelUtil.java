package main.java.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: niuzhenhao
 * @date: 2019-09-06 16:22
 * @desc:
 */
public class ExcelUtil {

    private static final ConcurrentHashMap<String, List<Method>> getMethodMap = new ConcurrentHashMap<>(64);
    private static final ConcurrentHashMap<String, Field[]> fieldMap = new ConcurrentHashMap<>(32);
    private static final ConcurrentHashMap<String, List<Class<?>>> fieldTypeMap = new ConcurrentHashMap<>(32);


    public static void main(String[] args) {
        File file = new File("D:\\TEST.xlsx");

        try {
            List<Student> objects = readExcelFile(new FileInputStream(file), Student.class);
            for (Student s : objects) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 将Excel文件流转为对象集合
     *
     * @param fileIn
     * @return
     * @throws IOException
     */
    public static <T> List<T> readExcelFile(FileInputStream fileIn, Class<?> clazz) throws Exception {
        List<T> result = new ArrayList<>();
        String clazzName = clazz.getSimpleName();
        Field[] fields = fieldMap.get(clazzName);
        List<Class<?>> fieldType = fieldTypeMap.get(clazzName);
        if (null == fields) {
            fields = clazz.getDeclaredFields();
            fieldMap.put(clazzName, fields);
        }
        if (null == fieldType) {
            fieldType = new ArrayList<>();
            for (int i = 0; i < fields.length; i++) {
                fieldType.add(fields[i].getType());
            }
            fieldTypeMap.put(clazzName, fieldType);
        }
        Method[] methods = clazz.getMethods();
        List<Method> getMethodList;
        if (null == (getMethodList = getMethodMap.get(clazzName))) {
            getMethodList = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                String methodName = "set" + UpperFirstChar(fieldName);
                getMethodList.add(clazz.getMethod(methodName, field.getType()));
            }
        }
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new XSSFWorkbook(fileIn);
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if (r.getRowNum() < 2) {
                continue;
            }
            if (r.getCell(0) == null) {
                break;
            }
            T o = (T) clazz.newInstance();
            Iterator it = r.cellIterator();
            int i = 0;
            while (it.hasNext()) {
                Method method = getMethodList.get(i);
                Cell cell = (Cell) it.next();
                Class fieldClazz = fieldType.get(i);
                if (fieldClazz.equals(String.class)) {
                    method.invoke(o, cell.getStringCellValue());
                } else if (fieldClazz.equals(Integer.class)){
                    method.invoke(o, (int)(cell.getNumericCellValue() / 1));
                } else if (fieldClazz.equals(Date.class)) {
                    method.invoke(o, cell.getDateCellValue());
                } else if (fieldClazz.equals(Long.class)) {
                    method.invoke(o, (long)(cell.getNumericCellValue() / 1));
                }
                i++;
            }
            result.add(o);
        }
        fileIn.close();
        return result;
    }
    /**
     * 首字母大写
     * @param s
     * @return
     */
    private static String UpperFirstChar(String s) {
        if (StringUtils.isEmpty(s) || StringUtils.isBlank(s)) {
            return s;
        }
        String first = s.substring(0, 1);
        String end = s.substring(1);
        return first.toUpperCase() + end;
    }
}

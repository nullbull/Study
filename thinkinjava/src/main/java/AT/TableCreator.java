package main.java.AT;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    private static String getConstraints(Constrains constrains) {
        String contraints = "";
        if(!constrains.allowNull())
            contraints += "NOT NULL";
        if (constrains.primaryKey())
            contraints += "PRIMARY KEY";
        if (constrains.unique()) 
            contraints += "UNIQUE";
        return contraints;
        
    }
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        Member member = new Member();
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null) {
                System.out.println("No DBTable annotations in class" + className);
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1) 
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs =  new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1) 
                    continue;
                if(anns[0] instanceof SQLInteger) {
                    SQLInteger sqlInteger = (SQLInteger) anns[0];
                    if(sqlInteger.name().length() < 1) 
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sqlInteger.name();
                    columnDefs.add(columnName+ " INT" + getConstraints(sqlInteger.contraints()));
                }
                if(anns[0] instanceof SQLString) {
                    SQLString SQLString = (SQLString) anns[0];
                    if(SQLString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = SQLString.name();
                    columnDefs.add(columnName+ " VARCHAR("  +SQLString.value() + ")" + getConstraints(SQLString.contraints()));
                }
                StringBuilder createCommand = new StringBuilder("Create table " + tableName + "(");
                for (String columnDef : columnDefs)
                    createCommand.append("\n      " + columnDef + ",");
                String tableCreate = createCommand.substring(
                        0, createCommand.length() - 1
                ) + ");";
                System.out.println("Table Creation SQL for " + className + " is : \n" + tableCreate);
            }
        }
    }
}

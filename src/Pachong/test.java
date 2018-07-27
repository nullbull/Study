package Pachong;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class test {
    public static void main(String[] args) {
        Date date = new Date(0);
        System.out.println(date);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(1997, 11, 30);
        gregorianCalendar.add(GregorianCalendar.DATE, 1);
        Date d = gregorianCalendar.getTime();
        DateFormat dateFormat = DateFormat.getDateInstance();
        String s = dateFormat.format(d);
        System.out.println(s);
    }


}

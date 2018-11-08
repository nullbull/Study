package Spring.Internet;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Auth justinniu
 * @Date 2018/10/28
 * @Desc
 */
public class LoacalTest {
    public static void main(String[] args) {
        Locale locale = new Locale("cn");
        Date date = new Date();
        NumberFormat dateFormat = NumberFormat.getCurrencyInstance(locale);
        DateFormat dateFormat1 = DateFormat.getDateInstance();

        System.out.println(dateFormat1.format(date));
        String p = "At {1, time, short} On {1, date, long}, {0} paid {2, number, currency}";
        Object[] params = {"niu", new GregorianCalendar().getTime(), 2};
        MessageFormat mf = new MessageFormat(p, Locale.CHINA);
        String ms = mf.format(params);
        System.out.println(ms);
    }
}

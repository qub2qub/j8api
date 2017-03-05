package winterbe.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalDateTime1 {

    public static void main(String[] args) {

        LocalDateTime sylvester = LocalDateTime.of(2017, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();
        System.out.println("instant = " + instant);

        Date legacyDate = Date.from(instant);
        System.out.println("legacyDate = " + legacyDate);     // Wed Dec 31 23:59:59 CET 2014


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm", Locale.ENGLISH);

        System.out.println("formatter = " + sylvester.format(formatter));

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter); // Nov ноя // case sensitive
        String string = parsed.format(formatter);
        System.out.println(string);     // Nov 03, 2014 - 07:13
    }
}
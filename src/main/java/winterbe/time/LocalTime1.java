package winterbe.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalTime1 {

    public static void main(String[] args) {

        // get the current time
        Clock clock = Clock.systemDefaultZone();
        long t0 = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        System.out.println("clock.millis() t0 = "+t0);
        System.out.println("clock.millis() = \t"+clock.millis() );
        System.out.println("clock = \t\t" + clock);
        System.out.println("instant = \t\t" + instant);
        System.out.println("legacyDate = \t" + legacyDate);

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println("zone1 Europe/Berlin = \t" + zone1.getRules());
        System.out.println("zone2 Brazil/East = \t" + zone2.getRules());

        // time
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println("now zone 1 = \t" + now1);
        System.out.println("now zone 2 = \t" + now2);
        System.out.println("now clock = \t" + LocalTime.now(clock));
        System.out.println("LocalTime.now() = \t" + LocalTime.now());

        System.out.println("now1.isBefore(now2) = "+now1.isBefore(now2));  // false
        System.out.println("now2.isBefore(now1) = "+now2.isBefore(now1));  // true

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println("hoursBetween = " + hoursBetween);
        System.out.println("minutesBetween = " + minutesBetween);

        // create time

        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println("late = " + late);
        //java.time.DateTimeException: Invalid value for HourOfDay (valid values 0 - 23): 44
//        LocalTime late2 = LocalTime.of(44, 59, 59);
//        System.out.println("late2 = " + late2);

        DateTimeFormatter germanFormatter = DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println("leetTime = " + leetTime);
        // to legacy date

        // prints all available timezone ids
//        System.out.println(ZoneId.getAvailableZoneIds());
    }

}
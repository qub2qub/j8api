package lambdasinaction.chap12;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created on 2018-03-31
 */
public class CheckTimeZones {
  
  public static void main(String[] args) {
    ZoneId romeZone = ZoneId.of("Europe/Rome");
    
//    LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
    LocalDate date = LocalDate.now();
    ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
    
//    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
    LocalDateTime dateTime = LocalDateTime.now();
    ZonedDateTime zdt2 = dateTime.atZone(romeZone);
    
    Instant instant = Instant.now();
    ZonedDateTime zdt3 = instant.atZone(romeZone);
    
    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    ZoneId nyZoneId = ZoneId.of("America/Panama");
    
    System.out.println("date = " + date);
    System.out.println("dateTime = " + dateTime);
    System.out.println("zoneId = " + zoneId);
    System.out.println("zdt1 = " + zdt1);
    System.out.println("zdt2 = " + zdt2);
    System.out.println("zdt3 = " + zdt3);
  
    //You can also convert a LocalDateTime to an Instant by using a ZoneId:
    Instant instantFromDateTime = dateTime.toInstant(ZoneOffset.of("-01:00"));
    // Or you can do it the other way around:
    LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
    ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
    //OffsetDateTime, which represents a date-time with an offset from UTC/Greenwich in the ISO-8601 calendar system:
    OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
//    newYorkOffset.
    
    System.out.println("instantFromDateTime = " + instantFromDateTime);
    System.out.println("timeFromInstant = " + timeFromInstant);
    System.out.println("\n__dateTimeInNewYork = " + dateTimeInNewYork);
    ZonedDateTime zonedDateTime = dateTimeInNewYork.toZonedDateTime();
    System.out.println("__dateTimeInNewYork2 = " + zonedDateTime);
    System.out.println("__BLR zonedDateTime.getZone() = " + zonedDateTime.withZoneSameInstant(zoneId));
  
    ZonedDateTime blrTime = ZonedDateTime.of(dateTime, zoneId);
    ZonedDateTime sfTime = blrTime.withZoneSameInstant(nyZoneId);
    System.out.println("sfTime = " + sfTime);
  
    JapaneseDate japaneseDate = JapaneseDate.from(date);
    Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
    ChronoLocalDate now = japaneseChronology.dateNow();
//    ChronoLocalDateTime
//    japaneseChronology.localDateTime()
    
    System.out.println("japaneseDate = " + japaneseDate);
    System.out.println("now in japan= " + now);
  
    // Get current Hijrah date; then change it to have the first day of Ramadan, which is the 9th month.
    HijrahDate ramadanDate = HijrahDate.now()
      .with(ChronoField.DAY_OF_MONTH, 1)
      .with(ChronoField.MONTH_OF_YEAR, 9);
    // Chronology.INSTANCE is a static instance of the IsoChronology class.
    System.out.println("Ramadan starts on " + IsoChronology.INSTANCE.date(ramadanDate) +
      " and ends on " + IsoChronology.INSTANCE.date(
        ramadanDate.with(TemporalAdjusters.lastDayOfMonth())));
    System.out.println("ramadanDate = " + ramadanDate);
  }
}

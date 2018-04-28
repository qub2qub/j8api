package lambdasinaction.chap12;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextWorkingDay implements TemporalAdjuster {
  
  public static final DateTimeFormatter ISO_LOCAL_DATE = new DateTimeFormatterBuilder()
      .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
      .appendLiteral('-')
      .appendValue(MONTH_OF_YEAR, 2)
      .appendLiteral('-')
      .appendValue(DAY_OF_MONTH, 2)
      .toFormatter();
  
  @Override
  public Temporal adjustInto(Temporal temporal) {
    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
    int dayToAdd = 1;
    if (dow == DayOfWeek.FRIDAY) {
      dayToAdd = 3;
    } else if (dow == DayOfWeek.SATURDAY) {
      dayToAdd = 2;
    }
    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
  }
  
  public static void main(String[] args) {
    NextWorkingDay myAdj = new NextWorkingDay();
    
    TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
      temporal -> {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
      });
    LocalDate date = LocalDate.now();
    System.out.println("date1 = " + date.with(nextWorkingDay));
    System.out.println("date2 = " + date.with(myAdj));
  }
}
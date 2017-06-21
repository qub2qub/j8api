package tutorial.Chapter_4_Functional_Programming;


import java.util.function.BiFunction;
import java.util.function.Function;

// функция с 2 агрументами, возвращает функцию с 1 аргументом
@FunctionalInterface
interface CurriedBiFunction<T, U, R>
    extends BiFunction<T, U, R> {
    default Function<U, R> setFirstArgument(T t) {
        return u -> apply(t, u);
    }
    default Function<T, R> setSecondArgument(U u) {
        return t -> apply(t, u);
    }
}
class CalculatePerDiem implements CurriedBiFunction<Double, Integer, Double>{
    private static final Double perDiemRate = 10.15;
    @Override
    public Double apply(Double dollarExchangeRate, Integer amountOfDays) {
        return dollarExchangeRate * amountOfDays * perDiemRate;
    }
}
public class Ex_4_Currying {
    public static void main(String[] args) {
        // Step 1: Calculate per diem for our IT-week speakers
        CalculatePerDiem calculatePerDiem = new CalculatePerDiem();
        // зафиксировали первый арг, будет изменяться второй
        Function<Integer, Double> fnFixedExchRate1stApril = calculatePerDiem.setFirstArgument(57.16);

        System.out.println("for 3 days = "+fnFixedExchRate1stApril.apply(3));
        System.out.println("for 5 days = "+fnFixedExchRate1stApril.apply(5));
        System.out.println("for 10 days = "+fnFixedExchRate1stApril.apply(10));

        // Step 2: Calculate per diem for different exchange rates
        // зафиксировали второй арг, будет изменяться первый
        Function<Double, Double> fnFixed10DaysStay = calculatePerDiem.setSecondArgument(10);

        System.out.println("курс по 56 = "+fnFixed10DaysStay.apply(56.12));
        System.out.println("курс по 61 = "+fnFixed10DaysStay.apply(61.63));
    }
}
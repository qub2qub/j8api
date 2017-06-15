package stream.map;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class List2Map
{
  public static void main(String[] args)
  {
    Map<String,String> map = Arrays.stream( DateFormat.getAvailableLocales() )
        .sorted(Comparator.comparing(Locale::getDisplayLanguage))
        .collect(Collectors.toMap(
            Locale::getDisplayLanguage,
            Locale::toString,
            (prev, next) -> prev + ", " + next) // handling for duplicates
        );

    TreeMap<String,String> tree = new TreeMap<>(map);
    tree.forEach( (k,v) -> System.out.println(k+" \t= "+v));
  }
}

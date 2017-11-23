package tutorial.tempo;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * Created on 2017-06-15
 */
public class LocaleLanguage
{
  public static String findLocaleByLanguagePreferences1( String language )
  {
    if ( StringUtils.isNotBlank( language ) ) {
    try
    {
      Locale locale1 = Arrays.stream(DateFormat.getAvailableLocales())
          .filter(it -> it.getDisplayLanguage().equalsIgnoreCase(language))
          .findFirst()
          .get();
      System.out
          .println(language + "_locale1 = " + locale1 + ", " + locale1.getLanguage() + "_" + locale1.getCountry());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    Locale locale2 = Locale.forLanguageTag(language);
    System.out.println(language + "_locale2 = " + locale2 + ", " + locale2.getLanguage() + "_" + locale2.getCountry());
  }
    return "en_US";
  }

  public static String findLocaleByLanguagePreferences( String language )
  {
    if ( StringUtils.isNotBlank( language ) ) {
      try {
        Locale locale = Arrays.stream( DateFormat.getAvailableLocales() )
            .filter( it -> it.getDisplayLanguage().equalsIgnoreCase( language ) )
            .findFirst()
            .get();
        return locale.getLanguage()
            + ( StringUtils.isNotBlank( locale.getCountry() ) ? "_" + locale.getCountry() : "" );
      } catch( NoSuchElementException e ) {
        return "en_US";
      }
    }
    return "en_US";
  }

  public static Map<String,String> find() {
    Map<String,String> map = Arrays.stream( DateFormat.getAvailableLocales() )
        .collect(Collectors.toMap(Locale::getDisplayLanguage,Locale::getLanguage));
    return map;
  }


  public void initDefaultLanguageCodes()
  {
    Map<String, String> map = Arrays.stream( DateFormat.getAvailableLocales() )
//        .filter( locale -> StringUtils.isNotBlank( locale.getDisplayLanguage() ) )
        .collect(Collectors.toMap( Locale::getLanguage, Locale::getDisplayLanguage, ( oldValue, newValue ) -> oldValue ) );
//    map.forEach( ( k, v ) -> LANGUAGE_CODES.put( v.toUpperCase(), k ) );
  }

  public static void main(String[] args) {

//    System.out.println(LanguageCodes.valueOf("French"));
//    System.out.println(LanguageCodes.valueOf("dutch"));
//    System.out.println(LanguageCodes.valueOf("zzxy"));

    Map<String,String> map = Arrays.stream( DateFormat.getAvailableLocales() )
//        .sorted(Comparator.comparing(Locale::getDisplayLanguage))
//        .collect(Collectors.toMap(Locale::getDisplayLanguage,Locale::toString, (s, a) -> s + ", " + a));
//        .collect(Collectors.groupingBy(e -> e.));
        .collect(Collectors.toMap(Locale::getLanguage,Locale::getDisplayLanguage, (s, a) -> s));

    TreeMap<String,String> tree = new TreeMap<>(map);
//    tree.forEach( (k,v) -> System.out.println(k+"="+v.toUpperCase()));
    tree.forEach( (k,v) -> System.out.println("["+k+"]=["+v.toUpperCase()+"]"));

//    findLocaleByLanguagePreferences1("ENGLISH");
//    findLocaleByLanguagePreferences1("French");
//    findLocaleByLanguagePreferences1("dutch");
//    findLocaleByLanguagePreferences1("xyz");
//    findLocaleByLanguagePreferences1("");
//    findLocaleByLanguagePreferences1(null);
//    System.out.println(findLocaleByLanguagePreferences("ENGLISH"));
//    System.out.println(findLocaleByLanguagePreferences("French"));
//    System.out.println(findLocaleByLanguagePreferences("dutch"));
//    System.out.println(findLocaleByLanguagePreferences("zzxy"));
//    System.out.println(findLocaleByLanguagePreferences(""));
//    System.out.println(findLocaleByLanguagePreferences(null));
  }
}

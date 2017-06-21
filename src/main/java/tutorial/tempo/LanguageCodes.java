package tutorial.tempo;

public enum LanguageCodes
{
  ALBANIAN("sq"),
  ARABIC("ar"),
  BELARUSIAN("be"),
  BULGARIAN("bg"),
  CATALAN("ca"),
  CHINESE("zh"),
  CROATIAN("hr"),
  CZECH("cs"),
  DANISH("da"),
  DUTCH("nl"),
  ENGLISH("en"),
  ESTONIAN("et"),
  FINNISH("fi"),
  FRENCH("fr"),
  GERMAN("de"),
  GREEK("el"),
  HEBREW("iw"),
  HINDI("hi"),
  HUNGARIAN("hu"),
  ICELANDIC("is"),
  INDONESIAN("in"),
  IRISH("ga"),
  ITALIAN("it"),
  JAPANESE("ja"),
  KOREAN("ko"),
  LATVIAN("lv"),
  LITHUANIAN("lt"),
  MACEDONIAN("mk"),
  MALAY("ms"),
  MALTESE("mt"),
  NORWEGIAN("no"),
  POLISH("pl"),
  PORTUGUESE("pt"),
  ROMANIAN("ro"),
  RUSSIAN("ru"),
  SERBIAN("sr"),
  SLOVAK("sk"),
  SLOVENIAN("sl"),
  SPANISH("es"),
  SWEDISH("sv"),
  THAI("th"),
  TURKISH("tr"),
  UKRAINIAN("uk"),
  VIETNAMESE("vi");

  private final String code;

  LanguageCodes(String code)
  {
   this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}

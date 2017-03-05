package winterbe.lambda;

/**
 * @author Benjamin Winterberg
 */
public class Lambda4 {

    static int outerStaticNum;

    int outerNum;

    void testScopes() {
        int num = 1;
        System.out.println("outerNum1 = " + outerNum);
        System.out.println("outerStaticNum1 = " + outerStaticNum);
        Converter<Integer, String> stringConverter0 =
                (from) -> String.valueOf(from + num);

        String convert = stringConverter0.convert(2);
        System.out.println(convert);    // 3

        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 35;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };

        String[] array = new String[1];
        Converter<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };

        stringConverter1.convert(35);
        stringConverter2.convert(72);
        stringConverter3.convert(23);
        System.out.println("outerNum2 = " + outerNum);
        System.out.println("outerStaticNum2 = " + outerStaticNum);
        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();
    }

}
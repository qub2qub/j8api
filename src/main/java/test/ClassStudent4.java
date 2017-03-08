package test;

/**
 * only the superclass method matters, and
 * any default method from the interface is simply ignored.
 *
 * This is the "class wins" rule.
 * The "class wins" rule ensures compatibility with Java 7.
 */
class ClassStudent4 extends ClassPerson3 implements Named {
    @Override
    public long getId() {
        return 0;
    }

    /**
     * Student inherits the getName method from Person,
     * and it doesn't make any difference whether the
     * Named interface provides a default for getName or not.
     */
    @Override
    public String getName() {
        return null;
    }

    /*
    Для метода getClassName(), к-й есть и в классе и в интерфейсе,
    влияет область видимости, в интерфейсе - она паблик,
    тогда в классе -- у этого методе она не может быть сужена,
     т.е. должны быть тоже паблик, protected уже не канает,
     компилятор выдаст ошибку.
     */

    /*
     you can't define a default method for toString or equals, even though
     that might be attractive for interfaces such as List.
     As a consequence of the "classes win" rule,
     such a method could never win against Object.toString or Object.equals.
     */

    public static void main(String[] args) {
        ClassStudent4 student4 = new ClassStudent4();
        System.out.println(student4.getClassName());
    }
}
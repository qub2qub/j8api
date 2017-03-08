package test;

/**
 * If neither interface provides a default for a shared method,
 * then we are in the pre-Java 8 situation and there is no conflict.
 * An implementing class has two choices: implement the method,
 * or leave it unimplemented. In the latter case, the class is itself abstract.
 */
abstract class Student3 implements Person2, Named2 {
    @Override
    public long getId() {
        return 0;
    }
}
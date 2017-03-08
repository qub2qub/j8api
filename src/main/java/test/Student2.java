package test;

/**
 *  It doesn't matter how two interfaces conflict.
 *  If at least one interface provides an implementation,
 *  the compiler reports an error, and the programmer must resolve the ambiguity.
 */
class Student2 implements Person, Named2 {
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return Person.super.getName();
    }
}
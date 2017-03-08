package test;

class Student implements Person, Named {
    @Override
    public long getId() {
        return 0;
    }

    /**
     * Чтобы разрешить конфликт с 2 дефолтными методами из 2х разных интерфейсов,
     * надо явно переопределить этот метод, и в нём вызывать какой-то 1 вариант.
     * @return имя
     */
    @Override
    public String getName() {
        // интересная форма вызова дефолтного метода.
        return Person.super.getName();
    }

}
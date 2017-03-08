package test;

abstract class ClassPerson3 {
     abstract long getId();
     abstract String getName();
     public String getClassName() {
         return this.getClass().getName();
     };
  }
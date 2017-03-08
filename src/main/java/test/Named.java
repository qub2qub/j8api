package test;

interface Named {

     default String getName() { return getClass().getName() + "_" + hashCode(); }

     default String  getClassName() { return getClass().getName() + "_" + hashCode(); }
  }
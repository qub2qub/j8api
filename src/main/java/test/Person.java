package test;

interface Person {
     long getId();
     default String getName() { return "John Q. Public"; }
  }
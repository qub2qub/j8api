package lambdasinaction.chap3;

public class Foo {
//    public int  myMethod(int param) { return 1; }
    public char myMethod(int param) { return 'a';}
    
    public static void main(String[] args) {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
//        portNumber = 2222;
    }
}
package lambdasinaction.chap8;

public class ShadowApp
{
    static int x;
    public static void main(String[] args)
    {
        x = 5;
        System.out.println("x = " + x);
        int x;
        x = 10;
        System.out.println("x = " + x);
        System.out.println("ShadowApp.x = " + ShadowApp.x);
    
        doSomething(new Task() {
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });
        
        doSomething((Task)() -> System.out.println("Danger danger!!"));
    }
    
    
    interface Task{
        public void execute();
    }
    public static void doSomething(Runnable r){ r.run(); }
    public static void doSomething(Task t){ t.execute(); }
}
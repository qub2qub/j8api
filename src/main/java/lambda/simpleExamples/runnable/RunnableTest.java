package lambda.simpleExamples.runnable;

/**
 * Created by dkorolev on 7/23/2016.
 */
public class RunnableTest {

    public static void main(String[] args) {

        //Annomymous Runnable
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("Hello from old style!");
            }
        };

        //Lambda Runnable
        Runnable r2 = () -> System.out.println("Hello from Lamda style.");

        //run em!
        r1.run();
        r2.run();

    }
}

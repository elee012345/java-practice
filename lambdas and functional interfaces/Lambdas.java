import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.Scanner;

public class Lambdas {

    public static void main( String[] args ) {

        System.out.println("What number do you want to square?");
        // gives the lambda to make a new user input scanner and read whatever the user inputs
        // make sure to actually give it a double!
        double num = Double.parseDouble( getInput( () -> ( new Scanner(System.in).nextLine() ) ) );
        // give it the lambda math to square a number, and give it the number to square as num
        double result = doMath( (x) -> {return x * x;}, num );
        String message = "The square of " + num + " is " + result  + ".";
        // takes a lambda to print whatever the consumer is given, gives the consumer 
        // our message variable to print
        doSomething( (x) -> { System.out.println(x); }, message );
    }

    /**
     * 
     * @param math the lambda math that you want the method to do as a Function lambda
     * @param inputNum the number you are applying the math to 
     * @return the result of doing math to your inputNum
     */
    public static double doMath( Function<Double, Double> math, double inputNum ) {
        return math.apply(inputNum);
    }

    /**
     * 
     * @param inputType the type of input (System.in, file scanner, etc.) as a Supplier lambda
     * @return whatever the scanner gets
     */
    public static String getInput( Supplier<String> inputType ) {
        return inputType.get();
    }

    /**
     * 
     * @param print takes a Consumer lambda for what you want to do to your message
     * @param message gives the message to the Consumer print method 
     */
    public static void doSomething( Consumer<String> print, String message ) {
        print.accept(message);
    }

}
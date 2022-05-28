import java.util.stream.*;
import java.util.Collection; 
import java.util.Arrays;
import java.util.Integer;
public class Streams {

    public static void main( String... args ) {
        // an integer array
        int[] intArray = { 938910384, 1, 2, 4, 1928, 934972, 8, 44};
        // converting int array to a filtered and sorted string array
        // trying to stream an int[] return an intstream and not a stream<Integer>
        String[] stringArray = Arrays.stream(intArray)
            // filter the stream to look for only numbers under 10
            .filter( x -> x < 10 )
            // sort the numbers
            .sorted()
            // turns the instream into a stream<integer>
            .mapToObj( x -> Integer.toString(x) )
            // turns the stream<integer> into a string[] array
            .toArray(String[]::new);
        // print out the filtered array
        System.out.println(Arrays.toString(stringArray));

        
    }
}
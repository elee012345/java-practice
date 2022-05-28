import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
public class Streams {

    public static void main( String... args ) {

        // streams work with all collections
        // they can also work with primitive arrays


        // an integer array
        int[] intArray1 = { 938910384, 1, 2, 4, 1928, 934972, 8, 44};
        // converting int array to a filtered and sorted string array
        // trying to stream an int[] return an intstream and not a stream<Integer>
        // does something similar to all primitive arrays (so array of doubles get DoubleStream)
        String[] stringArray1 = Arrays.stream(intArray1)
            // filter the stream to look for only numbers under 10
            .filter( x -> x < 10 )
            // sort the numbers
            .sorted()
            // turns the instream into a stream<integer>
            // same thing as x -> Integer.toString(x)
            .mapToObj(Integer::toString)
            // add the string " hi there" to the end of each number (now string)
            .map( x -> x + " hi there")
            // turns the stream<integer> into a string[] array
            .toArray(String[]::new);
        // print out the filtered array
        System.out.println(Arrays.toString(stringArray1));

        // make an array of strings
        String[] stringArray2 = { "hi", "jdiks,", "is0230so", "s", "", "0c0000coisj288", "di" };
        // make the string array a stream<string>
        List<String> stringList = Arrays.stream(stringArray2)
            // filter the stream to only include values length less than five and great than 0
            .filter( x -> x.length() < 5 && x.length() > 0 )
            // replace all 'i's with '!'
            .map( x -> x.replace('i', '!') )
            // sorts the stream
            .sorted()
            // converts the stream into a list<string>
            .collect( Collectors.toList() );
        System.out.println(stringList);

        // making a linkedlist
        LinkedList<Double> doubleLinkedList = new LinkedList<Double>();
        doubleLinkedList.add(3.1830);
        doubleLinkedList.add(9371.3);
        doubleLinkedList.add(33331654.2);
        doubleLinkedList.add(0.0281893);
        doubleLinkedList.add(-23.4);
        doubleLinkedList.add(1.0);

        // streaming the linkedlist
        Set<Double> doubleSet = doubleLinkedList.stream()
            // give us numbers with abs value greater than 3
            .filter( x -> Math.abs(x) > 3)
            // multiply all values by 3
            .map( x -> x * 3 )
            // sort all values
            .sorted()
            // turn into a set
            .collect( Collectors.toSet() );

        System.out.println(doubleSet);
    }
}
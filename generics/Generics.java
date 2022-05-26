public class Generics {

    public Generics() {

    }
    
    public static void main( String[] args ) {
        
        /*** Generic methods ***/

        // it does work lol
        // checking the class of the returned variable to make sure that it is a string
        if ( returnString(5.9).getClass() == String.class ) {
            System.out.println("It worked!");
        } else {
            System.out.println("It didn't work :(");
        }

        // works for all types of variables!
        if ( returnString(2).getClass() == String.class ) {
            System.out.println("It worked!");
        } else {
            System.out.println("It didn't work :(");
        }

        // works for arrays!
        if ( returnString( new int[] {1, 2, 3} ).getClass() == String.class ) {
            System.out.println("It worked!");
        } else {
            System.out.println("It didn't work :(");
        }

        /*** Generic classes ***/

    }


    // takes a variable of type <ConvertToStringType> and uses its toString method 
    // works for all variables
    public static <ConvertToStringType> String returnString( ConvertToStringType toStringVar ) {
        return toStringVar.toString();
    }

}
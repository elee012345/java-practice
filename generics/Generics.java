public class Generics <T>{

    T var1;
    public Generics( T inputVar1 ) {
        this.var1 = inputVar1;
    }

    public String returnAsStringPlusHi () {
        return var1.toString() + " Hi";
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

        // defining a generic class with type Double
        Generics<Double> genericClass1 = new Generics<>(3.0);
        // we are able to convert whatever we pass in into a string and then 
        // check that it is in fact a string 
        if ( genericClass1.returnAsStringPlusHi().getClass() == String.class ) {
            System.out.println( genericClass1.returnAsStringPlusHi() );
        } else {
            System.out.println("It didn't work :(");
        }
    }


    // takes a variable of type <ConvertToStringType> and uses its toString method 
    // works for all variables
    public static <ConvertToStringType> String returnString( ConvertToStringType toStringVar ) {
        return toStringVar.toString();
    }

}
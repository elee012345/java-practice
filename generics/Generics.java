public class Generics <type1, type2>{

    type1 var1;
    type2 var2;
    public Generics( type1 inputVar1, type2 inputVar2 ) {
        this.var1 = inputVar1;
        this.var2 = inputVar2;
    }

    public String addTogetherAsString() {
        return var1.toString() + var2.toString();
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

        // defining a generic class with type Double and Integer being passed into it
        Generics<Double, Integer> genericClass1 = new Generics<>(3.0, 2);

        // normally if we were to add a double and an integer you would get a double
        // if you were to add a string and something else you would get a string but that's 
        // besides the point 
        // we want to be able to add to numbers together as strings 
        // we've done this in this generic class
        // basically we are guarenteeing no matter what type of variable you pass in, 
        // you will get a string back
        if ( genericClass1.addTogetherAsString().getClass() == String.class ) {
            System.out.println( genericClass1.addTogetherAsString() );
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
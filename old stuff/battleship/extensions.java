import java.util.Arrays;
public class Extensions
{
    public static int letterToNum(String letter) {
        switch(letter) {
            case "A": return 1;
            case "B": return 2;
            case "C": return 3;
            case "D": return 4;
            case "E": return 5;
            case "F": return 6;
            case "G": return 7;
            case "H": return 8;
            case "I": return 9;
            case "J": return 10;
            default: return 0;
        }
    }
    
    public static String numToLetter(int num) {
        switch(num) {
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "E";
            case 6: return "F";
            case 7: return "G";
            case 8: return "H";
            case 9: return "I";
            case 10: return "J";
            default: return "Z";
        }
    }
    
    // if get something like (1A) will return [1, 1] or (5D) will return [4, 5] since it will try to return in form row, column
    // the coords in their form are in column, row
    // since this will get coords from the player.getCoords() method there won't be any invalid coordinates so it doesn't have to check for them
    public static int[] coordsToNum(String coords) {
        int[] coordsArr = {0, 0};
        // length 2 string 
        if ( coords.length() == 2 ) {
            coordsArr[0] = Extensions.letterToNum(coords.substring(1, 2));
            coordsArr[0] -= 1; // arrays start at 0 and not 1
            coordsArr[1] = Integer.parseInt( coords.substring(0, 1) );
            coordsArr[1] -= 1;
        // length 3 string 
        } else {
            coordsArr[0] = Extensions.letterToNum(coords.substring(2, 3));
            coordsArr[0] -= 1;
            coordsArr[1] = Integer.parseInt( coords.substring(0, 2) );
            coordsArr[1] -= 1;
        }
        return coordsArr;
    }
    
    public static void main(String[] args) { }
}
import java.util.Scanner; 
import java.util.Random;
import java.util.Arrays;
public class Player
{
    private Grid grid;
    
    public Player() {
        this.grid = new Grid();
    }
    
    public void printGrid()  {
        this.grid.printGrid();
    }
    
    public boolean placeShip( int[] coords, int shipID, boolean isVertical ) {
        return this.grid.placeShip(coords, shipID, isVertical);
    }
    
    // give coords in format ("column row") like (1A)
    // better implementation might have been to check coords against the actual grid
    public String getCoords() {
        Scanner scanner = new Scanner(System.in);
        String coords;
        System.out.println("Give me coordinates.");
        while ( true ) {
            
            coords = scanner.nextLine();
            coords = coords.toUpperCase();
            // checks if the coordinates are the right length
            if ( coords.length() != 2 && coords.length() != 3) {
                System.out.println("Give me proper coordinates.");
            // if its length 2 it checks if the first character (which is supposed to be a number) is a correct character 
            } else if ( coords.length() == 2 ) {
                // if the character isn't a number then the try will fail
                try {
                    // if the character is a bad number then this if will fail
                    if ( Extensions.numToLetter( Integer.parseInt( coords.substring(0, 1) ) ).equals("Z") ){
                        System.out.println("Give me proper coordinates.");
                    // if the character after that (the second character is supposed to be a letter) isn't correct then this doesn't work either
                    } else if ( Extensions.letterToNum( coords.substring(1, 2) ) == 0 ) {
                        System.out.println("Give me proper coordinates.");
                    // if nothing fails then it works
                    } else {
                        System.out.println("Valid coordinates.");
                        break;
                    }
                } catch ( Exception e ) {
                    System.out.println("Give me proper coordinates.");
                }
            } else if ( coords.length() == 3 ) {
                // if the character isn't a number then the try will fail
                try {
                    // if the character is a bad number then this if will fail
                    if ( Extensions.numToLetter( Integer.parseInt( coords.substring(0, 2) ) ).equals("Z") ){
                        System.out.println("Give me proper coordinates.");
                    // if the character after that (the third character is supposed to be a letter) isn't correct then this doesn't work either
                    } else if ( Extensions.letterToNum( coords.substring(2, 3) ) == 0 ) {
                        System.out.println("Give me proper coordinates.");
                    // if nothing fails then it works
                    } else {
                        System.out.println("Valid coordinates.");
                        break;
                    }
                } catch ( Exception e ) {
                    System.out.println("Give me proper coordinates.");
                }
            }
            
        }
        return coords;
    }
    
    public boolean pickOrientation() {
        while ( true ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What orientation do you want your ship to be? (horizontal or vertical)");
            String orientation = scanner.nextLine();
            if ( orientation.equalsIgnoreCase("vertical") ) {
                return true;
            } else if ( orientation.equalsIgnoreCase("horizontal") ) {
                return false;
            } else {
                System.out.println("You typed something wrong.");
            }
        }
    }
    
    public void printPickedShips() {
        this.grid.printPickedShips();
    }
    
    // returns ship id
    public int pickShip() {
        this.printPickedShips();
        Scanner scanner = new Scanner(System.in);
        while ( true ) {
            System.out.println("What ship do you want to pick? (ID or name)");
            String pickedShip = scanner.nextLine();
            if ( pickedShip.equalsIgnoreCase("1") || pickedShip.equalsIgnoreCase("Carrier") ) {
                if ( this.grid.ships[0].picked() ) {
                    System.out.println("You have already picked this ship.");
                } else {
                    this.grid.pickShip(1);
                    return 1;
                }
            } else if ( pickedShip.equalsIgnoreCase("2") || pickedShip.equalsIgnoreCase("Battleship") ) {
                if ( this.grid.ships[1].picked() ) {
                    System.out.println("You have already picked this ship.");
                } else {
                    this.grid.pickShip(2);
                    return 2;
                }
            } else if ( pickedShip.equalsIgnoreCase("3") || pickedShip.equalsIgnoreCase("Destroyer") ) {
                if ( this.grid.ships[2].picked() ) {
                    System.out.println("You have already picked this ship.");
                } else {
                    this.grid.pickShip(3);
                    return 3;
                }
            } else if ( pickedShip.equalsIgnoreCase("4") || pickedShip.equalsIgnoreCase("Submarine") ) {
                if ( this.grid.ships[3].picked() ) {
                    System.out.println("You have already picked this ship.");
                } else {
                    this.grid.pickShip(4);
                    return 4;
                }
            } else if ( pickedShip.equalsIgnoreCase("5") || pickedShip.equalsIgnoreCase("Patrol Ship") ) {
                if ( this.grid.ships[4].picked() ) {
                    System.out.println("You have already picked this ship.");
                } else {
                    this.grid.pickShip(5);
                    return 5;
                }
            } else {
                System.out.println("You typed something wrong.");
            }
        }
    }
    
    public void placeRandomShips() {
        // generates random orientations and coordinates until it comes 
        // up with one that fits on the grid
        Random random = new Random();
        while ( true ) {
            int ID;
            while ( true ) {
                ID = random.nextInt(5) + 1;
                if ( !this.grid.ships[ID - 1].picked() ) {
                    this.grid.pickShip(ID);
                    break;
                }
            }
            
            String rowCoords = Extensions.numToLetter(random.nextInt(10) + 1); // letters
            int colCoords = random.nextInt(10) + 1; // numbers
            String coords = colCoords + rowCoords;
            
            boolean isVertical = random.nextBoolean();
            // gets coords, converts to array, inputs into placeship method
            if ( this.placeShip( Extensions.coordsToNum( coords ) , ID, isVertical ) ) {
                // checks if all the ships have been placed yet
                if ( this.pickedAllShips() ) {
                    break;
                }
            }
        }
    }
    
    public boolean pickedAllShips() {
        return this.grid.pickedAllShips();
    }
    
    public int getEntityAtCoords(int[] coords) {
        return this.grid.getEntityAtCoords(coords);
    }
    
    public int[] shoot() {
        int[] coords = Extensions.coordsToNum( this.getCoords() );
        return coords;
    }
    
    // returns if all of the player's ships have been sunk aka if the player has lost
    public void getShot(int[] coords) {
        int entity = this.grid.getEntityAtCoords(coords);
        switch ( entity ) {
            case 0:
                // if player is getting shot the bot is shooting
                this.grid.botShoot(coords);
                break;
            case 1:
                this.grid.botShoot(coords);
                break;
            case 2:
                System.out.println("You've already shot here.");
                break;
       }
    }
    
    public void printHiddenGrid() {
        this.grid.printHiddenGrid();
    }
    
    public boolean hasLost() {
        return this.grid.hasLost();
    }
    
    public String getRandomCoords() {
        Random random = new Random();
        String rowCoords = Extensions.numToLetter(random.nextInt(10) + 1); // letters
        int colCoords = random.nextInt(10) + 1; // numbers
        String coords = colCoords + rowCoords;
        return coords;
    }
    
    public int[] randomShoot() {
        int[] coords = Extensions.coordsToNum( this.getRandomCoords() );
        return coords;
    }
    
    public String[][] getHiddenGridCopy() {
        return this.grid.getHiddenGridCopy();
    }
    
    public static void main(String[] args) { }
}
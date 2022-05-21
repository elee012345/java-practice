import java.util.*;
public class Grid
{
    public String[][] grid;
    public Ship shipOne;
    public Ship shipTwo;
    public Ship shipThree;
    public Ship shipFour;
    public Ship shipFive;
    public Ship[] ships;
    
    public Grid(){
        // iterating through grid just to put dashes (-) in it
        this.grid = new String[10][10];
        for ( int i = 0; i < 10; i++ ) {
            for ( int j = 0; j < 10; j++ ) {
                this.grid[i][j] = "-";
            }
        }
        shipOne = new Ship(1, "Carrier", 5);
        shipTwo = new Ship(2, "Battleship", 4);
        shipThree = new Ship(3, "Destroyer", 3);
        shipFour = new Ship(4, "Destroyer", 3);
        shipFive = new Ship(5, "Patrol Boat", 2);
        ships = new Ship[]{ shipOne, shipTwo, shipThree, shipFour, shipFive };
    }
    
    
    public class Ship {
        private boolean picked;
        private int length;
        private int id;
        private String name;
        public Ship(int inputID, String inputName, int inputLength) {
            this.picked = false;
            this.length = inputLength;
            this.id = inputID;
            this.name = inputName;
        }
        public boolean picked(){
            return this.picked;
        }
        public int getLength(){
            return this.length;
        }
        public int getID() {
            return this.id;
        }
        public String getName() {
            return this.name;
        }
        public void pick() {
            this.picked = true;
        }
        public void unPick() {
            this.picked = false;
        }
    }
    
    // just prints the grid with the numbers and letters next to it
    public void printGrid() {
        System.out.print("  ");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print(i + 1 + " ");
        }
        System.out.println("");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print(Extensions.numToLetter(i + 1) + " ");
            for ( int j = 0; j < 10; j++ ) {
                System.out.print(this.grid[i][j] + " ");
            }
            System.out.println(Extensions.numToLetter(i + 1));
        }
        System.out.print("  ");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print(i + 1 + " ");
        }
        System.out.println("");
        System.out.println("");
    }
    
    // coords should always be valid
    // as in the actual coordinates should be valid but not necessarily 
    // how the boat is placed according to them
    // coords refer always to left or top of ship 
    // so arguments of ( [1, 5], 3, true ) would mean that you have a ship length 3,
    // its vertical, and that its top edge is at coordinates 1, 5
    /**
     * @return returns whether or not the coordinates for placing the boat are good
     * 
     */
    public boolean placeShip(int[] coords, int shipID, boolean isVertical){
        int id = shipID;
        int length = this.ships[id - 1].getLength();
        // making a copy of the grid
        // we make a copy first and check the coordinates against the copy 
        // that way we don't accidentally make changes to the original
        String[][] gridCopy = new String[10][10];
        for ( int i = 0; i < 10; i++ ) {
            for ( int j = 0; j < 10; j++ ) {
                gridCopy[i][j] = this.grid[i][j];
            }
        }
        if ( isVertical ) {
            // fails try if the ship is too long and goes off the board (out of the array)
            try {
                for ( int i = 0; i < length; i++ ) {
                    // also fails if you try to place a boat on top of another ship
                    if ( gridCopy[coords[0] + i][coords[1]].equals("\\") ) {
                        this.ships[id - 1].unPick();
                        return false;
                    }
                    // setting the boat
                    gridCopy[coords[0] + i][coords[1]] = "\\";
                }
                
                // if any of the above fail then this part won't run
                // makes the actual saved grid equal to the copy of the grid 
                this.grid = gridCopy;
                return true;
            } catch ( Exception ArrayIndexOutOfBoundsException ) {
                this.ships[id - 1].unPick();
                return false;
            }
        } else {
            try {
                for ( int i = 0; i < length; i++ ) {
                    if ( gridCopy[coords[0]][coords[1] + i].equals("\\") ) {
                        this.ships[id - 1].unPick();
                        return false;
                    }
                    gridCopy[coords[0]][coords[1] + i] = "\\";
                }
                // make this.grid equal to gridCopy
                this.grid = gridCopy;
                return true;
            } catch ( Exception ArrayIndexOutOfBoundsException ) {
                this.ships[id - 1].unPick();
                return false;
            }
        }
    }
    
    public String booleanToString( boolean inputBoolean ) {
        if ( inputBoolean ) return "Yes";
        return "No";
    }
    
    public void printPickedShips() {
        System.out.println("~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
        System.out.println("| ID | Name        | Size | Picked |");
        System.out.println("|----------------------------------|");
        System.out.println("| 1. | Carrier     | 5    | " + booleanToString(shipOne.picked()) + "     |");
        System.out.println("| 2. | Battleship  | 4    | " + booleanToString(shipTwo.picked()) + "     |");
        System.out.println("| 3. | Destroyer   | 3    | " + booleanToString(shipThree.picked()) + "     |");
        System.out.println("| 4. | Submarine   | 3    | " + booleanToString(shipFour.picked()) + "     |");
        System.out.println("| 5. | Patrol Ship | 2    | " + booleanToString(shipFive.picked()) + "     |");
        System.out.println("~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
    }
    
    public void pickShip(int id) {
        switch(id) {
            case 1: 
                shipOne.pick(); 
                break;
            case 2: 
                shipTwo.pick();
                break;
            case 3: 
                shipThree.pick();
                break;
            case 4:
                shipFour.pick();
                break;
            case 5:
                shipFive.pick();
                break;
        }
    }
    
    public boolean pickedAllShips() {
        for ( int i = 0; i < 5; i++ ) {
            // if any of the ships haven't been picked then all the ships haven't been picked
            if ( !this.ships[i].picked() ) {
                return false;
            }
        }
        return true;
    }
    
    // if it returns 0 it means it is an unoccupied position, if it returns 1 it is a ship, if it returns 2 is a spot that has already been shot
    // -1 idek what that means
    public int getEntityAtCoords(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        if ( this.grid[x][y].equals("-") ) {
            return 0;
        } else if ( this.grid[x][y].equals("\\") ) {
            return 1;
        } else if ( this.grid[x][y].equals("O") || this.grid[x][y].equals("X") ) {
            return 2;
        } else {
            return -1;
        }
    }
    
    public void playerShoot(int[] coords) {
        int entity = this.getEntityAtCoords(coords);
        switch ( entity ) {
            case 0:
                System.out.println("You missed!");
                this.grid[coords[0]][coords[1]] = "O";
                break;
            case 1:
                System.out.println("A hit!");
                this.grid[coords[0]][coords[1]] = "X";
                break;
            case 2:
                System.out.println("You've already shot here?...");
                break;
        }
    }
    
    // removes prints
    public void botShoot(int[] coords) {
        int entity = this.getEntityAtCoords(coords);
        switch ( entity ) {
            case 0:
                this.grid[coords[0]][coords[1]] = "O";
                break;
            case 1:
                this.grid[coords[0]][coords[1]] = "X";
                break;
            case 2:
                break;
        }
    }
    
    public void printHiddenGrid() {
        System.out.print("  ");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print(i + 1 + " ");
        }
        System.out.println("");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print(Extensions.numToLetter(i + 1) + " ");
            for ( int j = 0; j < 10; j++ ) {
                if ( this.grid[i][j].equals("\\") ) {
                    System.out.print("- ");
                } else {
                    System.out.print(this.grid[i][j] + " ");
                }
            }
            System.out.println(Extensions.numToLetter(i + 1));
        }
        System.out.print("  ");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print(i + 1 + " ");
        }
        System.out.println("");
        System.out.println("");
    }
    
    // if it sees any ships it returns false
    // else it returns true
    public boolean allShipsSunk() {
        for ( int i = 0; i < 10; i++ ) {
            for ( int j = 0; j < 10; j++ ) {
                if ( this.grid[i][j].equals("\\") ) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean hasLost() {
        return this.allShipsSunk();
    }
    
    public String[][] getHiddenGridCopy() {
        String[][] hiddenGridCopy = new String[10][10];
        for ( int i = 0; i < 10; i++ ) {
            for ( int j = 0; j < 10; j++ ) {
                if ( this.grid[i][j].equals("\\") ) {
                    hiddenGridCopy[i][j] ="-";
                } else {
                    hiddenGridCopy[i][j] = this.grid[i][j];
                }
            }
        }
        return hiddenGridCopy;
    }
    
    public static void main(String[] args) { }
}
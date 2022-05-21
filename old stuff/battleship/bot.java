import java.util.Random;
import java.util.Arrays;
public class Bot
{
    private Grid grid;
    
    public Bot() {
        this.grid = new Grid();
    }
    
    public void printGrid()  {
        this.grid.printGrid();
    }
    
    public boolean placeShip( int[] coords, int shipID, boolean isVertical ) {
        return this.grid.placeShip(coords, shipID, isVertical);
    }
    
    public String getCoords() {
        Random random = new Random();
        String rowCoords = Extensions.numToLetter(random.nextInt(10) + 1); // letters
        int colCoords = random.nextInt(10) + 1; // numbers
        String coords = colCoords + rowCoords;
        return coords;
    }
    
    public boolean pickOrientation() {
        Random random = new Random();
        return random.nextBoolean();
    }
    
    public int pickShip() {
        Random random = new Random();
        while ( true ) {
            int id = random.nextInt(5) + 1;
            if ( !this.grid.ships[id - 1].picked() ) {
                this.grid.pickShip(id);
                return id;
            }
        }
    }
    
    public boolean pickedAllShips() {
        return this.grid.pickedAllShips();
    }

    public void printHiddenGrid() {
        this.grid.printHiddenGrid();
    }
    
    // needs to be a bot that intelligently picks where to shoot
    // logic: if it sees that it has hit a ship, it checks around it 
    // then it checks up, down, left, and right and sees if any of those positions have another hit ship 
    // if one of those directions does have one, then it keeps moving that way until it hits the end of the grid,
    // a spot that has been shot and missed, or a spot that hasn't been shot yet 
    // if it finds a spot that hasn't been shot yet, then it shoots there
    // if it can't do any of that, then 
    // it checks up, down, left, right, in a random order and sees if any of those spots 
    // have been shot at yet or are off the grid 
    // if none of those conditions are satisfied, then it shoots there
    // basically it sees a ship that it's already shot and tries to sink it 
    // if it can't shoot around that ship that has been hit, it moves on to the next one, and so on 
    // it does this until it can shoot around a ship, or it reaches the end of the board 
    // if it can't shoot around any of the ships, then it shoots a random spot that hasn't been shot at yet.
    public int[] shoot(String[][] hiddenEnemyGrid) {
        for ( int i = 0; i < 10; i++ ) {
            for ( int j = 0; j < 10; j++ ) {
                if ( hiddenEnemyGrid[i][j].equals("X") ) {
                    boolean[] northSouthEastWest = new boolean[4];
                    while ( true ) {
                        Random random = new Random();
                        int direction = random.nextInt(4);
                        // if it hasn't been picked yet
                        if ( !northSouthEastWest[direction] ) {
                            // tries to shoot in a direction, breaks
                            // i is rows, j is columns
                            // i increases from top to bottom, j increases from left to right
                            switch ( direction ) {
                                case 0:
                                    // shoot north 
                                    if ( i - 1 > -1 && i - 1 < 10) {
                                        if ( hiddenEnemyGrid[i - 1][j].equals("-") ) {
                                            // try to shoot there
                                            return new int[] {i - 1, j};
                                        }
                                    }
                                    northSouthEastWest[0] = true;
                                case 1:
                                    // shoot south 
                                    if ( i + 1 > -1 && i + 1 < 10) {
                                        if ( hiddenEnemyGrid[i + 1][j].equals("-") ) {
                                            // try to shoot there
                                            return new int[] {i + 1, j};
                                        }
                                    }
                                    northSouthEastWest[1] = true;
                                case 2:
                                    // shoot east 
                                    if ( j + 1 > -1 && j + 1 < 10) {
                                        if ( hiddenEnemyGrid[i][j + 1].equals("-") ) {
                                            // try to shoot there
                                            return new int[] {i, j + 1};
                                        }
                                    } 
                                    northSouthEastWest[2] = true;
                                case 3:
                                    // shoot west 
                                    if ( j - 1 > -1 && j - 1 < 10) {
                                        if ( hiddenEnemyGrid[i][j - 1].equals("-") ) {
                                            // try to shoot there
                                            return new int[] {i, j - 1};
                                        }
                                    } 
                                    northSouthEastWest[3] = true;
                            }
                        }
                        // breaks if they've all been picked
                        if ( northSouthEastWest[0] && northSouthEastWest[1] && northSouthEastWest[2] && northSouthEastWest[3] ) {
                            break;
                        }
                    }
                    for ( int k = 0; k < 4; k++ ) {
                        northSouthEastWest[k] = false;
                    }
                }
            }
        }
        while ( true ) {
            int[] coords = Extensions.coordsToNum( this.getCoords() );
            if ( hiddenEnemyGrid[coords[0]][coords[1]].equals("-") ) {
                return coords;
            }
        }
    }
    
    public boolean getShot(int[] coords) {
        int entity = this.grid.getEntityAtCoords(coords);
        switch ( entity ) {
            case 0:
                // if bot is getting shot the player is shooting
               this.grid.playerShoot(coords);
               break;
            case 1:
               this.grid.playerShoot(coords);
               if ( this.grid.allShipsSunk() ) return true;
                break;
            case 2:
                break;
       }
       return false;
    }
    
    public boolean hasLost() {
        return this.grid.hasLost();
    }
    
    public String[][] getHiddenGridCopy() {
        return this.grid.getHiddenGridCopy();
    }
}
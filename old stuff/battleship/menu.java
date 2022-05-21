/*
 * everything is called through the menu class
 * this way everything is all nice and neat
 * these are the 'commands'
 */
import java.util.*;
public class Menu
{
    private Player player;
    private Bot bot;
    
    public Menu() {
        this.player = new Player();
        this.bot = new Bot();
    }
    
    public void displayOwnGrid() {
        this.player.printGrid();
    }
    
    public void displayEnemyGrid() {
        this.bot.printGrid();
    }
    
    public void placeShips() {
        while ( true ) {
            int id = this.player.pickShip();
            boolean isVertical = player.pickOrientation();
            // gets coords, converts to array, inputs into placeship method
            if ( this.player.placeShip( Extensions.coordsToNum( player.getCoords() ), id, isVertical ) ) {
                this.player.printGrid();
                if ( this.player.pickedAllShips() ) {
                    break;
                }
            } else {
                System.out.println("The ship doesn't fit on the board.");
            }
        }
    }
    
    public void randomShips() {
        this.player.placeRandomShips();
    }
    
    public void enemyPlaceShips() {
        while ( true ) {
            int id = bot.pickShip();
            boolean isVertical = bot.pickOrientation();
            // gets coords, converts to array, inputs into placeship method
            if ( this.bot.placeShip( Extensions.coordsToNum( bot.getCoords() ), id, isVertical ) ) {
                if ( this.bot.pickedAllShips() ) {
                    break;
                }
            }
        }
    }
    
    public void playerShoot() {
        int[] coords = this.player.shoot();
        this.bot.getShot(coords);
        this.bot.printHiddenGrid();
    }
    
    public void playerRandomShoot() {
        int[] coords = this.player.randomShoot();
        this.bot.getShot(coords);
        this.bot.printHiddenGrid();
    }
    
    public void enemyShoot() {
        int[] coords = this.bot.shoot(this.player.getHiddenGridCopy());
        this.player.getShot(coords);
        System.out.println("The enemy has shot! Your grid now looks like this to them:");
        this.player.printHiddenGrid();
        System.out.println("This is your grid as it looks for you:");
        this.player.printGrid();
    }
    
    public void displayHiddenOwnGrid() {
        this.player.printHiddenGrid();
    }
    
    public void displayHiddenEnemyGrid() {
        this.bot.printHiddenGrid();
    }
    
    public void waitForContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type continue to continue...");
        while ( true ) {
            if ( scanner.nextLine().equalsIgnoreCase("continue") ) {
                break;
            }
        }
    }
    
    public void shootCycle() {
        while ( true ) {
            this.playerShoot();
            if ( this.bot.hasLost() ) {
                System.out.println("You win!");
                break;
            }
            this.waitForContinue();
            System.out.println("The enemy is shooting...");
            this.enemyShoot();
            if ( this.player.hasLost() ) {
                System.out.println("You lose!");
                break;
            }
        }
    }
    
    public void fastShootCycle() {
        while ( true ) {
            System.out.println("ur shot");
            this.playerRandomShoot();
            if ( this.bot.hasLost() ) {
                System.out.println("You win!");
                break;
            }
            System.out.println("bot's shot");
            this.enemyShoot();
            if ( this.player.hasLost() ) {
                System.out.println("You lose!");
                break;
            }
        }
    }
}
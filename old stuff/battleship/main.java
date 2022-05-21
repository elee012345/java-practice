
public class Main
{
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayOwnGrid();
        System.out.println("Time to place your ships.");
        menu.placeShips();
        menu.enemyPlaceShips();
        System.out.println("All ships have been placed. You may now start to fire.");
        menu.shootCycle();
    }
}

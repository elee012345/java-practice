import java.util.*;
public class PlayYahtzee {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        // getting the number of players
        int numPlayers;
        String numPlayersString;
        while ( true ) {
            System.out.println("How many players? (1-10)");
            // takes the number of players in with a string and then
            // tries to convert that string to an integer
            numPlayersString = scanner.nextLine();
            try {
                numPlayers = Integer.parseInt(numPlayersString);
                // checks if the user inputted a valid number of players
                if ( numPlayers >= 10 || numPlayers <= 0 ) {
                    System.out.println("That's not a valid number of players.");
                } else {
                    // basically checks if the user input is not valid,
                    // and if it is not not valid (it is valid), then it breaks the loop
                    break;
                }
            } catch (Exception e) {
                System.out.println("Give me a number.");
            }
        }
        
        // basicaly does the exact same thing as the block of code above
        String numRoundsString;
        int numRounds;
        while ( true ) {
            System.out.println("How many rounds do you want to play (max of 10)?");
            try {
                numRoundsString = scanner.nextLine();
                numRounds = Integer.parseInt(numRoundsString);
                if ( numRounds > 10 || numRounds < 1) System.out.println("Invalid number of rounds.");
                else break;
            } catch ( Exception e ) {
                System.out.println("Invalid input.");
            }
        }
        
        // creates a list of the some player classes 
        // creates them all based on the number of players that the program asked for above 
        Player[] playersList = new Player[10];
        for ( int i = 0; i < 10; i++ ) {
            if ( numPlayers >= (i + 1) ) playersList[i] = new Player();
        }

        // all the good stuffs happens here 
        // loops through the number of rounds, then turns, then players 
        // it makes the player roll their dice (includes holding and whatever)
        // then it makes the player score that roll
        // all the code to make it do this stuff is in the player class
        for ( int round = 0; round < numRounds; round++ ) {
            for ( int turn = 0; turn < 13; turn++ ) {
                for ( int player = 0; player < numPlayers; player++ ) {
                    System.out.println("Time for player " + ( player + 1 ) + "!");
                    System.out.println("It is round " + (round + 1) + ", turn " + ( turn + 1 ) + ", player " + ( player + 1) + ".");
                    int[] rolls = playersList[player].roll();
                    playersList[player].scoreRoll(rolls);
                }
            }
            // at the end of each round it goes through all the players and scores that round 
            // this also prints the round score and adds the round score to the player's total score
            for ( int i = 0; i < numPlayers; i++ ) {
                System.out.println("Player " + ( i + 1 )  + "'s score:");
                playersList[i].scoreRound();
                System.out.println("");
            }
        }
        
        // now that the game is over, we get all of the players' total scores 
        // this method/function just prints the total score of the player 
        int[] playerScores = new int[10];
        for ( int i = 0; i < numPlayers; i++ ) {
            playerScores[i] = playersList[i].getTotalScore();
        }
        
        // now we're finding the winners
        // we're finding the first, second, and third highest scores
        int highest = Integer.MIN_VALUE;
        int highestIndex = 0;
        int secondHighest = Integer.MIN_VALUE;
        int secondHighestIndex = 0;
        int thirdHighest = Integer.MIN_VALUE;
        int thirdHighestIndex = 0;
        for (int i = 0; i < playerScores.length; i++) {
            // if it finds a new high, then it moves all the other spots down
            // then it sets the current value to the new highest value 
            // it also saves the index 
            if (playerScores[i] > highest) {
                thirdHighest = secondHighest;
                secondHighest = highest;
                highest = playerScores[i];
                highestIndex = i;
            // if it doesn't find a new high, it checks for a second high 
            // if the value in the list is greater than the current second highest then 
            // it is now the second highest
            } else if (playerScores[i] > secondHighest) {
                secondHighest = playerScores[i];
                secondHighestIndex = i;
            // literally the same thing as the above else if
            } else if (playerScores[i] > thirdHighest ) {
                thirdHighest = playerScores[i];
                thirdHighestIndex = i;
            }
        }
        
        // just printing the winners
        // doesn't print out the people who got 0 points
        // if they got 0 points they weren't playing 
        System.out.println("Player " + ( highestIndex + 1) + " got first place! They got " + highest + " points.");
        if ( secondHighest != 0 ) {
            System.out.println("Player " + ( secondHighestIndex + 1 ) + " got second place! They got " + secondHighest + " points.");
            if ( thirdHighest != 0 ) {
                System.out.println("Player " + ( thirdHighestIndex + 1 ) + " got third place! They got " + thirdHighest + " points.");
            }
        }
    }
}

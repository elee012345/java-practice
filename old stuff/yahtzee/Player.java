import java.util.*;

public class Player {
    
    private LinkedHashMap<String, Integer> scores;
    // don't feel like making a new scanner every time 
    private Scanner scanner;
    private int totalScore;

    public Player() {
        this.scores = new LinkedHashMap<String, Integer>();
        this.scanner = new Scanner(System.in);
        this.totalScore = 0;
    }
    
    // just a getter method for the player's total score 
    protected int getTotalScore() {
        return this.totalScore;
    }
    
    // this method makes the player roll the dice 
    // it has rolling, holding, etc.
    // so not JUST rolling the dice 
    protected int[] roll() {
        // random object for dice rolls
        Random random = new Random();
        
        
        int[] holds = new int[5];
        int[] rolls = new int[5];
        int hold;
        boolean holdRollOne = false;
        boolean holdRollTwo = false;
        boolean holdRollThree = false;
        boolean holdRollFour = false;
        boolean holdRollFive = false;
        // the player gets three rolls 
        for ( int i = 0; i < 3; i++ ) {
            // only sets the dice to be rolled as the dice that 
            // aren't held 
            for ( var j = 0; j < holds.length; j++ ) {
                hold = holds[j];
                if ( hold == 1) holdRollOne = true;
                if ( hold == 2 ) holdRollTwo = true;
                if ( hold == 3 ) holdRollThree = true;
                if ( hold == 4 ) holdRollFour = true;
                if ( hold == 5 ) holdRollFive = true;
            }
            
            // rolls the dice depending on the variable set above
            // rolls them if they are set to not be held, 
            // not if they are set to be rolled 
            // random.nextInt(6) returns ints from 0 - 5 so you add 1 to them
            // adds the rolls to the rolls array 
            if ( holdRollOne == false ) rolls[0] = random.nextInt(6) + 1;
            if ( holdRollTwo == false ) rolls[1] = random.nextInt(6) + 1;
            if ( holdRollThree == false ) rolls[2] = random.nextInt(6) + 1;
            if ( holdRollFour == false ) rolls[3] = random.nextInt(6) + 1;
            if ( holdRollFive == false ) rolls[4] = random.nextInt(6) + 1;
            
            // resets the holds since we have already rolled the dice based on the holds
            holds = new int[5];
            // prints the rolls so that the player can decide what to do with them
            System.out.println("Rolls are " + Arrays.toString(rolls));
            
            // it doesn't matter what the player does on their last roll because 
            // they will be scoring their roll directly after 
            // this means the player can only sort, hold, etc. on turns 1 and 2 
            if ( i < 2 ) {
                boolean rollDone = false;
                while ( rollDone != true ) {
                    // options for what the user can do 
                    System.out.println("What do you want to do next?");
                    System.out.println("Sort your dice (sort) | End your turn (end) | Hold (hold) | Next roll (next) | List your rolls (list) | Show scores (scores)");
                    // reads what the user wants to do 
                    String toDo = this.scanner.nextLine();
                    // if the player wants to end their turn it returns the rolls 
                    // this will end the method 
                    if ( toDo.equals("end") ) {
                        return rolls;
                    // holds
                    } else if ( toDo.equals("hold") ) {
                        while ( true ) {
                            try {
                                System.out.println("What do you want to hold?");
                                // the user inputs their holds in the form 1, 2, 3, 4, 5
                                // so if they had the rolls 3, 4, 1, 2, 3 and they wanted to hold the first two rolls,
                                // they would type in 1, 2
                                String tempVar = this.scanner.nextLine();
                                // idk how this thing works but it basically splits the input string 
                                // mentioned above into an array 
                                holds = Arrays.stream(tempVar.split(", ")).mapToInt(Integer::parseInt).toArray();
                                break;
                            } catch( Exception e ) {
                                System.out.println("Invalid input");
                            }
                        }
                        rollDone = true;
                    // sorts rolls 
                    } else if ( toDo.equals("sort") ) {
                        Arrays.sort(rolls);
                        System.out.println("Sorted rolls: " + Arrays.toString(rolls));
                    // lists your rolls 
                    } else if ( toDo.equals("list") ) {
                        System.out.println("Dice are: " + Arrays.toString(rolls));
                    // continues with the player's turn without doing anything 
                    } else if ( toDo.equals("next") ) {
                        rollDone = true;
                    // tells the player their scores so that they know what to 
                    // do and strategize what to score their rolls as 
                    } else if ( toDo.equals("scores") ) {
                        this.printScores();
                    }
                }
            }
        }
        return rolls;
    }
    
    // one of the many methods that scores your roll 
    // this one just takes the array of rolls and the number you want to score 
    // it adds all the numbers up and thats your score 
    private int scoreNumber( int[] rolls, int numToScore ) {
        int numScore = 0;
        for ( int num : rolls ) {
            if ( num == numToScore ) numScore += num;
        } 
        return numScore;
    }
    
    // scores three of a kind, four of a kind, or full house 
    // also scores yahtzee 
    private int scoreSameNumbers( int[] rolls, int threeOrFourOfAKindOrFullHouse ) {
        int sameNumberScore = 0;
        // checks the amount of each number and stores it
        int numOnes = 0, numTwos = 0, numThrees = 0, numFours = 0, numFives = 0, numSixes = 0;
        for ( int diceNum : rolls ) {
            switch ( diceNum ) {
                case 1:
                    numOnes++;
                    break;
                case 2:
                    numTwos++;
                    break;
                case 3:
                    numThrees++;
                    break;
                case 4:
                    numFours++;
                    break;
                case 5:
                    numFives++;
                    break;
                case 6:
                    numSixes++;
                    break;
            }
        }
        // checks for doubles and triples for the full house
        if ( threeOrFourOfAKindOrFullHouse == 23 ) {
            int doubles = 0;
            if ( numOnes == 2 ) doubles = 1;
            else if ( numTwos == 2 ) doubles = 2;
            else if ( numThrees == 2 ) doubles = 3;
            else if ( numFours == 2 ) doubles = 4;
            else if ( numFives == 2) doubles = 5;
            else if ( numSixes == 2 ) doubles = 6;
            int triples = 0;
            if ( numOnes == 3 ) triples = 1;
            else if ( numTwos == 3 ) triples = 2;
            else if ( numThrees == 3 ) triples = 3;
            else if ( numFours == 3 ) triples = 4;
            else if ( numFives == 3) triples = 5;
            else if ( numSixes == 3 ) triples = 6;
            // determines whether its a full house or not 
            if ( doubles != triples && doubles != 0 && triples != 0) {
                sameNumberScore = 25;
            }
        // determines if the number of the same dice are greater than or 
        // equal to the 'blank' of the "blank of a kind" of dice 
        } else if ( numOnes >= threeOrFourOfAKindOrFullHouse || numTwos >= threeOrFourOfAKindOrFullHouse || numThrees >= threeOrFourOfAKindOrFullHouse || numFours >= threeOrFourOfAKindOrFullHouse || numFives >= threeOrFourOfAKindOrFullHouse || numSixes >= threeOrFourOfAKindOrFullHouse ) {
            // adds up all the dice
            for ( int diceNum : rolls ) {
                sameNumberScore += diceNum;
            }
        }
        return sameNumberScore;
    }
    
    // scores a straight 
    private int scoreStraight( int[] rolls, int desiredStraightLength ) {
        int actualStraightLength = 0;
        // needs to sort the array to check it 
        Arrays.sort(rolls);
        
        // converting array to set to remove duplicates
        // needs to do this in case its something like: 1, 2, 2, 3, 4 
        // or something like that 
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        // making it a set
        for (int i = 0; i < rolls.length; i++) {
            set.add(rolls[i]);
        }
        int setLength = set.size();
        // making the set back into an array 
        rolls = new int[setLength];
        int thing = 0;
        for (int roll : set) {
            rolls[thing++] = roll;
        }
  
        // logic behind this:
        // you take the next value in the array and if it is one greater 
        // than the current element, and this happens for all 
        // elements, then that means that it is all in a row 
        // that means that it is a straight 
        // when that stops happening it breaks the loop 
        // to stop recording the length because it is no longer longer than it is 
        // the actual straight length variable actually records the parts of the straight
        // that are strung together
        // example: 1, 2, 3, 4, 5 
        // 1-2 is one added to the variable, 2-3 is another, and so on 
        for ( int i = 0; i < (rolls.length - 1); i++ ) {
            if ( rolls[i + 1] - rolls[i] == 1 ) actualStraightLength++;
            else break;
        }
        // subtracts one from the desiredStraightLength because of the way the 
        // straight length is measured above 
        if ( actualStraightLength >= desiredStraightLength - 1) {
            if ( desiredStraightLength == 4 ) return 30;
            else return 40;
        }
        return 0;
    }
    
    // literally justs adds all of the dice values together
    private int scoreChance(int[] rolls) {
        int score = 0;
        for ( int dice : rolls ) {
            score += dice;
        }
        return score;
    }
    // checks what the score for a section is and returns 
    // the value as a string 
    // returns "Not scorefd yet" or someting like that if not scored
    private String getSectionScore( String section ) {
        // if the score isn't null then it returns the score as string or else it returns "not scored yet"
        String sectionScore = ( this.scores.get(section) != null ) ? Integer.toString(this.scores.get(section)) : "Not scored yet.";
        return sectionScore;
    }
    
    // scores the round and adds the round score to the payer's total score 
    protected void scoreRound() {
        int roundScore = ( Integer.parseInt(getSectionScore("ones")) + Integer.parseInt(getSectionScore("twos")) + 
            Integer.parseInt(getSectionScore("threes")) + Integer.parseInt(getSectionScore("fours")) + 
            Integer.parseInt(getSectionScore("fives")) + Integer.parseInt(getSectionScore("sixes")) +
            Integer.parseInt(getSectionScore("three of a kind")) + 
            Integer.parseInt(getSectionScore("four of a kind")) + Integer.parseInt(getSectionScore("full house")) + 
            Integer.parseInt(getSectionScore("small straight")) + Integer.parseInt(getSectionScore("large straight")) +
            Integer.parseInt(getSectionScore("yahtzee")) + Integer.parseInt(getSectionScore("chance")) 
        );
        // the player gets bonus points if they scored enough points
        if ( getSectionScore("bonus") == "35" ) {
            roundScore += Integer.parseInt(getSectionScore("bonus"));
        } else {
            System.out.println("They didn't get the bonus points :(");
        }
        System.out.println("Their round score is " + roundScore);
        this.totalScore += roundScore;
        System.out.println("Their total score is " + this.totalScore);
        roundScore = 0;
        this.scores.clear();
    }
    
    // literally just prints out the player's current round score 
    private void printScores() {
        System.out.println("Your scores: ");
        System.out.println("Ones: " + getSectionScore("ones"));
        System.out.println("Twos: " + getSectionScore("twos"));
        System.out.println("Threes: " + getSectionScore("threes"));
        System.out.println("Fours: " + getSectionScore("fours"));
        System.out.println("Fives: " + getSectionScore("fives"));
        System.out.println("Sixes: " + getSectionScore("sixes"));
        System.out.println("Bonus points: " + getSectionScore("bonus"));
        System.out.println("Three of a kind: " + getSectionScore("three of a kind"));
        System.out.println("Four of a kind: " + getSectionScore("four of a kind"));
        System.out.println("Full house: " + getSectionScore("full house"));
        System.out.println("Small straight: " + getSectionScore("small straight"));
        System.out.println("Large straight: " + getSectionScore("large straight"));
        System.out.println("Yahtzee: " + getSectionScore("yahtzee"));
        System.out.println("Chance: " + getSectionScore("chance"));
    }
    // scoring the rolls 
    protected void scoreRoll( int[] rolls ) {
        System.out.println("What do you want to score your roll as?");
        System.out.println("Ones (1 point for each 1) | Twos (2 points for each 2) | Threes (3 points for each 3)");
        System.out.println("Fours (4 points for each 4) | Fives (5 points for each 5) | Sixes (6 points for each 6)");
        System.out.println("If the above add up to at least 65, you earn 35 bonus points.");
        System.out.println("Three of a kind (total of all dice) | Four of a kind (total of all dice)");
        System.out.println("Full House (25 points) | Small Straight (30 points) | Large Straight (40 points)");
        System.out.println("Yahtzee (50 points) | Chance (total of all dice)");
        Arrays.sort(rolls);
        boolean doneScoringRolls = false;
        while ( doneScoringRolls == false ) {
            System.out.println("What do you want to score your roll as?");
            String scoreAs = this.scanner.nextLine();
            scoreAs.toLowerCase();
            // can't score a section if it's already been scored 
            if ( this.scores.get(scoreAs) != null ) {
                System.out.println("You've already scored this section");
            } else {
                doneScoringRolls = true;
                // tries to score the rolls 
                if ( scoreAs.equals("ones") ) this.scores.put( "ones", scoreNumber(rolls, 1) );
                else if ( scoreAs.equals("twos") ) this.scores.put( "twos", scoreNumber(rolls, 2) );
                else if ( scoreAs.equals("threes") ) this.scores.put( "threes", scoreNumber(rolls, 3) );
                else if ( scoreAs.equals("fours") ) this.scores.put( "fours", scoreNumber(rolls, 4) );
                else if ( scoreAs.equals("fives") ) this.scores.put( "fives", scoreNumber(rolls, 5) );
                else if ( scoreAs.equals("sixes") ) this.scores.put( "sixes", scoreNumber(rolls, 6) );
                else if ( scoreAs.equals("three of a kind") ) this.scores.put( "three of a kind", scoreSameNumbers(rolls, 3) );
                else if ( scoreAs.equals("four of a kind") ) this.scores.put( "four of a kind", scoreSameNumbers(rolls, 4) );
                else if ( scoreAs.equals("full house") ) this.scores.put( "full house", scoreSameNumbers(rolls, 23) );
                else if ( scoreAs.equals("small straight") ) this.scores.put( "small straight", scoreStraight(rolls, 4) );
                else if ( scoreAs.equals("large straight") ) this.scores.put( "large straight", scoreStraight(rolls, 5) );
                else if ( scoreAs.equals("yahtzee") ) this.scores.put( "yahtzee", scoreSameNumbers(rolls, 5) );
                else if ( scoreAs.equals("chance") ) this.scores.put( "chance", scoreChance(rolls) );
                // repeats this if the user doesn't input a valid score 
                else {
                    System.out.println("That's not a valid category.");
                    doneScoringRolls = false;
                }
            }
            try {
                // adding up all the number scores for bonus score
                if ( ( this.scores.get("ones") + this.scores.get("twos") + this.scores.get("threes") + this.scores.get("fours") + this.scores.get("fives") + this.scores.get("sixes") ) >= 63) {
                    this.scores.put("bonus", 35);
                }
            } catch ( Exception e ) {
                // does nothing (I just want the program to try to see if the player gets bonus points)
            }
        }
        printScores();
    }
}
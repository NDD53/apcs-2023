package unit11;

import java.util.Arrays;

class Die {
    private int dice;
    private int[] rolls;

    public Die(int dice) {
        this.dice = dice;
        rolls = new int[dice];
    }

    public void roll() {
        for (int i = 0; i < dice; i++) {
            rolls[i] = (int) (1 + Math.random() * 6);
        }
    }

    public int[] getRoll() {
        return rolls;
    }

    public int[] getSortedRoll() {
        int[] out = rolls.clone();
        Arrays.sort(out);
        return out;
    }

    public int getSum() {
        int sum = 0;
        for (int i : rolls) {
            sum += i;
        }
        return sum;
    }
}

class BeatThat {
    private Die playerOne;
    private Die playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public BeatThat() {
        playerOne = new Die(2);
        playerTwo = new Die(2);
    }

    public String play() {
        playerOneScore = 0;
        playerTwoScore = 0;
        while (playerOneScore != 5 && playerTwoScore != 5) {
            playerOne.roll();
            playerTwo.roll();
            int[] one = playerOne.getSortedRoll();
            int[] two = playerTwo.getSortedRoll();
            if (one[0] * 10 + one[1] > two[0] * 10 + two[1]) {
                playerOneScore++;
            }
            if (one[0] * 10 + one[1] < two[0] * 10 + two[1]) {
                playerTwoScore++;
            }
        }
        if (playerOneScore == 5) {
            return "Player 1 wins";
        }
        return "Player 2 wins";
    }
}

class Mexico {
    private Die playerOne;
    private Die playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public Mexico() {
        playerOne = new Die(2);
        playerTwo = new Die(2);
    }

    public String play() {
        playerOneScore = 6;
        playerTwoScore = 6;
        while (playerOneScore != 0 && playerTwoScore != 0) {
            playerOne.roll();
            playerTwo.roll();
            int one = playerOne.getSum();
            int two = playerTwo.getSum();
            if (one < two) {
                playerOneScore--;
            }
            if (one > two) {
                playerTwoScore--;
            }
        }
        if (playerTwoScore == 0) {
            return "Player 1 wins";
        }
        return "Player 2 wins";
    }
}

class NumberRace {
    private Die playerOne;
    private Die playerTwo;
    private int playerOneScore;
    private int playerTwoScore;
    private int target;

    public NumberRace() {
        playerOne = new Die(1);
        playerTwo = new Die(1);
    }

    public String play() {
        playerOne.roll();
        target = playerOne.getRoll()[0];
        playerOneScore = 0;
        playerTwoScore = 0;
        while ((playerOneScore < 5 && playerTwoScore < 5) || playerOneScore == playerTwoScore) {
            playerOne.roll();
            playerTwo.roll();
            int one = playerOne.getRoll()[0];
            int two = playerTwo.getRoll()[0];
            if (one == target) {
                playerOneScore++;
            }
            if (two == target) {
                playerTwoScore++;
            }
        }
        if (playerOneScore > playerTwoScore) {
            return "Player 1 wins";
        }
        return "Player 2 wins";
    }
}

class DoubleTrouble {
    private Die playerOne;
    private Die playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public DoubleTrouble() {
        playerOne = new Die(2);
        playerTwo = new Die(2);
    }

    public String play() {
        playerOneScore = 10;
        playerTwoScore = 10;
        while ((playerOneScore > 0 && playerTwoScore > 0) || playerOneScore == playerTwoScore) {
            playerOne.roll();
            playerTwo.roll();
            int[] one = playerOne.getRoll();
            int[] two = playerTwo.getRoll();
            if (playerOne.getSum() % 2 == 1) {
                playerOneScore++;
            }
            if (playerOne.getSum() == 7) {
                playerOneScore += 3;
            }
            if (one[0] == one[1]) {
                playerOneScore -= 5;
            }
            if (playerTwo.getSum() % 2 == 1) {
                playerTwoScore++;
            }
            if (playerTwo.getSum() == 7) {
                playerTwoScore += 3;
            }
            if (two[0] == two[1]) {
                playerTwoScore -= 5;
            }
        }
        if (playerOneScore < playerTwoScore) {
            return "Player 1 wins";
        }
        return "Player 2 wins";
    }
}

class HighRoll {
    private Die playerOne;
    private Die playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public HighRoll() {
        playerOne = new Die(3);
        playerTwo = new Die(3);
    }

    public String play() {
        playerOneScore = 0;
        playerTwoScore = 0;
        while ((playerOneScore < 50 && playerTwoScore < 50) || playerOneScore == playerTwoScore) {
            playerOne.roll();
            playerTwo.roll();
            int one = playerOne.getSum();
            int two = playerTwo.getSum();
            if (one >= two) {
                playerOneScore += one;
            }
            if (two >= one) {
                playerTwoScore += two;
            }
        }
        if (playerOneScore > playerTwoScore) {
            return "Player 1 wins";
        }
        return "Player 2 wins";
    }
}

public class Dice {
    public static void main(String[] args) {
        DoubleTrouble myGame = new DoubleTrouble();
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
        System.out.println(myGame.play());
    }
}
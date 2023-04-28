package unit11;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

class Player {
    private int dice;
    private int[] rolls;
    private String name;
    private int score;

    public Player(int dice, String name, int score) {
        this.dice = dice;
        rolls = new int[dice];
        this.name = name;
        this.score = score;
    }

    public void roll() {
        for (int i = 0; i < dice; i++) {
            rolls[i] = (int) (1 + Math.random() * 6);
        }
    }

    public int[] getRoll() {
        return rolls;
    }

    public int getSum() {
        int sum = 0;
        for (int i : rolls) {
            sum += i;
        }
        return sum;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int a) {
        score = a;
    }
}

class Mexico {
    private ArrayList<Player> players;

    public Mexico(ArrayList<String> playerNames,int dice) {
        players = new ArrayList<Player>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(dice, playerNames.get(i), 0));
        }
    }
    public static void check(int players) throws AssertionError {
        if (players==0)
            throw new AssertionError("There are no players.");
    }

    public String play() {
        ArrayList<Player> game = (ArrayList<Player>) players.clone();
        for (Player a : game) {
            a.setScore(6);
        }
        check(game.size());
        int count = 0;
        while (game.size() != 1) {
            count++;
            System.out.println("\n------------------------");
            System.out.println("Round " + count + ":\n");
            for (Player a : game) {
                a.roll();
                System.out.println("" + a.getName() + " rolls " + Arrays.toString(a.getRoll()));
            }
            int[] scores = new int[game.size()];
            for (int i = 0; i < scores.length; i++) {
                scores[i] = game.get(i).getSum();
            }
            int lowest = Integer.MAX_VALUE;
            for (int i = 0; i < game.size(); i++) {
                if (scores[i] < lowest) {
                    lowest = scores[i];
                }
            }
            System.out.println("The lowest score this round is " + lowest);
            boolean test = true;
            for (int i = 0; i < game.size(); i++) {
                if (!(scores[i] == lowest)) {
                    test = false;
                    break;
                }
            }
            if (test) {
                System.out.println("Round skipped");
            } else {
                for (int i = game.size() - 1; i >= 0; i--) {
                    if (scores[i] == lowest) {
                        game.get(i).setScore(game.get(i).getScore() - 1);
                        System.out.println("" + game.get(i).getName() + " loses a point");
                        if (game.get(i).getScore() == 0) {
                            System.out.println("" + game.get(i).getName() + " is eliminated!");
                            game.remove(i);
                        }
                    }
                }
            }
        }
        System.out.println("\n\n" + game.get(0).getName() + " wins!\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

public class ClassDice {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Mexico!");
        System.out.println("How many dice do you want to roll?");
        int dice = scanner.nextInt();
        System.out.println("How many games do you want to play?");
        int games = scanner.nextInt();
        System.out.println("Enter the player names. Enter \"-1\" to finish");
        String next = "";
        while (true) {
            next = scanner.nextLine();
            if (next.equals("-1")) {
                break;
            }
            names.add(next);
        }
        scanner.close();
        System.out.println("\nLet the games begin!");
        Mexico myGame = new Mexico(names,dice);
        for(int i = 0; i<games; i++){
            myGame.play();
        }
    }
}
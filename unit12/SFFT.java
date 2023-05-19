package unit12;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SFFT {
    // in the form axy + bx + cy + d
    private int level;
    private int a, b, c, d;
    private String problem;
    private ArrayList<String> solution;
    private String user;
    private Scanner scanner;

    public SFFT(int level, Scanner scanner) {
        this.level = level;
        this.scanner = scanner;
        if (level == 1) {
            generateL1();
        }
    }

    public void generateL1() {
        a = 1;
        b = (int) (Math.random() * 6) + 4;
        c = (int) (Math.random() * 6) + 4;
        d = b * c;
        problem = "factor \"xy + " + b + "x + " + c + "y + " + d + "\"";
        System.out.println(problem);
        user = scanner.nextLine();
        clean();
        Pattern pattern = Pattern.compile("((([(][x][+][" + c + "][)])|([(][" + c + "][+][x][)]))(([(][y][+][" + b
                + "][)])|([(][" + b + "][+][y][)])))|((([(][y][+][" + b + "][)])|([(][" + b + "][+][y][)]))(([(][x][+]["
                + c + "][)])|([(][" + c + "][+][x][)])))");
        Matcher matcher = pattern.matcher(user);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("Correct");
        } else {
            errors();
        }
    }

    public void clean() {
        user = user.replaceAll(" ", "");
        user = user.replaceAll("\\*", "");
    }

    public void errors() {
        Pattern pattern = Pattern.compile(
                "((([(][x][+][" + c + "][)][x|X])|([(][" + c + "][+][x][)][x|X]))(([(][y][+][" + b + "][)])|([(][" + b
                        + "][+][y][)])))|((([(][y][+][" + b + "][)][x|X])|([(][" + b + "][+][y][)][x|X]))(([(][x][+]["
                        + c + "][)])|([(][" + c + "][+][x][)])))");
        Matcher matcher = pattern.matcher(user);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("Do not put an x");
        }
    }
}

class SFFTRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SFFT sfft = new SFFT(1, scanner);
        scanner.close();
    }
}

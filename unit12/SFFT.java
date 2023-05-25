package unit12;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SFFT {
    // in the form axy + bx + cy + d
    private int a, b, c, d;
    private String problem;
    private String user;
    private Scanner scanner;

    public SFFT(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean question(int level) {
        boolean out = false;
        if (level == 1) {
            out = generateL1();
            return out;
        }
        if (level == 2) {
            out = generateL2();
            return out;
        }
        return out;
    }

    public boolean generateL1() {
        a = 1;
        b = (int) (Math.random() * 6) + 4;
        c = b;
        while (c == b) {
            c = (int) (Math.random() * 6) + 4;
        }
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
            return true;
        } else {
            errors1();
            return false;
        }
    }

    public boolean generateL2() {
        a = (int) (Math.random() * 8) + 2;
        b = ((int) (Math.random() * 6) + 4) * a;
        c = b;
        while (c == b) {
            c = ((int) (Math.random() * 6) + 4) * a;
        }
        d = b * c * a;
        problem = "factor \"" + a + "xy + " + b + "x + " + c + "y + " + d + "\"";
        System.out.println(problem);
        user = scanner.nextLine();
        clean();
    }

    public void clean() {
        user = user.replaceAll(" ", "");
        user = user.replaceAll("\\*", "");
    }

    public void moveCo() {
        // I am out of time
        // You want to move the "x" "*" error into its own method, use an open/close
        // paren counter
        // In this method, call this first to find if they put an x outside, return this
        // error
        // Next, use a similiar open/close paren counter to find a coef, move it to the
        // front
        // You can then use essentially the same regex as before to test for this
    }

    public void errors1() {
        Pattern pattern = Pattern.compile(
                "((([(][x][+].[)][x|X])|([(].[+][x][)][x|X]))(([(][y][+].[)])|([(].[+][y][)])))|((([(][y][+].[)][x|X])|([(].[+][y][)][x|X]))(([(][x][+].[)])|([(].[+][x][)])))");
        Matcher matcher = pattern.matcher(user);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println(
                    "It appears you used the \"x\" symbol instead of \"*\" when multiplying. You need to stop doing this now. You will get marked down on tests and competitions for using an \"x\".");
            return;
        }
        pattern = Pattern.compile("((([(][x][+][" + b + "][)])|([(][" + b + "][+][x][)]))(([(][y][+][" + c
                + "][)])|([(][" + c + "][+][y][)])))|((([(][y][+][" + c + "][)])|([(][" + c + "][+][y][)]))(([(][x][+]["
                + b + "][)])|([(][" + b + "][+][x][)])))");
        matcher = pattern.matcher(user);
        matchFound = matcher.find();
        if (matchFound) {
            System.out.println(
                    "You reversed the order of the \"x\" and \"y\". If you put both the \"x\" and the \"" + b
                            + "\" in the same set of parentheses, they can not be multiplied together to produce the \""
                            + b
                            + "x\" term needed.");
            return;
        }
        System.out.println("You are incorrect, and I don't know what your error is. You probably mistyped somthing.");
    }
}

class SFFTRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SFFT sfft = new SFFT(scanner);
        sfft.question(1);
        scanner.close();
    }
}

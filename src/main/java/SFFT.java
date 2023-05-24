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

    public void question(int level) {
        if (level == 1) {
            generateL1();
        }
    }

    public void generateL1() {
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
                "((([(][x][+].[)][x|X])|([(].[+][x][)][x|X]))(([(][y][+].[)])|([(].[+][y][)])))|((([(][y][+].[)][x|X])|([(].[+][y][)][x|X]))(([(][x][+].[)])|([(].[+][x][)])))");
        Matcher matcher = pattern.matcher(user);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println(
                    "It appears you used the \"x\" symbol instead of \"*\" when multiplying. You need to stop doing this now. You will get marked down on tests and competitions for using an \"x\".");
        }
        // new code test it
        pattern = Pattern.compile("((([(][x][+][" + b + "][)])|([(][" + b + "][+][x][)]))(([(][y][+][" + c
                + "][)])|([(][" + c + "][+][y][)])))|((([(][y][+][" + c + "][)])|([(][" + c + "][+][y][)]))(([(][x][+]["
                + b + "][)])|([(][" + b + "][+][x][)])))");
        matcher = pattern.matcher(user);
        matchFound = matcher.find();
        if (matchFound) {
            System.out.println(
                    "You reversed the order of the \"x\" and \"y\". If you put both the \"x\" and the " + b
                            + " in the same set of parentheses, they can not be multiplied together to produce the " + b
                            + "x term needed");
        }
        // end new code
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

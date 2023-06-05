package unit12;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SFFT {
    // in the form axy + bx + cy + d
    private int a, b, c, d;
    private String problem;
    private String user;
    private Scanner scanner;
    private SFFTparser parser;

    public SFFT(Scanner scanner, SFFTparser parser) {
        this.scanner = scanner;
        this.parser = parser;
    }

    public boolean question(int level) {
        boolean out = false;
        if (level == 1) {
            out = generateL1();
        }
        if (level == 2) {
            out = generateL2();
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

    public boolean equalLists(List<String> one, List<String> two) {
        if (one == null && two == null) {
            return true;
        }

        if ((one == null && two != null)
                || one != null && two == null
                || one.size() != two.size()) {
            return false;
        }

        one = new ArrayList<String>(one);
        two = new ArrayList<String>(two);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

    public boolean generateL2() {
        a = (int) ((Math.round(Math.random())*2-1)*((Math.random() * 8) + 2));
        b = (int) (((Math.round(Math.random())*2-1)*(Math.random() * 6) + 4)) * a;
        c = b;
        while (c == b) {
            c = (int) ((Math.round(Math.random())*2-1)*(Math.random() * 6) + 4) * a;
        }
        d = b * c * a;
        System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);
        problem = "factor \"" + a + "xy " + (b < 0 ? "" : "+ ") + b + "x " + (c < 0 ? "" : "+ ") + c + "y "
                + (d < 0 ? "" : "+ ") + d + "\"";
        System.out.println(problem);
        user = scanner.nextLine();
        clean();
        List<String> stuff = parser.parseExpression(user);
        List<String> solution = new ArrayList<>();
        solution.add("" + a);
        solution.add("X" + c);
        solution.add("y" + b);
        return true;
    }

    public void clean() {
        user = user.replaceAll(" ", "");
        user = user.replaceAll("\\*", "");
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

class SFFTparser {
    public String subFactor(String expr) {
        int a = Integer.MIN_VALUE;
        int b = Integer.MIN_VALUE;
        char v = '!';
        String[] parts;
        boolean plus = (expr.indexOf('+') != -1);
        parts = plus ? expr.split("\\+") : expr.split("-");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            try {
                // maybe this is the constant part
                b = (i == 1 && !plus) ? Integer.parseInt(part) * -1 : Integer.parseInt(part);
            } catch (NumberFormatException e) {
                v = part.charAt(part.length() - 1);
                a = i == 1 && !plus ? -1 : 1;
                if (part.length() > 1) {
                    try {
                        String mult = part.substring(0, part.length() - 1);
                        a *= (mult.equals("-") ? -1 : Integer.parseInt(mult));
                    } catch (NumberFormatException ee) {
                        System.out.println("bad expr:" + part);
                    }
                }
            }
        }
        return "" + (a != Integer.MIN_VALUE ? "" + a + v + (b >= 0 ? "+" : "") : "") + b;
    }

    public List<String> parseExpression(String expression) {
        List<String> factors = new ArrayList<String>();
        int parenthesesCount = 0;
        StringBuilder factorBuilder = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                if (parenthesesCount == 0 && factorBuilder.length() != 0) {
                    factors.add(factorBuilder.toString().trim());
                    factorBuilder.setLength(0);
                } else {
                    factorBuilder.append(c);
                }
                parenthesesCount++;
            } else if (c == ')') {
                parenthesesCount--;
                if (parenthesesCount == 0) {
                    if (factorBuilder.toString().trim().charAt(0) == '(') {
                        factors.add(factorBuilder.toString().trim().substring(1));
                    } else {
                        factors.add(factorBuilder.toString().trim());
                    }
                    factorBuilder.setLength(0);
                } else {
                    factorBuilder.append(c);
                }
            } else {
                factorBuilder.append(c);
            }
        }
        for (; !noParen(factors);) {
            for (int i = factors.size() - 1; i > -1; i--) {
                String factor = factors.get(i);
                if (factor.indexOf('(') != -1) {
                    List<String> hold = parseExpression(factor);
                    for (String element : hold) {
                        factors.add(element);
                    }
                    factors.remove(i);
                }
            }
        }
        for (int i = factors.size() - 1; i > -1; i--) {
            factors.set(i, subFactor(factors.get(i)));
        }
        return factors;
    }

    public boolean noParen(List<String> check) {
        for (String element : check) {
            if (element.indexOf('(') != -1) {
                return false;
            }
        }
        return true;
    }
}

class SFFTRunner {
    public static void main(String[] args) {
        SFFTparser parser = new SFFTparser();
        Scanner scanner = new Scanner(System.in);
        SFFT sfft = new SFFT(scanner, parser);
        sfft.question(2);
        scanner.close();
    }
}

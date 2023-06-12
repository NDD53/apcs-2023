package unit13;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SFFT {
    // in the form axy + bx + cy + d = e???
    private int a, b, c, d, e;
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
        if (level == 3) {
            out = generateL3();
        }
        if (level == 4) {
            out = generateL4();
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
        System.out.println("Answer in the form \"(x+a)(y+b)\"");
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
            System.out.println("Incorrect");
            errors1();
            return false;
        }
    }

    public boolean generateL2() {
        a = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 8) + 2));
        b = (int) (((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 6) + 4))) * a;
        c = b;
        while (c == b) {
            c = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 6) + 4)) * a;
        }
        d = b * c / a;
        problem = "factor \"" + a + "xy " + (b < 0 ? "" : "+ ") + b + "x " + (c < 0 ? "" : "+ ") + c + "y "
                + (d < 0 ? "" : "+ ") + d + "\"";
        System.out.println(problem);
        System.out.println("Answer in the form \"a(x+b)(y+c)\"");
        user = scanner.nextLine();
        clean();
        List<String> stuff = parser.parseExpression(user, false);
        List<String> solution = new ArrayList<>();
        solution.add("" + a);
        solution.add("1x" + (c / a > 0 ? "+" : "") + c / a);
        solution.add("1y" + (b / a > 0 ? "+" : "") + b / a);
        boolean correct = equalLists(stuff, solution);
        if (!correct) {
            Pattern pattern = Pattern.compile(".+[x,y]");
            Matcher matcher;
            for (String str : stuff) {
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    System.out.println(
                            "Incorrect.\nMake sure that you have factored out coefficients if front of the \"x\" and \"y\" (including a \"-\").");
                    return false;
                }
            }
            System.out.println("Incorrect");
            return false;
        }
        System.out.println("Correct");
        return true;
    }

    public boolean generateL3() {
        a = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 8) + 2));
        e = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 8) + 2)) * a;
        b = (int) (((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 6) + 4))) * a;
        c = b;
        while (c == b) {
            c = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 6) + 4)) * a;
        }
        d = b * c / a;
        problem = "factor \"" + a + "xy " + (b < 0 ? "" : "+ ") + b + "x " + (c < 0 ? "" : "+ ") + c + "y "
                + (d < 0 ? "" : "+ ") + (d - e) + " = 0\"";
        System.out.println(problem);
        System.out.println("Answer in the form \"(x+a)(y+b)=c\"");
        user = scanner.nextLine();
        clean();
        List<String> stuff = parser.parseExpression(user, false);
        List<String> solution = new ArrayList<>();
        solution.add("=" + e / a);
        solution.add("1x" + (c / a > 0 ? "+" : "") + c / a);
        solution.add("1y" + (b / a > 0 ? "+" : "") + b / a);
        boolean correct = equalLists(stuff, solution);
        if (!correct) {
            System.out.println("Incorrect");
            return false;
        }
        System.out.println("Correct");
        return correct;
    }

    public boolean generateL4() {
        a = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 8) + 2));
        e = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 8) + 2)) * a;
        b = (int) (((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 6) + 4))) * a;
        c = b;
        while (c == b) {
            c = (int) ((Math.round(Math.random()) * 2 - 1) * ((Math.random() * 6) + 4)) * a;
        }
        d = b * c / a;
        problem = "Find all ordered pairs of integers (n,m) such that\n" + a + "nm " + (b < 0 ? "" : "+ ") + b + "n "
                + (c < 0 ? "" : "+ ") + c + "m "
                + (d < 0 ? "" : "+ ") + (d - e) + " = 0\nis satisfied.";
        System.out.println(problem);
        System.out.println("Answer in the form \"(n,m),(n2,m2),...\"");
        user = scanner.nextLine();
        clean();
        List<String> stuff = parser.parseExpression(user, true);
        Map<Integer, Integer> stuffy = new HashMap<>();
        for (int i = stuff.size() - 1; i >= 0; i--) {
            if (stuff.get(i).equals(",")) {
                stuff.remove(i);
            }
        }
        for (int i = stuff.size() - 1; i >= 0; i--) {
            String str = stuff.get(i);
            int o = str.indexOf(',');
            if (o == -1) {
                System.out.println("Incorrect");
                System.out.println("You don't know how to format, check yo commas.");
                return false;
            }
            try {
                stuffy.put(Integer.parseInt(str.substring(0, o)), Integer.parseInt(str.substring(o + 1)));
            } catch (Exception e) {
                System.out.println("Incorrect");
                System.out.println("I can't read this...\nOnly ints, commas, and parens please.");
                return false;
            }
        }
        int n = e / a;
        Map<Integer, Integer> solution = new HashMap<>();
        for (int i = -Math.abs(n); i <= Math.abs(n); i++) {
            if (i != 0) {
                if (n % i == 0) {
                    solution.put(i - (c / a), (n / i) - (b / a));
                }
            }
        }
        solution.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByKey());
        stuffy.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByKey());
        boolean correct = solution.equals(stuffy);
        if (!correct) {
            System.out.println("Incorrect");
            System.out.println("Remeber that an integer can be positive or negative.");
            return false;
        }
        System.out.println("Correct");
        return correct;
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

    public void clean() {
        user = user.replaceAll(" ", "");
        user = user.replaceAll("\\*", "");
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
                try {
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
                } catch (Exception eee) {
                }
            }
        }
        return "" + (a != Integer.MIN_VALUE ? "" + a + v + (b >= 0 ? "+" : "") : "") + b;
    }

    public List<String> parseExpression(String expression, boolean l4) {
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
                    List<String> hold = parseExpression(factor, l4);
                    for (String element : hold) {
                        factors.add(element);
                    }
                    factors.remove(i);
                }
            }
        }
        if (!l4) {
            for (int i = factors.size() - 1; i > -1; i--) {
                factors.set(i, subFactor(factors.get(i)));
            }
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
        System.out.println("What level of difficulty (int 1-4) do you want to start at?");
        int level = Integer.parseInt(scanner.nextLine());
        for (; level < 5; level++) {
            if (level == 1) {
                System.out.println(
                        "\nWelcome to level one!\nThis is a basic introduction to Simon's Favorite Factoring Trick (SFFT).\nSFFT is used when we need to factor expression with multiple variables, typically x,y or m,n.");
                System.out
                        .println("This level has pretty decent error detection, and I encourage you to just jump in.");
                System.out.println("If you want to read up on what we are actually doing, these are great resources:");
                System.out.println("https://www.youtube.com/watch?v=0nN3H7w2LnI");
                System.out.println("https://artofproblemsolving.com/wiki/index.php/Simon%27s_Favorite_Factoring_Trick");
            }
            if (level == 2) {
                System.out.println(
                        "\nWelcome to level two!");
                System.out.println("This time, there is a constant in front of the \"xy\" term.");
                System.out.println("Never fear though, it factors out nice.");
                System.out.println("In the real world, expect dome fractions.");
                System.out.println("In these problems, you ALWAYS factor out the constant in front of \"xy\" first.");
            }
            if (level == 3) {
                System.out.println("\nWelcome to level 3!");
                System.out.println("This is basically level two, but it doesn't always factor nice.");
                System.out.println(
                        "The trick is to factor the lhs as normal, then bring the remaining constant to the rhs.");
                System.out.println(
                        "If you remember completing the square, this is essentially the same process but with two variables.");
            }
            if (level == 4) {
                System.out.println("\nWelcome to level 4!");
                System.out.println("This is where stuff gets real");
                System.out.println(
                        "I have changed the variables to \"m\" and \"n\" because that is what you will see in math contests,");
                System.out
                        .println("The idea here is to simplify the equation into the answers to the level 3 problems.");
                System.out.println(
                        "Realize that we are only with integers, and adding or multiplying integers results in integers.");
                System.out.println("This means that we need two numbers that multiply to another number...");
                System.out.println("FACTORS!!!!!!");
                System.out.println("Write out all the factors for the constant on the lhs.");
                System.out.println("Each expression inside the parens will have to equal a factor.");
                System.out.println("This might take a lot of work, but making a table will help you.");
            }
            for (int score = 0; score < 5 - level;) {
                System.out.println("");
                boolean correct = sfft.question(level);
                System.out.println("");
                if (correct) {
                    score++;
                }
            }
        }
        System.out.println("You made it!");
        System.out.println("This is a type of problem very similar to what you will see in math competitions.");
        System.out.println("If you want more practice with more dynamic problems, visit:");
        System.out.println("https://artofproblemsolving.com/alcumus");
        System.out.println("and navigate to change focus --> algebra --> Simons Favorite Factoring Trick");
        System.out.println("If you are seeing this message, you would be great on math team.");
        System.out.println("What took 20+ hours of coding will only take me 10 min to fully explain to you.");
        System.out.println("These problems were more computationaly heavy than anything on math team ever will be.");
        System.out.println("We are not as much math as we are cool tricks.");
        System.out.println(
                "If you ever need help with math for school or fun, contact me. I will be very lonely in college.");
        scanner.close();
    }
}

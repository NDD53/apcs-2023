import java.util.List;
import java.util.ArrayList;

public class SFFTdummy {
    public static String subFactor(String expr) {
        int a = -1;
        int b = -1;
        char v = '!';
        String[] parts = expr.split("\\+");
        for (String part : parts) {
            try {
                // maybe this is the constant part
                b = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                v = part.charAt(part.length() - 1);
                a = 1;
                if (part.length() > 1)
                    try {
                        a = Integer.parseInt(part.substring(0, part.length() - 1));
                    } catch (NumberFormatException ee) {
                        System.out.println("bad expr:" + part);
                    }
            }
        }
        return "" + (a > 0 ? "" + a + v + "+" : "") + b;
    }

    public static List<String> parseExpression(String expression) {
        List<String> factors = new ArrayList<String>();
        List<String> ret = new ArrayList<String>();
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
        for (;factors.size() > 0;) {
            System.out.println("ur bad");
            for (int i = factors.size()-1; i > -1; i--) {
                System.out.println("ur dum");
                String factor = factors.get(i);
                if (factor.indexOf('(') != -1) {
                    System.out.println("ur mean");
                    List<String> hold = parseExpression(factor);
                    for (String element : hold) {
                        factors.add(element);
                    }
                } else {
                    System.out.println("ur an idiot");
                    ret.add(factor);
                    factors.remove(i);
                }
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        String a = "(1)(2(3(4+x)(2+x)))";
        List<String> test = parseExpression(a);
        for (String factor : test) {
            System.out.println(factor);
        }
    }
}

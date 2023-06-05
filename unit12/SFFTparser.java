import java.util.List;
import java.util.ArrayList;

public class SFFTparser {
    public static String subFactor(String expr) {
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
        return "" + (a != Integer.MIN_VALUE ? "" + a + v + (b >= 0 ? "+" : "") : "")+ b;
    }

    public static List<String> parseExpression(String expression) {
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

    public static boolean noParen(List<String> check) {
        for (String element : check) {
            if (element.indexOf('(') != -1) {
                return false;
            }
        }
        return true;
    }

    // public static void main(String[] args) {
    //     String a = "(1)(2(3(-4+x)(2+6x)))(2+x)(x+2)(((((4x-20)))(3+3x)))";
    //     List<String> test = parseExpression(a);
    //     for (String factor : test) {
    //         System.out.println(factor);
    //     }
    // }
}

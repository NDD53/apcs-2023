package unit11;

// 2018 FRQ #3
// https://secure-media.collegeboard.org/ap/pdf/ap18-frq-computer-science-a.pdf#page=12
interface StringChecker {
    /** Returns true if str is valid. */
    boolean isValid(String str);
}

public class CodeWordChecker implements StringChecker {
    private int a;
    private int b;
    private String c;
    public CodeWordChecker(int a, int b, String c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public CodeWordChecker(String c){
        a=6;
        b=20;
        this.c=c;
    }

    /** Returns true if str is valid. */
    public boolean isValid(String str) {
        if(str.length()>=a && str.length()<=b && str.indexOf(c)==-1){
            return true;
        }
        return false;
    }

    static boolean check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
        return test;
    }

    public static void main(String[] args) {
         StringChecker sc1 = new CodeWordChecker(5, 8, "$");
         check(sc1.isValid("happy"));
         check(!sc1.isValid("happy$"));
         check(!sc1.isValid("Code"));
         check(!sc1.isValid("happyCode"));
         check(!sc1.isValid("happy$Code"));
         check(!sc1.isValid("Code$happy"));
         StringChecker sc2 = new CodeWordChecker("pass");
         check(sc2.isValid("MyPass"));
         check(!sc2.isValid("Mypassport"));
         check(!sc2.isValid("happy"));
         check(!sc2.isValid("1,000,000,000,000,000"));
         System.out.println("Happy Panda! \uD83D\uDC3C");
    }

}

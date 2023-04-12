package unit11;

// 2021 FRQ #1
// https://apcentral.collegeboard.org/media/pdf/ap21-frq-computer-science-a.pdf#page=3
public class WordMatch {
    private String secret;

    public WordMatch(String word) {
        this.secret = word;
    }

    public int scoreGuess(String guess) {
        int s = 0;
        int l = guess.length();
        for(int i = 0; i<secret.length()-l+1; i++){
            if(secret.substring(i, i+l).equals(guess)){
                s += l*l;
            }
        }
        return s;
    }

    public String findBetterGuess(String guess1, String guess2) {
        String a = guess1;
        String b = guess2;
        int as = scoreGuess(a);
        int bs = scoreGuess(b);
        if(as>bs){
            return a;
        }
        if(bs>as){
            return b;
        }
        if(as==bs){
            int d = a.compareTo(b);
            if(d<0){
                return b;
            }
            else{ 
                return a;
            }
        }
        return "";
    }

    public static void parta1() {
        WordMatch game = new WordMatch("mississippi");
        check(game.scoreGuess("i") == 4);
        check(game.scoreGuess("iss") == 18);
        check(game.scoreGuess("issipp") == 36);
        check(game.scoreGuess("mississippi") == 121);
    }

    public static void parta2() {
        WordMatch game = new WordMatch("aaaabb");
        check(game.scoreGuess("a") == 4);
        check(game.scoreGuess("aa") == 12);
        check(game.scoreGuess("aaa") == 18);
        check(game.scoreGuess("aabb") == 16);
        check(game.scoreGuess("c") == 0);
    }

    public static void partb1() {
        WordMatch game = new WordMatch("concatenation");
        check(game.scoreGuess("ten") == 9);
        check(game.scoreGuess("nation") == 36);
        check(game.findBetterGuess("ten", "nation") == "nation");
    }

    public static void partb2() {
        WordMatch game = new WordMatch("concatenation");
        check(game.scoreGuess("cat") == 9);
        check(game.scoreGuess("con") == 9);
        check(game.findBetterGuess("cat", "con") == "con");
        check(game.findBetterGuess("con", "cat") == "con");
    }

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
        parta1();
        parta2();
        partb1();
        partb2();

        System.out.println("Happy Panda! \uD83D\uDC3C");
    }

}

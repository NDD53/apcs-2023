package unit11;

import java.util.ArrayList;
import java.util.Arrays;

// 2016 FRQ #1
// https://secure-media.collegeboard.org/digitalServices/pdf/ap/ap16_frq_computer_science_a.pdf#page=2

public class RandomStringChooser {
    private ArrayList<String> arr;

    public RandomStringChooser(String[] words){
            arr = new ArrayList<String>();
            for(int i = 0; i<words.length; i++){
                arr.add(words[i]);
            }
        }
        public String getNext(){
            int i = (int)(Math.random() * arr.size());
            if(arr.size()==0){
                return "NONE";
            }
            String temp = arr.get(i);
            arr.remove(i);
            return temp;
        }
    

    static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
         String[] wordArray = { "wheels", "on", "the", "bus" };
         RandomStringChooser sChooser = new RandomStringChooser(wordArray);
         for (int k = 0; k < 6; k++) {
         String next = sChooser.getNext();
         System.out.print(next + " ");
         if (k < 4)
         check(Arrays.stream(wordArray).anyMatch(next::equals));
         else
         check(next.equals("NONE"));

         }
         System.out.println();

         String word = "cat";
         RandomLetterChooser letterChooser = new RandomLetterChooser(word);
         for (int k = 0; k < 4; k++) {
         String next = letterChooser.getNext();
         System.out.print(next);
         if (k < 3)
         check(word.indexOf(next) != -1);
         else
         check(next.equals("NONE"));
         }
         System.out.println();
         System.out.println("Happy Panda! \uD83D\uDC3C");
    }
}

class RandomLetterChooser extends RandomStringChooser {

    /**
     * Constructs a random letter chooser using the given string str.
     * Precondition: str contains only letters.
     */
    public RandomLetterChooser(String str) {
        super(getSingleLetters(str));
    }

    /**
     * Returns an array of single-letter strings.
     * Each of these strings consists of a single letter from str. Element k
     * of the returned array contains the single letter at position k of str.
     * For example, getSingleLetters("cat") returns the
     * array { "c", "a", "t" }.
     */
    private static String[] getSingleLetters(String str) { /* implementation not shown */
        return str.split("");
    }
}
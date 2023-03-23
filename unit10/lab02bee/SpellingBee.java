import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.SourceDataLine;

public class SpellingBee {

    private char[] letters;
    private static char mustUse;
    private static String string;

    public SpellingBee(char[] letters, char mustUse) {
        this.letters = letters;
        this.mustUse = mustUse;
        string = "";
        for(int i = 0; i<letters.length; i++){
            string += letters[i];
        }
    }

    public static boolean checkWord(String word) {
        if(word.indexOf(mustUse) == -1 || word.length() < 4){
            return false;
        }
        for(int a = 0; a < word.length(); a++){
            if(string.indexOf(word.charAt(a))==-1){
                return false;
            }
        }
        return true;
    }


    /**
     * Loads the contents of file "filename" as a String.
     * 
     * @param filename the file to load
     * @return the contents of file "filename"
     */
    public static String loadFile(String filename) {
        String contents = "";
        Path path = Paths.get(filename);
        try {
            path = Path.of(ClassLoader.getSystemResource(filename).toURI());
            contents = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public static void main(String[] args) {
        String[] words = loadFile("words_dropped.txt").split("\n");
        System.out.println("Loaded " + words.length + " words");
        SpellingBee bee = new SpellingBee("ranglty".toCharArray(), 'y');
        int n = 0;
        for(String test : words){
            if(checkWord(test)){
                n++;
            }
        }
        String[] checked = new String[n];
        for(int a = 0; a<words.length; a++){
            if(checkWord(words[a])){
                checked[n-1] = words[a];
                n--;
            }
        }
        Arrays.sort(checked);
        System.out.println(Arrays.toString(checked));
        int i = 0;
        for(String a : words){
            i++;
            if(a.equals("search")){
                System.out.println("Found \"seaarch\" at " + i);
            }
        }
    }
}


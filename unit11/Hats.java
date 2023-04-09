package unit11;

import java.util.Arrays;

/*
 * this was my coding of spring break :)
 * based on the prisoners hat riddle https://www.youtube.com/watch?v=N5vJSNXPEwA
 * this code walks through the logic for each person in the line
 * black is 1, white is 0
 * this uses recursion :O (look at the method "run") (the recursion allowed me to eliminate some private variables)
 * if you really want me to, I can add comments to make it readable
 * the ted ed riddle series is great, and many of the riddles include polarity/binary concepts that are useful in comp sci
 */

public class Hats{
    private String[] real;
    private String[] guess;
    private int[] realInt;

    public Hats(String[] real){
        this.real = real;
        realInt = new int[real.length];
        for(int i = 0; i<real.length; i++) {
            realInt[i] = StrToInt(real[i]);
        }
        guess = new String[real.length];
    }

    public int StrToInt(String s){
        if(s.equals("black")){
            return 1;
        }
        return 0;
    }

    public int sum(int[] arr) {
        int ret = 0;
        for(int i = 0; i<arr.length; i++){
            ret += arr[i];
        }
        return ret;
    }

    public void run(int person, int polarity){
        if(person==0){
            return;
        }
        else if(person==real.length){
            System.out.println("\nPerson " + person + " sees " + Arrays.toString(Arrays.copyOfRange(real, real.length-person+1, real.length)));
            System.out.println("Converts to binary: " + Arrays.toString(Arrays.copyOfRange(realInt, realInt.length-person+1, realInt.length)));       
            int sum = sum(Arrays.copyOfRange(realInt, realInt.length-person+1, realInt.length));
            System.out.println("Sums the array: " + sum);
            sum = sum % 2;
            System.out.println("Converts to binary " + sum);
            if(sum==1){
                System.out.println("Guesses \"black\", setting the polarity to 1");
                guess[real.length-person] = "black";
                polarity = 1;
            }
            else{
                System.out.println("Guesses \"white\", setting the polarity to 0");
                guess[real.length-person] = "white";
                polarity = 0;
            }
            run(person-1,sum);
        }
        else{
            System.out.println("\nPerson " + person + " sees " + Arrays.toString(Arrays.copyOfRange(real, real.length-person+1, real.length)));
            System.out.println("Converts to binary: " + Arrays.toString(Arrays.copyOfRange(realInt, realInt.length-person+1, realInt.length)));       
            int sum = sum(Arrays.copyOfRange(realInt, realInt.length-person+1, realInt.length));
            System.out.println("Sums the array: " + sum);
            sum = sum % 2;
            System.out.println("Converts to binary " + sum);
            System.out.println("Person " + person + " sees a polarity of " + sum + " and expects a polarity of " + polarity);
            if(sum != polarity){
                polarity = (1+polarity)%2;
                System.out.println("Guesses \"black\", changing the polarity to " + polarity);
                guess[real.length-person] = "black";
            }
            else{
                System.out.println("Guesses \"white\", keeping the polarity as " + polarity);
                guess[real.length-person] = "white";
            }
            run(person-1,polarity);
        }
    }

    public void end(){
        System.out.println("\n");
        System.out.println("Real arrangment:    " + Arrays.toString(real));
        System.out.println("Guessed arrangment: " + Arrays.toString(guess));
        check();
    }

    public void check(){
        int off = 0;
        for(int i = 0; i<real.length; i++){
            if(!real[i].equals(guess[i])){
                off++;
            }
        }
        if(off!=1){
            System.out.println("We had " + off + " wrong guesses");
        }
        else{
            System.out.println("We had " + off + " wrong guess");
        }
        if(off<=1){
            System.out.println("Humanity is saved!");
        }
        else{
            System.out.println("Humanity is eaten by aliens (and I am going to fail my AP test)");
        }
    }


    public static void main(String[] args) {
        String[] a = {"black","black","white","white","black","white","black","black","black","black"};
        Hats h = new Hats(a);
        h.run(a.length,0);
        h.end();
    }
}
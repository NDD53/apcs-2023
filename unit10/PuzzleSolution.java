package unit10;
import java.util.Arrays;

// takes an int[] arr as the orignal array to figure out the +/- values of and an int that is the target value
// returns an int[] fin of +1 or -1 values corresponding to what the +/- value in the original array should be
public class PuzzleSolution {
    public static int[] puzzler(int[] arr,int n) {
        int[] fin = new int[arr.length];
        // loops through all possible combonations of +/- for each spot in the array
        for(int k = 0; k < Math.pow(2,arr.length);k++){
            int j = 0;
            // converts the index in the original loop k to a binary string
            String bin = Integer.toBinaryString(k);
            // sets each value in fin to 1 if the binary value at that index is 1, and -1 if the binary value is 0
            for(int x = bin.length()-1; x >= 0; x--){
                if(bin.charAt(x)==('1')){
                    fin[x]=1;
                }
                else{
                    fin[x]= -1;
                }
            }
            // sets the remaining values in the array to -1 (0 in binary)
            for(int r = fin.length -1 ; r>=bin.length();r--){
                fin[r]=-1;
            }
            // sets j to the sum of the product arr and fin
            for(int w = 0; w < fin.length; w++){
                j += fin[w]*arr[w];
            }
            // returns the array if j = the target 
            if(j==n){
                return fin;
            }

        }
        // dummy return that will never be used
        return fin;
    }
    public static void main(String[] args) {
        System.out.println("");
        int[] arr = {4, 10, 6, 10, 4, 2};
        System.out.println(Arrays.toString(puzzler(arr,0)));
    }
}

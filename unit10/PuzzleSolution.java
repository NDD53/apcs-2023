package unit10;
import java.util.Arrays;

// takes an int[] arr as the orignal array to figure out the +/- values of and an int that is the target value
// returns an int[] fin of +1 or -1 values corresponding to what the +/- value in the origional array should be
public class PuzzleSolution {
    public static int[] puzzler(int[] arr,int n) {
        int[] fin = new int[arr.length];
        // sets fin to all 1s
        for(int i = 0; i<fin.length; i++){
            fin[i] = 1;
        }
        // loops through all possible combanations of +/- for each spot in the array
        for(int k = 0; k < Math.pow(2,arr.length);k++){
            int j = 0;
            // sets j to the sum of the product arr and fin
            for(int w = 0; w < fin.length; w++){
                j += fin[w]*arr[w];
            }
            // returns the array if j = the target 
            if(j==n){
                return fin;
            }
            // converts the index in the origional loop k to a binary string
            String bin = Integer.toBinaryString(k);
            // sets each value in fin to 1 if the binary value at that index is 1, and -1 if the binary value is 0
            for(int x = 0; x < bin.length(); x++){
                if(bin.charAt(x)==('1')){
                    fin[x]=1;
                }
                else{
                    fin[x]= -1;
                }
            }

        }

        return fin;
    }
    public static void main(String[] args) {
        System.out.println("");
        int[] arr = {11, 6, 11, 10, 11, 1, 6, 7, 2, 6, 4, 6, 3 };
        System.out.println(Arrays.toString(puzzler(arr,30)));
    }
}

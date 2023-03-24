package unit10;
import java.util.Arrays;

public class PuzzleSolutions {
    /**
     * Calculates the sequence of "+"s or "-"s that is required for the ints in an array to equal a target.
     * @param arr original array of ints that needs to be added or subtracted to reach the target
     * @param n target number we want arr to add to
     * @return 2d array containg all possible solutions to the puzzle as 1 or -1
     * 
     * Very unoptimized:
     * 
     * Change the string bin to a binary number. This will remove the need to calculate the binary representation for every index
     * 
     * We calculate the arrays in the output from the binary string twice. I don't know how to add an array into a 2D array without
     * knowing how many arrays I need to add. 2D arraylist? 
     * 
     * For larger input arrays, it might be useful to calculate everything mod 3, then add. This would reduce the number of times you
     * have to run the full calulation.
     * 
     * For large differences in the numbers in the input array, you might be able to simplify the problem by knowing what sign certain 
     * numbers must be (ex: {1,1,1,2,2,2,3,3,3,1000} with target = 1006, no matter what, the 1000 must be positive)
     */
    public static int[][] puzzler(int[] arr,int n) {
        int count = 0;
        String finbin = "";
        int[] fin = new int[arr.length];
        // loops through each possible combination of -1s and 1s
        for(int k = 0; k < Math.pow(2,arr.length);k++){
            int j = 0;
            // bin is a string that is the binary expansion of the index k in the loop
            // this will cycle through each combination of 1s and 0s (later 1s and -1s) 
            String bin = "";
            // adds the appropriate number of 0s to the front of the binary number so it is the same length as the array
            // there are no 0s in front of binary numbers, so I have to add them
            for(int r = fin.length -1 ; r>=Integer.toBinaryString(k).length();r--){
                bin += "0";
            }
            bin += Integer.toBinaryString(k);
            // assigns -1 or 1 to each spot in the array fin based on the corresponding
            // 1 or 0 in the binary expansion of the index in the main for loop
            for(int x = bin.length()-1; x >= 0; x--){
                if(bin.charAt(x)==('1')){
                    fin[x]=1;
                }
                else{
                    fin[x]= -1;
                }
            }
            // multiplies each value in the original passed array arr by the 1 or -1 in the array fin
            // adds each product together to detemine the rusulting sum
            for(int w = 0; w < fin.length; w++){
                j += fin[w]*arr[w];
            }
            // adds the binary number to finbin if the sum above equals the target sum
            // after each binary number has been tested, finbin contains every solution to the problem in binary form in one string
            if(j==n){
                finbin += bin;
                count++;
            }
        }
        // ret is the array returned at the end of the method
        int[][] ret = new int[count][arr.length];
        // loops through each row and column in ret
        // assigns the value of -1 if the first char in finbin is a 0, 1 if it is a one, then cuts off the starting char 
        for(int row = 0; row < count; row++){
            for(int col = 0; col < arr.length; col++){
                if(finbin.substring(0, 1).equals("1")){
                    ret[row][col] = 1;
                }
                else{
                    ret[row][col] = -1;
                }
                finbin = finbin.substring(1);
            }
        }
        return ret;   
    }

    public static void main(String[] args) {
        System.out.println("");
        int target = 11;
        int[] arr = {1,2,3,4,5,6};
        int[][] pri = puzzler(arr,target);
        System.out.println("Target number: " + target);
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(arr));
        System.out.println("List of all solutions:");
        for(int[] row : pri){
            System.out.println(Arrays.toString(row));
        }
    }
}

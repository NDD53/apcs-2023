package unit10;
import java.util.Arrays;

public class PuzzleSolutions {
    public static int[][] puzzler(int[] arr,int n) {
        int count = 0;
        String finbin = "";
        int[] fin = new int[arr.length];
        for(int k = 0; k < Math.pow(2,arr.length);k++){
            int j = 0;
            String bin = "";
            for(int r = fin.length -1 ; r>=Integer.toBinaryString(k).length();r--){
                bin += "0";
            }
            bin += Integer.toBinaryString(k);
            for(int x = bin.length()-1; x >= 0; x--){
                if(bin.charAt(x)==('1')){
                    fin[x]=1;
                }
                else{
                    fin[x]= -1;
                }
            }
            for(int w = 0; w < fin.length; w++){
                j += fin[w]*arr[w];
            }
            if(j==n){
                finbin += bin;
                count++;
            }
        }
        int[][] ret = new int[count][arr.length];
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
        int target = 20;
        int[] arr = {4, 10, 1, 6, 10, 9, 4, 2};
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

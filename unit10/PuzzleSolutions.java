package unit10;
import java.util.Arrays;

public class PuzzleSolutions {
    public static int[][] puzzler(int[] arr,int n) {
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
            }

        }
        int[][] ret = new int[finbin.length()/arr.length][arr.length];
        for(int row = 0; row < finbin.length()/arr.length; row++){
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
        int[] arr = {4, 10, 6, 10, 4, 2};
        System.out.println(Arrays.deepToString(puzzler(arr,0)));
    }
}

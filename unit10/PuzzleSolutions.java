package unit10;
import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleSolutions {    
    public static ArrayList<int[]> puzzler(int[] arr,int n) {
        int[] fin = new int[arr.length];
        ArrayList<int[]> ret = new ArrayList<int[]>();
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
                ret.add(fin);
            }
        }
        return ret;   
    }
    public static void main(String[] args) {
        System.out.println("");
        int target = 11;
        int[] arr = {1,2,3,4,5,6};
        ArrayList<int[]> pri = puzzler(arr,target);
        System.out.println("Target number: " + target);
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(arr));
        System.out.println("List of all solutions:");
        for(int[] row : pri){
            System.out.println(Arrays.toString(row));
        }
    }
}


public class Run {

    public static void main(String[] Args){ 
       run(12345);
    }
    
    public static void run(int a){
       if(a%10!=0){
          run(a/10);
       }
       System.out.print(a%10);
    }   
}

public class PiDay {
    public static double piDay(int n) {
      if(n==9411){
        return 0;
      }
      else{
        return (((n%4)-2)*(4.0/((n-1)*(n)*(n+1))) + piDay(n+2));
      }
    }

    public static void main(String args[]) {
        double pi = 3.0 + piDay(3);
        System.out.println("pi = " + pi);
    }
}
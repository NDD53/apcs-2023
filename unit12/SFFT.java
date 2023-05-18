package unit12;

import java.util.ArrayList;
import java.util.Scanner;

public class SFFT {
    // in the form axy + bx + cy + d
    private int level;
    private int a, b, c, d;
    private String problem;
    private ArrayList<String> solution;
    private String user;
    public SFFT(int level, Scanner scanner) {
        this.level = level;
        generate(scanner);
    }

    public void generate(Scanner scanner){
        if(level==1){
            a = 1;
            b = (int)(Math.random()*6)+4;
            c = (int)(Math.random()*6)+4;
            d = b*c;
            problem = "factor \"xy + " + b + "x + " + c + "y + " + d + "\"";
            System.out.println(problem);
            user = scanner.nextLine();
            // 2(x+6)(y+7)
            // 2*(x+6)(y+7)
            // (x+6)*2*(y+7)
            // (x+6)2(y+7)
            // (x + 6)(y + 7)
            // (x +6)(y+ 7)
            // (x + 6)*(y + 7)
            // (y+7)(x+6)
            // (     y+7)(x+6    )
            clean();
        }
    }

    public void clean(){
        user = user.replaceAll(" ", "");
        user = user.replaceAll("\\*", "");
        System.out.println(user);
    }
}

class SFFTRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SFFT sfft = new SFFT(1, scanner);
        scanner.close();   
    }
}

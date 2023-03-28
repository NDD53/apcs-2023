package unit10;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Maze {
    private boolean solution;
    private char[][] maze;
    private String ans;

    public Maze(int rows, int cols, String line) {
        maze = new char[rows][cols];
        solution = false;
        for( int r = 0 ; r < rows ; r++){
            for( int c = 0 ; c < cols ; c++){
                maze[r][c] = line.charAt(0);
                line = line.substring(1);
            }
        }
        ans = "";
    }

    public String getStart() { /* Not shown, plz ignore implementation */
        int z = Arrays.stream(maze).map(String::new).collect(Collectors.joining("")).indexOf('@');
        return "" + z / maze[0].length + " " + z % maze[0].length;
    }

    public String getEnd() { /* Not shown, plz ignore implementation */
        int z = Arrays.stream(maze).map(String::new).collect(Collectors.joining("")).indexOf('$');
        return "" + z / maze[0].length + " " + z % maze[0].length;
    }

    private void check(int r, int c, String w) {
        if( 0<=c && c<maze[0].length && 0<=r && r<maze.length && maze[r][c]!=('#') && maze[r][c]!=('?')){
            if(maze[r][c] == '$'){
                solution = true;
                ans = w;
                return;
            }
            maze[r][c] = '?';
            check(r+1,c,w+"⌄");
            check(r-1,c,w+"^");
            check(r,c+1,w+">");
            check(r,c-1,w+"<");
        }
    }

    public boolean hasSolution() {
        String a = getStart();
        int s = a.indexOf(" ");
        int r = Integer.parseInt(a.substring(0, s));
        int c = Integer.parseInt(a.substring(s+1));
        check(r,c,"");
        return solution;
    }

    public String toString(){
        if(solution){
            return "a solution is " + ans;
        }
        return "there is no solution";
    }

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
        Maze example = new Maze(3, 3, "#.@.....$");
        check(example.hasSolution());
        System.out.println(example);

        Maze case1 = new Maze(5, 7, ".#.#....#.#.##@.....$#...#.##..#...");
        check(case1.hasSolution());
        System.out.println(case1);

        Maze case2 = new Maze(4, 4, ".#.$.##..##.@..#");
        check(!case2.hasSolution());
        System.out.println(case2);

        Maze test = new Maze(3, 3, "#.@.....$");
        check(test.hasSolution());
        System.out.println(test);

        test = new Maze(3, 3, "##@#####$");
        check(!test.hasSolution());
        System.out.println(test);

        test = new Maze(3, 3, "##@#..#.$");
        check(test.hasSolution());
        System.out.println(test);

        test = new Maze(3, 3, "#.@#.##.$");
        check(test.hasSolution());
        System.out.println(test);

        test = new Maze(3, 3, "##@#.##.$");
        check(!test.hasSolution());
        System.out.println(test);

        System.out.println("Happy Panda! \uD83D\uDC3C");

    }

}

package Lab05;

import java.util.*;

/**
 * @author ORI WEISS
 * @version 02/20/2018
 * https://www.cs.bu.edu/teaching/alg/maze/
 */
public class MazeSolver
{
    private char[][] maze;

    public MazeSolver(char[][] grid)
    {
        setMaze(grid);
    }

    public void setMaze(char[][] grid)
    {
        this.maze = new char[grid.length][];
        for (int r = 0; r < grid.length; r++)
        {
            this.maze[r] = new char[grid[r].length];
            for (int c = 0; c < grid[r].length; c++)
                this.maze[r][c] = grid[r][c];
        }
    }

    private boolean findPath(int r, int c)
    {
        if(!isInsideMaze(r,c)){
        return false;
    }
        if(isGoal(r,c)){
            return true;
        }
        if(!isOpen(r,c)){
            return false;
        }
        this.maze[r][c] = 'X';
        if(findPath(r-1,c) == true){
            return true;
        }
        if(findPath(r,c-1) == true){
            return true;
        }
        if(findPath(r+1,c) == true) {
            return true;
        }
        if(findPath(r,c+1) == true){
                return true;
        }
        else{
            return false;
        }

        // TODO-DONE Project #4
//        return false; // THIS IS A STUB
    }

    private boolean isInsideMaze(int r, int c)
    {
        return (r >= 0 && c >= 0 && r < this.maze.length && c < this.maze[0].length);
        // TODO-DONE Project #4
//        return false; // THIS IS A STUB
    }

    private boolean isGoal(int r, int c)
    {
        return (this.maze[r][c] == 'G');
    }

    private boolean isOpen(int r, int c)
    {
        return (this.maze[r][c] == '.'||this.maze[r][c] =='S' || this.maze[r][c] =='G') ;
        // TODO-DONE Project #4
         // ., S, or G would be considered open
//        return false; // THIS IS A STUB
    }

    private boolean setGoal(int r, int c)
    {
        boolean goalOK = false;
        if (isInsideMaze(r, c) && isOpen(r, c))
        {
            this.maze[r][c] = 'G';
            goalOK = true;
        }
        return goalOK;
    }

    private boolean setStart(int r, int c)
    {
        boolean startOK = false;
        if (isInsideMaze(r, c) && isOpen(r, c))
        {
            this.maze[r][c] = 'S';
            startOK = true;
        }
        return startOK;
    }

    private void resetStart(int r, int c)
    {
        this.maze[r][c] = 'S';
    }

    public void displayMaze()
    {
        System.out.printf("      ");
        for (int c = 0; c < this.maze[0].length; c++)
        {
            System.out.printf("[%1$2s] ", c);
        }
        System.out.println();
        for (int r = 0; r < this.maze.length; r++)
        {
            System.out.printf("[%1$2s]", r);
            for (int c = 0; c < this.maze[0].length; c++)
            {
                System.out.printf("%1$5s", this.maze[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        char[][] grid = {{'.', '#', '#', '#', '#', '#'},
                         {'.', '.', '.', '.', '.', '#'},
                         {'#', '.', '#', '#', '#', '#'},
                         {'#', '.', '#', '.', '#', '#'},
                         {'.', '.', '.', '#', '.', '.'},
                         {'#', '#', '.', '.', '.', '#'}};

        MazeSolver searchGrid = new MazeSolver(grid);
        System.out.print("\n        *** SEARCH THE MAZE ***\n");
        searchGrid.displayMaze();
        Scanner keyboard = new Scanner(System.in);

        int rGoal;
        int cGoal;
        int rStart;
        int cStart;
        boolean inputOK;

        do
        {
            inputOK = true;
            System.out.println("Enter the START row");
            rStart = keyboard.nextInt();
            System.out.println("Enter the START column");
            cStart = keyboard.nextInt();
            if (!searchGrid.setStart(rStart, cStart))
            {
                System.out.println("Incorrect START coordinates, please try again.");
                inputOK = false;
            }
        } while (!inputOK);

        do
        {
            inputOK = true;
            System.out.println("Enter the GOAL row");
            rGoal = keyboard.nextInt();
            System.out.println("Enter the GOAL column");
            cGoal = keyboard.nextInt();
            if (rGoal == rStart && cGoal == cStart)
            {
                System.out.println("GOAL is the same as START, try different coordinates.");
                inputOK = false;
            }
            else if (!searchGrid.setGoal(rGoal, cGoal))
            {
                System.out.println("Incorrect GOAL coordinates, please try again.");
                inputOK = false;
            }
        } while (!inputOK);

        searchGrid.displayMaze();
        if (searchGrid.findPath(rStart, cStart))
            System.out.println("\n---> The GOAL [" + rGoal + "," + cGoal + "] was found!");
        else
            System.out.println("\n---> The GOAL [" + rGoal + "," + cGoal + "]  was not reached!");
        searchGrid.resetStart(rStart, cStart);
        System.out.println("\nThe search results:");
        searchGrid.displayMaze();
    }
}

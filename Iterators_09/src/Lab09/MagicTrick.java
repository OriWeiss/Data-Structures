package Lab09;

import java.util.*;

/**
 * MagicTrick is a program that will guess a number that user is thinking of.
 *
 * @author atb
 * @version 3/27/2018
 */
public class MagicTrick
{
    public final int NUM_OF_SEQUENCES = 5;
    public final int NUMBERS_PER_SEQUENCE = 16;
    private ArrayList<Integer>[] sequences;

    public MagicTrick()
    {
        // TODO Project 5
        // create array of arraylist objects
        //
        //ArrayList<Integer>sequences = new ArrayList<Integer>(NUM_OF_SEQUENCES);
        this.sequences = new ArrayList[NUM_OF_SEQUENCES];
        for(int i=0; i < NUM_OF_SEQUENCES;i++){
            int power = (int) Math.pow(2,i);
            this.sequences[i] = new ArrayList<Integer>();
            this.sequences[i].add(power);
            for (int j = 1; j < this.NUMBERS_PER_SEQUENCE; j++) {
                int prev = this.sequences[i].get(j-1);
                if(j % this.sequences[i].get(0) == 0){
                    this.sequences[i].add(prev + power +1);
                }
                else{
                    this.sequences[i].add(prev + 1);
                }
            }
        }
        // create this.sequences object
        // using a for loop create ArrayList object for each slot and fill it with appropriate values
    }

    public void displaySequences()
    {

        for (int i = 0; i < this.NUM_OF_SEQUENCES; i++) {
            System.out.println("Sequence " + (i+1)+":" + sequences[i]);
        }
        // TODO Project 5
    }

    public void guessNumber(String[] answer)
    {
        int total = 0;
        for(String element: answer){
            int index = Integer.parseInt(element) - 1;
            total += this.sequences[index].get(0);
        }
        System.out.println("Your Number is " + total + " :)");

        // TODO Project 5
    }

    public static void main(String[] args)
    {
        MagicTrick magic = new MagicTrick();
        System.out.println("Think of a number between 1 and 31\n");
        magic.displaySequences();

        System.out.println("\nList all the sequences that your number is in (ie. 1 3)");
        Scanner scan = new Scanner(System.in);
        magic.guessNumber(scan.nextLine().split(" "));
    }
}
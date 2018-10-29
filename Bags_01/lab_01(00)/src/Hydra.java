//Author: Ori Weiss
//1/30/2018

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

/**
 * This class determines how long it would take to remove billiard balls from a table,
 * where ball n is replaced by n balls with randomly generated numbers between 1 and n-1.
 *
 * @version 1/23/2018
 * @updatedBy YOUR NAME GOES HERE
 */
public class Hydra
{
    private BagInterface<String> hydraHeads;

    /**
     * constructor creates this.hydraHeads object as ResizableArrayBag
     */
    public Hydra()
    {
        this.hydraHeads = new ResizableArrayBag<>();
    }

    /**
     * prompts the user for the first numbered ball and adds it to this.hydraHeads
     */
    public void addFirstElement()
    {
        Scanner keyboard = new Scanner(System.in);
        String start;
        System.out.println("What is the initial string? ");
        start = keyboard.next();
        System.out.println("The initial string: \" " + start + "\"");
        this.hydraHeads.add(start);
    }

    /**
     * Removes balls from this.table until all are gone.
     */
    public void removeHead()
    {
        System.out.println("\n*** Removing head from the Hydra ***\n");
//        Create Random object
        while(!this.hydraHeads.isEmpty()){
            String headRemove = this.hydraHeads.remove();
            System.out.println("Removed: "+ headRemove);
            if(headRemove.length() == 1){
                System.out.println("The removed head is of length 1, no new" +
                        " heads will be added - " +
                        this.hydraHeads.getCurrentSize() + " heads remaining");
            }
            else {
                this.hydraHeads.add(headRemove.substring(1));
                this.hydraHeads.add(headRemove.substring(1));
            }
            displayBag();
        }
//        Repeat for as long as there are balls on the table:
//        remove random ball and display its value
//        if the ball number is 1 just print a message as shown in the sample run
//        otherwise (the ball number is greater than 1)
//        put the “ball number” of randomly generated balls on the table
//        print appropriate message as shown in the sample run
//        display the content of the bag

        //TODO
        System.out.println("\nThe table is empty!!!");
    } // end removeBallsFromTable

    /**
     * Displays the content of this.table
     */
    private void displayBag()
    {
        Object[] bagArray = this.hydraHeads.toArray();
        System.out.println(Arrays.toString(bagArray));
        System.out.println();
    } // end displayBag

    public static void main(String args[])
    {
        Hydra billiard = new Hydra();
        billiard.addFirstElement();

        long startTime = Calendar.getInstance().getTime().getTime(); // get current time in miliseconds

        billiard.removeHead();

        long stopTime = Calendar.getInstance().getTime().getTime();

        System.out.println("\nThe time required was " + (stopTime - startTime) + " milliseconds");
    } // end main
} // end Billiard
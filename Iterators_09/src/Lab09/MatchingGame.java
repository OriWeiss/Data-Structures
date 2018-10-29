package Lab09;
import java.util.*;

/**
 *
 * @author atb
 * @version 3/27/2018
 */
public class MatchingGame
{
    private ArrayList<Integer> theNumbers;
    final int MAX_NUMBER_OF_SHUFFLES = 5;
    final int MIN_NUMBER = 10;
    final int MAX_NUMBER = 99;
    Random generator = new Random(11);

    public MatchingGame(int numberAmount)
    {
        this.theNumbers = new ArrayList<>();
        initializeList(numberAmount);
    }

    /**
     * Initialize the list with the count of random 2 digit numbers.
     *
     */
    private void initializeList(int numberAmount)
    {
        // TODO Project 6a
        ListIterator<Integer> iter = this.theNumbers.listIterator();
        for (int i = 0; i < numberAmount; i++) {
            iter.add(generator.nextInt(100 - 10)+ 10);
        }
        // generate the numbers and add them to theNumbers using iterator

    }

    /**
     * See whether two numbers are removable.
     * @param first the first 2 digit integer value
     * @param second the second 2 digit integer value
     * @return true if the first and second match
     */
    private boolean removablePair(Integer first, Integer second)
    {
        // TODO Project 6c
        boolean result = false;
        String sFirst = first.toString();
        String sSecond = second.toString();
        if((sFirst.charAt(0)== sSecond.charAt(0)) || (sFirst.charAt(0) == sSecond.charAt(1)) ||
                (sFirst.charAt(1)== sSecond.charAt(0)) || (sFirst.charAt(1) == sSecond.charAt(1))){
            result = true;
        }

        // implement this method
        return result;
        //return false; //stub
    }

    /**
     * Implements one pass when called by play method
     * Scans over the list and removes any pairs of values that are removable.
     * @return true if any pair of integers was removed
     */
    private boolean scanAndRemovePairs(){
        // TODO Project 6d
        boolean removedAPair = false;
        ListIterator<Integer> scan = this.theNumbers.listIterator();

        Integer first = null;
        Integer second = null;

        if(scan.hasNext()){
            first = scan.next();
        }

        while(scan.hasNext()){
            //System.out.println("first = " + first);
                second = scan.next();
            //System.out.println("second = " + second);
                if (removablePair(first, second)) {
                    scan.remove();
                    scan.previous();
                    scan.remove();
                    removedAPair = true;
                    System.out.println("    Removed : " + first + "  " + second);
                    if(scan.hasNext()) {
                        first = scan.next();
                    }

                }
                else {
                    first = second;
                }
        }
        // implement the method
        // this method calls helper method removablePair to see if there is a match
        return removedAPair;
    }

    private void displayTheNumbers()
    {
        // TODO Project 6b
        if(this.theNumbers.size() == 0){
            System.out.println("The list is empty");
        }
        else{
            ListIterator<Integer> iter = this.theNumbers.listIterator();
            while(iter.hasNext()){
                System.out.print(iter.next() + " ");
            }
            System.out.println();
        }
        // using an instance of Iterator display the content of theNumbers
        // notify the user if the list is empty
    }

    public void play()
    {
        int pass = 0;
        int numberOfShuffles = 0;
        boolean repeat;

        System.out.println("Starting with: ");
        displayTheNumbers();

        do
        {
            repeat = false;
            while (scanAndRemovePairs())
            {
                pass++;
                System.out.println("The list after pass #" + pass);
                displayTheNumbers();
            }
            System.out.println("No more pairs to remove.");
            // do we have at least 3 numbers in the list?
            if (this.theNumbers.size() > 2)
            {
                if (numberOfShuffles < MAX_NUMBER_OF_SHUFFLES)
                {
                    numberOfShuffles++;
                    System.out.println("Shuffling the numbers.");
                    Collections.shuffle(this.theNumbers, this.generator);
                    System.out.println("The list after shuffling #" + numberOfShuffles);
                    displayTheNumbers();
                    repeat = true;
                }
            }
        }while(repeat);

        if (this.theNumbers.isEmpty())
        {
            System.out.println("\n*** Winner!!! ***");
        }
        else
        {
            System.out.println("\n*** Better luck next time! ***");
        }
    }

    public static void main(String[] args)
    {
        final int MIN_NUMBER_OF_ELEMENTS = 10;
        Scanner scan = new Scanner(System.in);
        int numberAmount;

        do
        {
            System.out.println("How many numbers (no less than " + MIN_NUMBER_OF_ELEMENTS + ")?");
            numberAmount = scan.nextInt();
        }while(numberAmount < MIN_NUMBER_OF_ELEMENTS);

        MatchingGame game = new MatchingGame(numberAmount);
        game.play();
    }
}

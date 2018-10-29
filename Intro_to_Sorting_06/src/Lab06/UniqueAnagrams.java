package Lab06;

import java.util.*;

/**
 * This class creates all permutations of the given string
 *
 * @author YOUR NAME
 * @version 2/27/2018
 */
public class UniqueAnagrams
{
    private ArrayList<String> anagrams;

    public UniqueAnagrams()
    {
        this.anagrams = new ArrayList<>();
    }

    public void permutations(String word)
    {
        permutations("", word);
        System.out.println("Possible anagrams = " + this.anagrams);

        TreeSet<String> toTest = new TreeSet(this.anagrams);
        System.out.println("Expected unique and sorted anagrams = " + toTest);
        System.out.println();

        sort(); // duplicates will be removed during the sorting process
    }

    private void permutations(String prefix, String suffix)
    {
        int numOfChars = suffix.length();
        if (numOfChars == 1)
        {
            //System.out.println(prefix + suffix);
            this.anagrams.add(prefix + suffix);
        }
        else
        {
            for (int i = 0; i < numOfChars; i++)
                permutations(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i+1, numOfChars));
            //TODO-DONE Project2
        }
    }

    private void sort()
    {
        for (int index = 0; index < this.anagrams.size() -1; index++) {
            int indexOfNextSmallest = getIndexOfSmallestAndRemoveDuplicates(index, this.anagrams.size());
            swap(index, indexOfNextSmallest);
        }
        //TODO-DONE Project2

        // calls getIndexOfSmallestAndRemoveDuplicates(index, this.anagrams.size());
        // calls swap(index, indexOfNextSmallest);
    }

    private int getIndexOfSmallestAndRemoveDuplicates(int first, int last)
    {
        String min = this.anagrams.get(first);
        int indexOfMin = first;
        for (int index = first + 1; index < last; index++) {
            if (this.anagrams.get(index).compareTo(min) < 0) {
                min = this.anagrams.get(index);
                indexOfMin = index;
            }
            else if(this.anagrams.get(index).equals(min)){
                this.anagrams.remove(index);
                last--;
                index--;
            }
        }
        return indexOfMin;



        //TODO-DONE Project2

        // as the smallest is being found removes duplicates

//        return 0; // THIS IS A STUB
    }

    private void swap(int i, int j)
    {
        if (i != j) {
            String temp = this.anagrams.get(i);
            this.anagrams.set(i,this.anagrams.get(j));
            this.anagrams.set(j,temp);
        }

        //TODO-DONE Project2
    }

    private void display()
    {
        System.out.println("Computed unique and sorted anagrams = " + this.anagrams);
    }

    public static void main(String[] args)
    {
        UniqueAnagrams uniqueAnagrams = new UniqueAnagrams();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter a word");
        String word = keyboard.next();

        uniqueAnagrams.permutations(word);
        uniqueAnagrams.display();
    }
}

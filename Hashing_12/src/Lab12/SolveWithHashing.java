package Lab12;

import java.util.*;

/**
 * A class that implements solutions to several problems using hashing
 *
 * @author Ori Weiss
 * @version 4/17/2018
 */
public class SolveWithHashing
{
    private DictionaryInterface<Integer, Integer> hashedDictionary;
    private final int DEFAULT_CAPACITY = 5;

    public SolveWithHashing()
    {
        createHashedDictionary();
    }

    public void createHashedDictionary()
    {
        this.hashedDictionary = new HashedDictionary<>(DEFAULT_CAPACITY);
    }

    private void testDisplayHashTable()
    {
        System.out.println("displaying empty dictionary");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.add(1, 1);
        this.hashedDictionary.add(7, 7);
        System.out.println("displaying dictionary after 2 entries have been added");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.add(13, 13);
        this.hashedDictionary.add(17, 17);
        this.hashedDictionary.add(8, 8);
        System.out.println("displaying dictionary after 3 additional entries have been added");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.remove(1);
        this.hashedDictionary.remove(17);
        System.out.println("displaying dictionary after 2 entries have been removed");
        this.hashedDictionary.displayHashTable();
    }

    public Integer getFirstRepeatedElement(int[] a)
    {
  // TODO Project1 #1
        boolean stop = false;
        Integer result = null;
        for (int i = 0;!stop && i < a.length; i++) {
            if(this.hashedDictionary.getValue(a[i]) == null) {
                this.hashedDictionary.add(a[i] ,i + 1 );
            }
            else{
                int value = this.hashedDictionary.getValue(a[i]);
                this.hashedDictionary.add(a[i], Math.abs(value) * -1);
            }

        }
        this.hashedDictionary.displayHashTable();
        Iterator iter = this.hashedDictionary.getValueIterator();
        int highestValue = -10;
        while (iter.hasNext()) {
            int currentValue = (int) iter.next();
            if ((currentValue < 0)) {
                if (currentValue > highestValue) {
                    highestValue = currentValue;
                    return a[Math.abs(highestValue)];
                }
            }
            else {
                iter.next();
            }
        }
        return result;
    }

    public boolean lookForPair(int[] a, int[] b, int k)
    {
        int[] minArray;
        int[] otherArray;
        if(a.length>b.length){
            minArray =b;
            otherArray = a;
        }
        else{
            minArray = a;
            otherArray = b;
        }
        System.out.print("toPutInHashTable = [");
        for(int element: minArray){
            System.out.print(element+ ", ");
        }
        System.out.println("]");
        System.out.print("toCheck = [");
        for(int otherElement: otherArray){
            System.out.print(otherElement+ ", ");
        }
        System.out.println("]");
        for (int i = 0; i < minArray.length; i++) {
            if (this.hashedDictionary.getValue(minArray[i]) == null) {
                this.hashedDictionary.add(minArray[i], minArray[i]);
            }

            Iterator iter = this.hashedDictionary.getValueIterator();
            while (iter.hasNext()){
                int hashTableValue = (int) iter.next();
                for(int h = 0; h<otherArray.length;h++){
                    int arrayValue = otherArray[h];
                    if(hashTableValue+arrayValue == k){
                        System.out.println("The pair {"+ hashTableValue+","+
                                arrayValue+"} adds up to "+ k);
                        return true;
                    }
                }
            }
        }
        return false;

        // TODO Project1 #2
        //return false;
    }

    public static void main(String[] args)
    {
        ArrayList<int[]> toCheck = new ArrayList<>();
        toCheck.add(new int[]{9, 3, 5, 1, 2, 2, 5, 3});
        toCheck.add(new int[]{6, 6, 3, 2, 1, 2, 2, 3});
        toCheck.add(new int[]{2, 1, 6, 2, 3, 2, 3, 6});
        toCheck.add(new int[]{3, 2, 1, 2, 2, 3, 6, 6});
        toCheck.add(new int[]{9, 3, 4, 4, 4, 1, 2, 2, 5, 3});
        toCheck.add(new int[]{3, 3, 3, 3, 3, 3, 3});
        toCheck.add(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        toCheck.add(new int[]{8, 1, 2, 3, 4, 5, 6, 7});

        SolveWithHashing solver = new SolveWithHashing();

        System.out.println("\n\t*** Testing displayHashTable ***");
        solver.testDisplayHashTable();

        System.out.println("\n\t*** Find The First Element With Duplicate ***");
        Integer firstDuplicate;
        for (int[] array : toCheck)
        {
            solver.createHashedDictionary();
            firstDuplicate = solver.getFirstRepeatedElement(array);
            if (firstDuplicate != null)
                System.out.println("--> the first element that is repeated is: " + firstDuplicate);
            else
                System.out.println("--> duplicates not found");
            System.out.println();
        }

        System.out.println("\n\t*** Check If There Exists A Pair Of Elements That Add Up To k ***");
        boolean found;
        for (int k = 2; k < 10; k++)
        {
            System.out.println("k = " + k);
            for (int i = 1; i < toCheck.size(); i++)
            {
                solver.createHashedDictionary();
                found = solver.lookForPair(toCheck.get(i - 1), toCheck.get(i), k);
                System.out.println("--> pair that add up to " + k + (found ? "" : " NOT") + " found.");
            }
            System.out.println();
        }
        System.out.println("\nBye!");
    }  // end main
}

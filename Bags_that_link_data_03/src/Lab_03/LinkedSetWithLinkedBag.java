//Author: Ori Weiss
//Date: 2/13/2018

package Lab_03;

import java.util.Arrays;

/**
 * A class that implements the ADT set by using a linked bag.
 * The set is never full.
 *
 * @author YOUR NAME
 * @version 2/6/2018
 */
public class LinkedSetWithLinkedBag<T extends Comparable<? super T>> implements SetInterface<T>
{
    private LinkedBag<T> bagOfSetEntries;

    /**
     * Creates a set from a new, empty linked bag.
     */
    public LinkedSetWithLinkedBag()
    {
        bagOfSetEntries = new LinkedBag<>();
        //TODO Project1
    } // end default constructor

    public boolean add(T newEntry)
    {
        boolean result = false;
        if(!bagOfSetEntries.contains(newEntry)){
            bagOfSetEntries.add(newEntry);
            result = true;
        }


        //TODO Project1

        return result; //THIS IS A STUB
    } // end add

    public T[] toArray()
    {
        return bagOfSetEntries.toArray();
        //TODO Project1
//        return null; //THIS IS A STUB
    } // end toArray

    public boolean isEmpty()
    {
        return bagOfSetEntries.isEmpty();
        //TODO Project1
//        return false; //THIS IS A STUB
    } // end isEmpty

    public boolean contains(T anEntry)
    {
        return bagOfSetEntries.contains(anEntry);
        //TODO Project1
//        return false; //THIS IS A STUB
    } // end contains

    public void clear()
    {
        bagOfSetEntries.clear();
        //TODO Project1
    } // end clear

    public T remove()
    {
        return bagOfSetEntries.remove();
        //TODO Project1
//        return null; //THIS IS A STUB
    } // end remove

    public boolean removeElement(T anEntry)
    {
        return bagOfSetEntries.removeElement(anEntry);
        //TODO Project1
//        return false; //THIS IS A STUB
    } // end remove

    // Displays a set.
    public void displaySet()
    {
        bagOfSetEntries.display();;
        //TODO Project1
    } // end displaySet

    public static void main(String[] args)
    {
        String[] inputData = {"A", "B", "C", "D", "A", "C", "B", "B"};
        System.out.println("Creating aSet and adding to it elements from inputData: " + Arrays.toString(inputData));
        SetInterface<String> aSet = new LinkedSetWithLinkedBag<>();
        for (int i=0; i < inputData.length; i++)
        {
            aSet.add(inputData[i]);
        }
        aSet.displaySet();

        System.out.println("\nClearing aSet");
        aSet.clear();
        aSet.displaySet();
        System.out.println("aSet isEmpty returns " + aSet.isEmpty());

        System.out.println("\nCreating set1 and set2");
        SetInterface<String> set1 = new LinkedSetWithLinkedBag<>();
        SetInterface<String> set2 = new LinkedSetWithLinkedBag<>();

        System.out.println("\nAdding elements to set1");
        set1.add("A");
        set1.add("A");
        set1.add("B");
        set1.add("A");
        set1.add("C");
        set1.add("A");
        System.out.println("set1 is ");
        set1.displaySet();

        System.out.println("\nAdding elements to set2");
        set2.add("A");
        set2.add("B");
        set2.add("B");
        set2.add("A");
        set2.add("C");
        set2.add("C");
        set2.add("D");
        System.out.println("set2 is ");
        set2.displaySet();

        System.out.println("\nset1 contains A: " + set1.contains("A"));
        System.out.println("set1 contains E: " + set1.contains("E"));

        set1.removeElement("B");
        System.out.println("After removing B from set1, ");
        set1.displaySet();

        System.out.println("After removing " + set1.remove() + " from set1, ");
        set1.displaySet();
    } // end main
} // end LinkedSetWithLinkedBag

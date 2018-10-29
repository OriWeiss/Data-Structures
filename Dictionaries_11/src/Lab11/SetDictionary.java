package Lab11;

import java.util.*;

/**
 * @author Ori Weiss
 * @version 4/10/2018
 */

public class SetDictionary<K extends Comparable<? super K>> implements SetInterface<K>, Iterable<K>
{
    private TreeMap<K, Boolean> items;

    public SetDictionary()
    {
        this.items = new TreeMap<K, Boolean>();
       // TODO Project 1
    } // end default constructor

    public boolean add(K newEntry)
    {
        items.put(newEntry, true);
        return true;
        // TODO Project 1
//        return false;
    } // end add

    public boolean remove(K anEntry)
    {
        boolean result = false;
        if(items.remove(anEntry) != null) {
            result = true;
        }
        return result;
        // TODO Project 1
        //return false;
    } // end remove

    public void clear()
    {
        items.clear();
        // TODO Project 1
    } // end clear

    public boolean contains(K anEntry)
    {
        return items.containsKey(anEntry);
        // TODO Project 1
        //return false;
    } // end contains

    public int getCurrentSize()
    {
        return items.size();
        // TODO Project 1
        //return 0;
    } // end getCurrentSize

    public boolean isEmpty()
    {
        return items.isEmpty();
        // TODO Project 1
        //return false;
    } // end isEmpty

    public boolean equals(Object o)
    {
        boolean same = true;
        if(this == o){
            same = true;
        }
        else if(o == null || getClass() != o.getClass()){
            same = false;
        }
        else{
            SetDictionary<K> other = (SetDictionary<K>) o;

            if(this.getCurrentSize() == other.getCurrentSize()){
                if(!this.items.equals(other.items)){
                    same = false;
                }
            }
            else {
                same = false;
            }
        }
        return same;
        // TODO Project 1
       // return false;
    } // equals

    public Iterator<K> getIterator()
    {
        Set<K> keys = this.items.keySet();
        Iterator<K> keyIterator = keys.iterator();
        return keyIterator;
        // TODO Project 1
        //return null;
    } // end getIterator

    public Iterator<K> iterator()
    {
        return this.getIterator();
        // TODO Project 1
    } // end iterator

    public K[] toArray()
    {
        // TODO Project 1
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        K[] result = (K[]) new Comparable[getCurrentSize()]; // unchecked cast
        Iterator<K>  thisIter = this.iterator();
        for (int i = 0; i < getCurrentSize(); i++) {
            result[i] = thisIter.next();
        }
        //MUST BE IMPLEMENTED WITH ITERATOR
        return result;
    } // end toArray

    public SetInterface<K> union(SetInterface<K> otherSet)
    {
        // TODO Project 1
        SetInterface<K> result = new SetDictionary<>();
        this.items.forEach((K,v) -> {
            result.add(K);
        });
        SetDictionary<K> other = (SetDictionary<K>) otherSet;
        ((SetDictionary<K>) otherSet).forEach((K)->{
            result.add(K);
        });

        //MUST BE IMPLEMENTED WITH ITERATORS USING forEach lambda CONSTRUCT
        // AS SHOWN IN LectureDictionary EXAMPLES

        return result;
    } // end union

    public SetInterface<K> intersection(SetInterface<K> otherSet)
    {
        // TODO Project 1
        SetInterface<K> result = new SetDictionary<>();

    //compares with iterator if both equal moves both otherwise moves the smaller value
        //MUST BE IMPLEMENTED WITH ITERATORS

        // UTILIZE TRY_CATCH BLOCK
        try
        {
            Iterator<K>  thisIter = this.iterator();
            Iterator<K> otherIter = otherSet.getIterator();
            K thisVal = thisIter.next();
            K otherVal = otherIter.next();
            while (true)
            {
                if (thisVal.compareTo(otherVal) == 0){
                    result.add(thisVal);
                    thisVal = thisIter.next();
                    otherVal = otherIter.next();

                }
                else if(thisVal.compareTo(otherVal) > 0){
                    otherVal = otherIter.next();
                }
                else{
                    thisVal = thisIter.next();
                }

            } // end while
        } catch (NoSuchElementException nsee)
        {
            System.out.println("Finished creating intersection.");
        }

        return result;
    } // end intersection

    public static void main(String args[])
    {
        System.out.println("CREATING set1");
        SetInterface<Integer> set1 = new SetDictionary<>();
        set1.add(1);
        set1.add(3);
        set1.add(2);
        set1.add(0);
        set1.add(-1);

        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("--> contains for -1 yields " + set1.contains(-1));
        System.out.println("--> contains for -2 yields " + set1.contains(-2));
        System.out.println("--> contains for 3 yields " + set1.contains(3));
        System.out.println("--> contains for 4 yields " + set1.contains(4));

        set1.add(1);
        System.out.println("\n--> Added 1 again to the set1, should be the same");
        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("--> Iterating over set1 utilizing getIterator method");
        Iterator<Integer> kIter = set1.getIterator();
        while (kIter.hasNext())
        {
            System.out.println("\t" + kIter.next());
        } // end while

        System.out.println("--> Iterating over set1 utilizing iterator method");
        kIter = ((SetDictionary<Integer>) set1).iterator();
        while (kIter.hasNext())
        {
            System.out.println("\t" + kIter.next());
        } // end while

        System.out.println("--> Iterating over set1 utilizing forEach lambda");
        ((SetDictionary<Integer>) set1).items.forEach((k,v) -> System.out.println("\t " + k));

        System.out.println("\n--> Removing -1 20 3 from set1:");
        boolean result = set1.remove(-1);
        if (result)
            System.out.println("---> -1 was removed - CORRECT");
        else
            System.out.println("---> -1 was not removed - INCORRECT");
        result = set1.remove(20);
        if (result)
            System.out.println("---> 20 was removed - INCORRECT");
        else
            System.out.println("---> 20 was not removed - CORRECT");
        result = set1.remove(3);
        if (result)
            System.out.println("---> 3 was removed - CORRECT");
        else
            System.out.println("---> 3 was not removed - INCORRECT");

        System.out.println("--> Should just have 0 1 and 2 now");
        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("CREATING set2");
        SetInterface<Integer> set2 = new SetDictionary<>();
        set2.add(1);
        set2.add(3);
        set2.add(2);
        set2.add(-1);
        set2.add(5);
        set2.add(8);

        System.out.println("--> set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        if (set1.equals(set2))
            System.out.println("set1 and set2 are the same - INCORRECT");
        else
            System.out.println("set1 and set2 are NOT the same - CORRECT");
        System.out.println();

        System.out.println("CREATING UNION OF set1 and set2");
        SetInterface<Integer> testUnion = set1.union(set2);
        System.out.print("--> The union of set1 and set2 has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        System.out.println("--> set1 should be unchanged");
        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("--> set2 should be unchanged");
        System.out.println("--> set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING UNION OF set1 and set1");
        testUnion = set1.union(set1);
        System.out.print("--> The union of set1 and set1 has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        if (set1.equals(testUnion))
            System.out.println("set1 and testUnion are the same - CORRECT");
        else
            System.out.println("set1 and testUnion are NOT the same - INCORRECT");
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set1 and set2");
        SetInterface<Integer> testIntersection = set1.intersection(set2);
        System.out.print("--> The intersection of set1 and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("--> set1 should be unchanged");
        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("--> set2 should be unchanged");
        System.out.println("--> set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set2 and set1");
        testIntersection = set2.intersection(set1);
        System.out.print("--> The intersection of set2 and set1 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("--> set1 should be unchanged");
        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("--> set2 should be unchanged");
        System.out.println("--> set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set2 and set2");
        testIntersection = set2.intersection(set2);
        System.out.print("--> The intersection of set2 and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("--> set1 should be unchanged");
        System.out.println("--> set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("--> set2 should be unchanged");
        System.out.println("--> set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        if (set2.equals(testIntersection))
            System.out.println("set2 and testIntersection are the same - CORRECT");
        else
            System.out.println("set2 and testIntersection are NOT the same - INCORRECT");
        System.out.println();

        System.out.println("CREATING INTERSECTION OF testUnion and set2");
        testIntersection = testUnion.intersection(set2);
        System.out.print("--> The intersection of testUnion and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("--> testUnion should be unchanged");
        System.out.println("--> testUnion has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        System.out.println("--> set2 should be unchanged");
        System.out.println("--> set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        if (set2.equals(testIntersection))
            System.out.println("set2 and testIntersection are the same - INCORRECT");
        else
            System.out.println("set2 and testIntersection are NOT the same - CORRECT");
        System.out.println();
    } // end main
}

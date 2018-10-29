//Author: Ori Weiss//Date: 02/06/2018package Lab02;import java.util.Arrays;/** * A class that implements a bag of objects by using an array. * The bag is never full. * * @author YOUR NAME * @version 1/30/2018 */public class ResizableArrayBag<T extends Comparable<? super T>> implements BagInterface<T>{    private T[] bag; // Cannot be final due to doubling    private int numberOfEntries = 0;    private boolean initialized = false;    // TODO Project3 - change the DEFAULT_CAPACITY to 25    private static final int DEFAULT_CAPACITY = 25; // Initial capacity of bag    private static final int MAX_CAPACITY = 10000;    /**     * Creates an empty bag whose initial capacity is 25.     */    public ResizableArrayBag()    {        this(DEFAULT_CAPACITY);    } // end default constructor    /**     * Creates an empty bag having a given initial capacity.     *     * @param initialCapacity The integer capacity desired.     */    public ResizableArrayBag(int initialCapacity)    {        checkCapacity(initialCapacity);        // The cast is safe because the new array contains null entries        @SuppressWarnings("unchecked")        T[] tempBag = (T[]) new Comparable<?>[initialCapacity]; // Unchecked cast        this.bag = tempBag;        this.numberOfEntries = 0;        this.initialized = true;    } // end constructor    /**     * Creates a bag containing given entries.     *     * @param contents         An array of objects.     * @param numberOfElements - the number of entries we want to copy starting at index 0     */    public ResizableArrayBag(T[] contents, int numberOfElements)    {        checkCapacity(numberOfElements);        this.bag = Arrays.copyOf(contents, numberOfElements);        this.numberOfEntries = numberOfElements;        this.initialized = true;    } // end constructor    /**     * Adds a new entry to this bag.     *     * @param newEntry The object to be added as a new entry.     * @return True.     */    public boolean add(T newEntry)    {        checkInitialization();        if (isArrayFull())        {            doubleCapacity();        }        this.bag[this.numberOfEntries] = newEntry;        this.numberOfEntries++;        return true;    } // end add    /**     * Retrieves all entries that are in this bag.     *     * @return A newly allocated array of all the entries in this bag.     */    public T[] toArray()    {        checkInitialization();        // The cast is safe because the new array contains null entries.        @SuppressWarnings("unchecked")        T[] result = (T[]) new Comparable<?>[this.numberOfEntries]; // Unchecked cast        for (int index = 0; index < this.numberOfEntries; index++)        {            result[index] = this.bag[index];        }        return result;    } // end toArray    /**     * Sees whether this bag is empty.     *     * @return True if this bag is empty, or false if not.     */    public boolean isEmpty()    {        return this.numberOfEntries == 0;    } // end isEmpty    /**     * Gets the current number of entries in this bag.     *     * @return The integer number of entries currently in this bag.     */    public int getCurrentSize()    {        return this.numberOfEntries;    } // end getCurrentSize    /**     * Counts the number of times a given entry appears in this bag.     *     * @param anEntry The entry to be counted.     * @return The number of times anEntry appears in this ba.     */    public int getFrequencyOf(T anEntry)    {        checkInitialization();        int counter = 0;        for (int index = 0; index < this.numberOfEntries; index++)        {            if (anEntry.equals(this.bag[index]))            {                counter++;            }        }        return counter;    } // end getFrequencyOf    /**     * Tests whether this bag contains a given entry.     *     * @param anEntry The entry to locate.     * @return True if this bag contains anEntry, or false otherwise.     */    public boolean contains(T anEntry)    {        checkInitialization();        return getIndexOf(anEntry) > -1; // or >= 0    } // end contains    // Locates a given entry within the array bag.    // Returns the index of the entry, if located,    // or -1 otherwise.    // Precondition: checkInitialization has been called.    private int getIndexOf(T anEntry)    {        int where = -1;        boolean stillLooking = true;        for (int index = 0; stillLooking && (index < this.numberOfEntries); index++)        {            if (anEntry.equals(this.bag[index]))            {                stillLooking = false;                where = index;            }        }        return where;    } // end getIndexOf    /**     * Removes all entries from this bag.     */    public void clear()    {        while (!isEmpty())            remove();    } // end clear    /**     * Removes one unspecified entry from this bag, if possible.     *     * @return Either the removed entry, if the removal     * was successful, or null.     */    public T remove()    {        checkInitialization();        T result = removeEntry(this.numberOfEntries -1); //SHOULD BE -1?        return result;    } // end remove    /**     * Removes one occurrence of a given entry from this bag.     *     * @param anElement The entry to be removed.     * @return True if the removal was successful, or false if not.     */    public boolean removeElement(T anElement)    {        checkInitialization();        int index = getIndexOf(anElement);        T result = removeEntry(index);        return anElement.equals(result);    } // end remove    // Removes and returns the entry at a given index within the array.    // If no such entry exists, returns null.    // Precondition: 0 <= givenIndex < numberOfEntries.    // Precondition: checkInitialization has been called.    private T removeEntry(int givenIndex)    {        T result = null;        if (!isEmpty() && (givenIndex >= 0))        {            result = this.bag[givenIndex];          // Entry to remove            this.numberOfEntries--;            this.bag[givenIndex] = this.bag[this.numberOfEntries];  // Replace entry to remove with last entry            this.bag[this.numberOfEntries] = null;             // Remove reference to last entry        }        return result;    } // end removeEntry    // Returns true if the array bag is full, or false if not.    private boolean isArrayFull()    {        return this.numberOfEntries >= this.bag.length;    } // end isArrayFull    // Doubles the size of the array bag.    // Precondition: checkInitialization has been called.    private void doubleCapacity()    {        int newLength = 2 * this.bag.length;        checkCapacity(newLength);        this.bag = Arrays.copyOf(this.bag, newLength);    } // end doubleCapacity    // Throws an exception if the client requests a capacity that is too large.    private void checkCapacity(int capacity)    {        if (capacity > MAX_CAPACITY)            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +                    "allowed maximum of " + MAX_CAPACITY);    } // end checkCapacity    // Throws an exception if receiving object is not initialized.    private void checkInitialization()    {        if (!this.initialized)            throw new SecurityException("Uninitialized object used " +                    "to call an ArrayBag method.");    } // end checkInitialization    /**     * Displays all the elements in the bag     */    public void display()    {        if (this.numberOfEntries > 0)        {            System.out.print("There are " + this.numberOfEntries + " element(s): ");            for (int index = 0; index < this.numberOfEntries; index++)            {                System.out.print(this.bag[index] + " ");            }            System.out.println();        }        else            System.out.println("The bag is empty");    } // end display    //    // +++++++++++++++++++  NEW METHODS  +++++++++++++++++++++++++++    //    /**     * Checks if the given bag called other is the same as the bag     *     * @param o the other bag to be compared with     * @return true both bags are the same     */    public boolean equals(Object o)    {        boolean same = true;        if (this == o)            same = true;        else if (o == null || getClass() != o.getClass())            same = false;        else        {            ResizableArrayBag<T> other = (ResizableArrayBag<T>) o;            if(this.numberOfEntries == other.numberOfEntries){                for (int i = 0; same && i < this.numberOfEntries ; i++) {                    if(!this.bag[i].equals(other.bag[i]) ){                        same = false;                    }                }            }            else {                same = false;            }            //TODO Project3        }        // one return statement per method please        return same; // this is a stub, must return the computed value of same    }    /**     * Removes the largest entry from the this.bag     *     * @return - null if the element was not found or the largest element. Uses removeEntry(givenIndex) method     */    public T removeMax()    {        //TODO Project3        T result = null;        T max = this.bag[0];        int maxIndex = -1;        for (int i = 0; i < this.numberOfEntries -1; i++) { //SHOULD BE -1?            if(max.compareTo(this.bag[i]) < 0){                max = this.bag[i];                maxIndex = i;            }        }        if(maxIndex != -1){//checks to be sure there is a max otherwise returns null            result = this.removeEntry(maxIndex);        }        return result;    } // end removeMax    /**     * Removes every occurrence of a given entry from this bag. Uses removeEntry(givenIndex) method     *     * @param anEntry the entry to be removed     */    public void removeEvery(T anEntry)    {        checkInitialization();        for (int i = this.numberOfEntries -1; i >= 0 ; i--) { //SHOULD BE -1?            if(anEntry.equals(this.bag[i])){                this.bag[i] = this.bag[this.numberOfEntries -1];  //SHOULD BE -1?                this.numberOfEntries--;            }        }        //TODO Project3        // must utilize only one loop that starts with the last element    } // end removeEvery    /**     * Replaces the last entry in this bag with a given object.     *     * @param replacement the given object     * @return the original entry in the bag that was replaced or null if the bag was empty     */    public T replace(T replacement)    {        checkInitialization();        int lastEntryIndex = this.numberOfEntries -1; //SHOULD BE -1?        T replacedObject = this.bag[lastEntryIndex];        this.bag[lastEntryIndex] = replacement;        //TODO Project3        // replace data at selected index        // one return statement per method please        return replacedObject; //THIS IS A STUB    } // end replace    /**     * Creates a new bag that combines the contents of this bag and     * the second given bag without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the union of the two bags     */    public BagInterface<T> union(BagInterface<T> otherBag)    {        ResizableArrayBag<T> other = (ResizableArrayBag<T>) otherBag;        ResizableArrayBag<T> unionBag = new ResizableArrayBag<T>(this.bag,this.numberOfEntries);        checkInitialization();        for (int i = 0; i < other.numberOfEntries; i++) {            unionBag.add(other.bag[i]);        }        //TODO Project3        // one return statement per method please        return unionBag;    } // end union    /**     * Creates a new bag that contains those objects that occur in both this     * bag and the second given bag without affecting the original two bags.     * utilizes getIndexOf(anEntry) and removeEntry(givenIndex) methods     *     * @param otherBag the given bag     * @return a bag that is the intersection of the two bags     */    public BagInterface<T> intersection(BagInterface<T> otherBag) {        ResizableArrayBag<T> other = (ResizableArrayBag<T>) otherBag;        ResizableArrayBag<T> intersectionBag = new ResizableArrayBag<T>();        ResizableArrayBag<T> copyOfOtherBag = new ResizableArrayBag<T>(other.bag, other.numberOfEntries);        //TODO Project3        checkInitialization();        for (int i = 0; i < this.numberOfEntries; i++) {            int index = copyOfOtherBag.getIndexOf(this.bag[i]);            if (index != -1) {                T removedObject = copyOfOtherBag.removeEntry(index);                intersectionBag.add(removedObject);            }        }        return intersectionBag;    }    /**     * Creates a new bag of objects that would be left in this bag     * after removing those that also occur in a second given bag     * without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the difference of the two bags     */    public BagInterface<T> difference(BagInterface<T> otherBag)    {        checkInitialization();        ResizableArrayBag<T> other = (ResizableArrayBag<T>) otherBag;        ResizableArrayBag<T> differenceBag = new ResizableArrayBag<T>(this.bag, this.numberOfEntries);        for (int i = 0; i < other.numberOfEntries ; i++) { //SHOULD BE -1? SHOULD BE OTHER #OFENTRIES OR DIFFERENCE BAG #OFENTRIES?            int index = differenceBag.getIndexOf(other.bag[i]);            if(index != -1 ){                differenceBag.removeEntry(index);            }        }        return differenceBag;        //TODO Project3        // do NOT call contains, utilize getIndexOf(anEntry) and removeEntry(givenIndex) methods instead    } // end difference    /**     * Creates a new bag of objects that are in this bag and are less than a given object.     *     * @param anEntry a given object     * @return a new bag of objects that are in this bag and are less than anEntry     */    public BagInterface<T> getAllLessThan(T anEntry)    {        checkInitialization();        BagInterface<T> result = new ResizableArrayBag<T>();        for (int i = 0; i < this.numberOfEntries ; i++) {            if(anEntry.compareTo(this.bag[i]) > 0){                result.add(this.bag[i]);            }        }        //TODO Project3        // utilize compareTo method        // one return statement per method please        return result;    } // end getAllLessThan    /**     * Checks if all the elements of the given bag are also included in the other bag     *     * @param other bag to check     * @return returns true if all the elements of the given bag are also included in the other bag     */    public boolean isSubset(BagInterface<T> other)    {        checkInitialization();        boolean result = false;        if(this.difference(other).isEmpty()){            result = true;        }        //TODO Project3        // utilize difference method        // one return statement per method please        return result; //THIS IS A STUB    }    public static void main(String[] args)    {        System.out.println("RUNNING TEST CASES");        ResizableArrayBag<String> bag1 = new ResizableArrayBag<>(3);        ResizableArrayBag<String> bag2 = new ResizableArrayBag<>();        bag1.add("C");        bag1.add("A");        bag1.add("A");        bag1.add("A");        bag1.add("X");        System.out.println("Created bag1:");        bag1.display();        System.out.println("Created bag2:");        bag2.display();        System.out.println("After removing the last element " + bag1.remove() + " from bag1, it contains:");        bag1.display();        // testing equals        System.out.println("\n***Testing equals method***");        System.out.println("Are bag1 and bag2 equal? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        System.out.println("Are bag2 and bag1 equal? --> " + (bag2.equals(bag1) ? "YES" : "NO"));        bag2.add("A");        bag2.add("A");        bag2.add("A");        bag2.add("C");        bag2.add("X");        System.out.println("bag2:");        bag2.display();        System.out.println("Are bag1 and bag2 equal? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        System.out.println("Removed " + bag2.remove() + " from bag2:");        bag2.display();        System.out.println("Are bag1 and bag2 equal now? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        ResizableArrayBag<String> bagCopyOfBag1 = new ResizableArrayBag<>(bag1.toArray(), bag1.getCurrentSize());        System.out.println("Created bagCopyOfBag1:");        bagCopyOfBag1.display();        System.out.println("Are bag1 and bagCopyOfBag1 equal? --> " + (bag1.equals(bagCopyOfBag1) ? "YES" : "NO"));        bag1.clear();        bag1.add("C");        bag1.add("A");        bag1.add("A");        bag1.add("X");        bag1.add("A");        bag2.clear();        bag2.add("A");        bag2.add("B");        bag2.add("B");        bag2.add("A");        bag2.add("C");        bag2.add("C");        bag2.add("D");        System.out.println("\n***Testing union, intersection, difference, removeMax, getAllLessThan and isSubset methods***");        System.out.println("bag1:");        bag1.display();        System.out.println("bag2:");        bag2.display();        // testing union        System.out.println("\n***Testing union method***");        BagInterface<String> everything = bag1.union(bag2);        System.out.println("The union of bag1 and bag2 is:");        everything.display();        // testing removeMax        System.out.println("\n***Testing removeMax method***");        String largest = everything.removeMax();        System.out.println("Removed the largest element \"" + largest + "\" from the union bag; the current content is:");        everything.display();        everything.clear();        largest = everything.removeMax();        if (largest == null)            System.out.println("The bag is empty and removeMax returned null - CORRECT");        else            System.out.println("The bag is empty bur removeMax returned did not return null - INCORRECT");        // testing intersection        System.out.println("\n***Testing intersection method***");        BagInterface<String> commonItems = bag1.intersection(bag2);        System.out.println("The intersection of bag1 and bag2 is:");        commonItems.display();        // testing difference        System.out.println("\n***Testing difference method***");        BagInterface<String> leftOver = bag1.difference(bag2);        System.out.println("The difference of bag1 and bag2 is:");        leftOver.display();        leftOver = bag2.difference(bag1);        System.out.println("The difference of bag2 and bag1 is:");        leftOver.display();        // testing getAllLessThan        System.out.println("\n***Testing getAllLessThan method***");        BagInterface<String> smaller = bag1.getAllLessThan("Z");        System.out.println("The following entries in bag1 are smaller than \"Z\":");        smaller.display();        smaller = bag2.getAllLessThan("C");        System.out.println("The following entries in bag2 are smaller than \"C\":");        smaller.display();        // testing subset        System.out.println("\n***Testing isSubset method***");        System.out.println("Is bag1 a subset of bag1 ? --> " + (bag1.isSubset(bag1) ? "YES" : "NO"));        System.out.println("Is bag1 a subset of bag2 ? --> " + (bag1.isSubset(bag2) ? "YES" : "NO"));        ResizableArrayBag<String> emptyBag = new ResizableArrayBag<>();        System.out.println("Is an empty bag a subset of bag2 ? --> " + (emptyBag.isSubset(bag2) ? "YES" : "NO"));        System.out.println("Is bag2 a subset of an empty bag ? --> " + (bag2.isSubset(emptyBag) ? "YES" : "NO"));        ResizableArrayBag<String> bag3 = new ResizableArrayBag<>();        ResizableArrayBag<String> bag4 = new ResizableArrayBag<>();        bag3.add("A");        bag3.add("B");        bag3.add("C");        System.out.println("Created bag3:");        bag3.display();        bag4.add("B");        bag4.add("C");        bag4.add("A");        System.out.println("Created bag4:");        bag4.display();        System.out.println("Is bag3 a subset of bag4 ? --> " + (bag3.isSubset(bag4) ? "YES" : "NO"));        bag4.add("Z");        System.out.println("Is bag3 a subset of bag4 after adding \"Z\" to it ? --> " + (bag3.isSubset(bag4) ? "YES" : "NO"));        System.out.println("Is bag4 a subset of bag3 ? --> " + (bag4.isSubset(bag3) ? "YES" : "NO"));        System.out.println("Adding  \"Z\" to bag 3 twice");        bag3.add("Z");        bag3.add("Z");        System.out.println("bag3:");        bag3.display();        System.out.println("bag4:");        bag4.display();        System.out.println("Is bag3 a subset of bag4 ? --> " + (bag3.isSubset(bag4) ? "YES" : "NO"));        bag1.clear();        bag1.add("A");        bag1.add("A");        bag1.add("B");        bag1.add("X");        bag1.add("A");        bag1.add("C");        bag1.add("A");        // testing replace        System.out.println("\n***Testing replace method***");        System.out.println("bag1:");        bag1.display();        System.out.println("Replaced \"" + bag1.replace("X") + "\" with \"X\"");        System.out.println("Now bag1 contains:");        bag1.display();        // testing removeEvery        System.out.println("\n***Testing removeEvery method***");        System.out.println("bag1:");        bag1.display();        System.out.println("Removing all \"Z\"");        bag1.removeEvery("Z");        System.out.println("After removing all \"Z\" bag1 contains:");        bag1.display();        System.out.println("Removing all \"A\"");        bag1.removeEvery("A");        System.out.println("After removing all \"A\" bag1 contains:");        bag1.display();        System.out.println("Removing all \"X\"");        bag1.removeEvery("X");        System.out.println("After removing all \"X\" bag1 contains:");        bag1.display();    } // end main} // end ResizableArrayBag
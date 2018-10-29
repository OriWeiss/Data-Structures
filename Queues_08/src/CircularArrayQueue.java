package Lab08;

/** Author: Ori Weiss
 * @version 3/13/2018.
 */
public class CircularArrayQueue<T> implements Lab08.QueueInterface<T>
{
    private T[] queue; // Circular array of queue entries and one unused location
    private int frontIndex; // Index of front entry
    private int backIndex;  // Index of back entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 10000;

    public CircularArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public CircularArrayQueue(int initialCapacity)
    {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        this.queue = tempQueue;
        this.frontIndex = 0;
        this.backIndex = initialCapacity;
        this.initialized = true;
    } // end constructor

    public CircularArrayQueue(T[] initialContent)
    {
        this(initialContent.length);
        for (int i = 0; i < initialContent.length ; i++) {
            enqueue(initialContent[i]);
        }


        // TODO-DONE Project 2A

    } // end constructor

    public void enqueue(T newEntry)
    {
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
//        System.out.println("enqueue(" + newEntry + ")");               // ***TESTING
        // TODO-DONE Project 2A
//        System.out.println("queue[" + backIndex + "] = " + newEntry);  // ***TESTING
    } // end enqueue

    public T getFront() throws Lab08.EmptyQueueException
    {
        checkInitialization();
        if(isEmpty()){
            throw new Lab08.EmptyQueueException();
        }
        else{
            return queue[frontIndex];
        }
        // TODO-DONE Project 2A
//        return null;
    } // end getFront

    public T dequeue() throws Lab08.EmptyQueueException
    {
        checkInitialization();
        if(isEmpty()){
            throw new Lab08.EmptyQueueException();
        }
        else{
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) %queue.length;
            return front;
        }
        // TODO-DONE Project 2A
//        return null;
    } // end dequeue

    public boolean isEmpty()
    {
        return frontIndex == ((backIndex + 1) % queue.length);
        // TODO-DONE Project 2A
//        return true;  // THIS IS A STUB
    } // end isEmpty

    public void clear()
    {
        checkInitialization();
        if(!isEmpty()){
            for(int index = frontIndex;index != backIndex; index = (index + 1) % queue.length){
                queue[index] = null;
            }
            queue[backIndex] = null;
        }
        frontIndex = 0;
        backIndex = queue.length - 1;
        // null out only the used slots
        // TODO-DONE Project 2A

    } // end clear


    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException("CircularArrayQueue object is not initialized properly.");
    } // end checkInitialization

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a queue " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity

    // Doubles the size of the array queue if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        if(frontIndex == ((backIndex + 2 ) % queue.length)){
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2*oldSize;
            checkCapacity(newSize);
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for(int index = 0; index < oldSize -1; index++){
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize -2;
        }
        // TODO-DONE Project 2A

    } // end ensureCapacity
}

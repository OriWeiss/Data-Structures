package Lab13;

import java.util.*;
/**
 * @author Ori Weiss
 * @version 4/24/2018
 */

public class WorkingWithHeaps
{
    private ArrayList<Node<Integer>> lists;

    public WorkingWithHeaps()
    {
        this.lists = new ArrayList<>();
        createLists();
    }

    public Node<Integer> mergeKSortedLists()
    {
        Node<Integer> headOfMergedList = null;

        // TODO-DONE Project 4b

        class HeapComparator implements Comparator<Node<Integer>>{ //Note: I used some online help for this method.
                // website I use to help is https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
                //I used it as a resource to help me build this class
            public int compare(Node<Integer> n1 , Node<Integer> n2){
                if(n1.data < n2.data){
                    return -1;
                }
                else if(n1.data > n2.data){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        PriorityQueue<Node<Integer>> heap = new PriorityQueue<Node<Integer>>(5,new HeapComparator());
        for (int i = 0; i < this.lists.size(); i++) { //adds all the node data into a queue
            Node<Integer> listNodeHead = this.lists.get(i);
            Node<Integer> nextNode = listNodeHead;
            while(nextNode != null){
                heap.offer(nextNode);
                nextNode = nextNode.next;
            }
        }
        if(!heap.isEmpty()) { //may not be needed but always good to check if queue is empty
            headOfMergedList = heap.poll();//creating the new linked chain
        }
        headOfMergedList.next = heap.peek();
        while(!heap.isEmpty()){ //links the rest of the nodes
            heap.poll().next = heap.peek();
        }
        return headOfMergedList;
    }

    public void createLists()
    {
        Scanner keyboard = new Scanner(System.in);
        int numberOfLists;
        do
        {
            System.out.println("How many lists to create?");
            numberOfLists = keyboard.nextInt();
        } while (numberOfLists < 1);

        final int MIN_SIZE = 5;
        int maxSize;
        do
        {
            System.out.println("Maximum number of elements for the lists?");
            maxSize = keyboard.nextInt();
        } while (maxSize < MIN_SIZE);

        Random random = new Random(11);
        final int MAX_FIRST_VALUE = 7;
        Node<Integer> firstNode;
        Node<Integer> current;
        int sizeUpTo;
        int randomNumber;

        for (int list = 0; list < numberOfLists; list++)
        {
            firstNode = null;
            current = null;
            sizeUpTo = random.nextInt(maxSize - MIN_SIZE + 1) + MIN_SIZE;
            randomNumber = random.nextInt(MAX_FIRST_VALUE) + 1;
            for (int i = 1; i <= sizeUpTo; i++)
            {
                if (random.nextBoolean())
                {
                    if (firstNode == null)
                    {
                        firstNode = new Node<>(randomNumber);
                        current = firstNode;
                    }
                    else
                    {
                        current.next = new Node<>(randomNumber);
                        current = current.next;
                    }
                    randomNumber += i;
                }
                randomNumber++;
            }
            this.lists.add(firstNode);
        }
        displayCreatedLists();
    }

    private void displayCreatedLists()
    {
        System.out.println("\nCreated lists:");
        // TODO-DONE Project 4a
        for (int i = 0; i < this.lists.size(); i++) {
            if(this.lists.get(i)!= null) {
                Node<Integer> node = this.lists.get(i).next;
                System.out.print(this.lists.get(i).data); //ADDED this because i could'nt figure out how to print the
                    //first number
               while(node != null) {
                   System.out.print(" " + node.data);
                   node = node.next;
               }
                System.out.println();
           }
           else{
               System.out.println("<empty>");
           }
        }
    }

    public static void main(String args[])
    {
        WorkingWithHeaps tester = new WorkingWithHeaps();

        Node<Integer> mergedHead = tester.mergeKSortedLists();
        System.out.println("\nMerged List:");
        if (mergedHead == null)
            System.out.println("The list is empty");
        else
        {
            Node<Integer> current = mergedHead;
            while (current != null)
            {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
        System.out.println("\n*** Done ***");
    } // end main

    private class Node<S>
    {
        private S data;
        private Node<S> next;

        private Node(S dataPortion)
        {
            this.data = dataPortion;
            this.next = null;
        }

        private Node(S dataPortion, Node nextNode)
        {
            this.data = dataPortion;
            this.next = nextNode;
        }
    } // end Node
}

package Lab4;/** * A class of stacks whose entries are stored in a chain of nodes. * * @author ORI WEISS * @version 02/13/2018 */public final class LinkedStack<T> implements TextbookStackInterface<T>{    private Node<T> topNode; // References the first node in the chain    public LinkedStack()    {        this.topNode = null;        // TODO-DONE PROJECT #3    } // end default constructor    public void push(T newEntry)    {        Node newNode = new Node(newEntry,topNode);        this.topNode = newNode;        // TODO-DONE PROJECT #3    } // end push    public T peek() throws InsufficientNumberOfElementsOnStackException    {        if(isEmpty()){            throw new InsufficientNumberOfElementsOnStackException("Stack is Empty");        }        else{            T top = null;            if(topNode != null){                top = topNode.data;            }            return top;        }        // TODO-DONE PROJECT #3//        return null; // THIS IS A STUB    } // end peek    public T peek2() throws InsufficientNumberOfElementsOnStackException    {        if(isEmpty() || this.topNode.next == null){            throw new InsufficientNumberOfElementsOnStackException("Stack is Empty");        }        else{            return this.topNode.next.data;        }        // TODO-DONE PROJECT #3//        return null; // THIS IS A STUB    } // end peek2    public T pop() throws InsufficientNumberOfElementsOnStackException    {        if(isEmpty()) {            throw new InsufficientNumberOfElementsOnStackException("Stack is Empty");        }        else{            T top = peek();            if(topNode != null){                topNode = topNode.next;            }            return top;        }        // TODO-DONE PROJECT #3//        return null; // THIS IS A STUB    } // end pop    public boolean isEmpty()    {        return topNode == null;        // TODO-DONE PROJECT #3//        return false;  // THIS IS A STUB    } // end isEmpty    public void clear()    {        while(!isEmpty()){            pop();        }        // TODO-DONE PROJECT #3    } // end clear    // These methods are only for testing of array implementation    public int getTopIndex()    {        return 0;    }    public int getCapacity() { return 0; }    private class Node<S>    {        private S data; // Entry in stack        private Node<S> next; // Link to next node        private Node(S dataPortion)        {            this(dataPortion, null);        } // end constructor        private Node(S dataPortion, Node<S> linkPortion)        {            this.data = dataPortion;            this.next = linkPortion;        } // end constructor    } // end Node    public static void main(String[] args)    {        System.out.println("*** Create a stack ***");        LinkedStack<String> myStack = new LinkedStack<>();        System.out.println("--> Add to stack to get: " +                "Joe Jane Jill Jess Jim\n");        myStack.push("Jim");        myStack.push("Jess");        myStack.push("Jill");        myStack.push("Jane");        myStack.push("Joe");        System.out.println("Done adding 5 elements.\n");        System.out.println("--> Testing peek, peek2, and pop:");        while (!myStack.isEmpty())        {            String top = myStack.peek();            System.out.println(top + " is at the top of the stack.");            try            {                String beneathTop = myStack.peek2();                System.out.println(beneathTop + " is just beneath the top of the stack.");            } catch (InsufficientNumberOfElementsOnStackException inoeose)            {                System.out.println(" CORRECT - exception has been thrown: " + inoeose.getMessage());            }            top = myStack.pop();            System.out.println(top + " is removed from the stack.\n");        } // end while        System.out.println("--> The stack should be empty: ");        System.out.println("isEmpty() returns " + myStack.isEmpty());        try        {            String top = myStack.peek();            System.out.println(top + " is at the top of the stack.");        } catch (InsufficientNumberOfElementsOnStackException inoeose)        {            System.out.println(" CORRECT - exception has been thrown: " + inoeose.getMessage());        }        try        {            String top = myStack.pop();            System.out.println(top + " is at the top of the stack.");        } catch (InsufficientNumberOfElementsOnStackException inoeose)        {            System.out.println(" CORRECT - exception has been thrown: " + inoeose.getMessage());        }        try        {            String beneathTop = myStack.peek2();            System.out.println(beneathTop + " is just beneath the top of the stack.");        } catch (InsufficientNumberOfElementsOnStackException inoeose)        {            System.out.println(" CORRECT - exception has been thrown: " + inoeose.getMessage());        }        System.out.println("*** Done ***");    }  // end main} // end LinkedStack
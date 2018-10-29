package Lab08;

/**
 * A class that implements a cipher with repeating key algorithm.
 *
 * @author Ori Weiss
 * @version 3/13/2018
 */


public class Cipher
{
    private Integer[] key;
    private final int ADD_FACTOR = 1;
    private final int SUBTRACT_FACTOR = -1;

    public Cipher(Integer... key)
    {
        this.key = key;
// take key and put it in an array
        // TODO-DONE Project 2B

    }


    public String encode(String message)
    {
        String result = null;
        if(!message.equals(null)) {
            result = code(message, ADD_FACTOR);
        }
        return result;
        // TODO-DONE Project 2B
        //calls code method with ADD_FACTOR

//        return "???";
    }

    public String decode(String encoded)
    {
        String result = null;
        if(!encoded.equals(null)){
            result = code(encoded,SUBTRACT_FACTOR);
        }
        return result;
        // TODO-DONE Project 2B
        //calls code method with SUBTRACT_FACTOR

//        return "???";
    }

    private String code(String message, int addOrSubtractFactor)
    {
        StringBuilder encoded = new StringBuilder();
        Lab08.QueueInterface<Integer> key = getKeyQueue();
        for (int i = 0; i < message.length() ; i++) {
            int next = message.charAt(i) + (key.getFront() * addOrSubtractFactor);
            encoded.append((char)next); // adds the ascii value of next into the string builder
            key.enqueue(key.dequeue()); // puts the first element in the last position
        }
        // TODO-DONE Project 2B

        return encoded.toString();
    }

    private Lab08.QueueInterface<Integer> getKeyQueue()
    {
        Lab08.QueueInterface<Integer> keyQueue = new Lab08.CircularArrayQueue(key);
        return keyQueue;

        // TODO-DONE Project 2B

//        return null; // THIS IS A STUB
    }


    public static void main(String args[])
    {
        System.out.println("**************  TESTING THE CIPHER  **************\n");
        Cipher cipher = new Cipher(5, 12, -3, 8, -9, 4, 10, 2, 3, 5, 1);
        String encoded = cipher.encode("All programmers are playwrights and all computers are lousy actors.");
        System.out.println("--->The original message encoded is:");
        System.out.println(encoded);
        String decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        encoded = cipher.encode("There is no elevator to success, You have to take the stairs...");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        cipher = new Cipher(3, 1, 7, 4, 2, 5);
        encoded = cipher.encode("Trust but Verify");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        encoded = cipher.encode("race car");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);
    }
}



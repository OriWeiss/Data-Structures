package Lab4;

import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

import java.text.DecimalFormat;
import java.util.*;

/**
 * A class that implements math operations utilizing a stack.
 *
 * @author ORI WEISS
 * @version 02/13/2018
 */
public class FunWithStack
{
    public void decimalToBinary()
    {
        System.out.println("DECIMAL TO BINARY CONVERTER");
        // TODO-DONE PROJECT #1
        Scanner keyboard = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        try
        {
            do
            {
                System.out.println("\nPlease enter a positive integer, or type \"stop\"");
                int decimalNumber = keyboard.nextInt();

                System.out.print(decimalNumber + " in binary is --> ");

                while( decimalNumber != 0){
                     int remainer = decimalNumber%2;
                     stack.push(remainer);
                     decimalNumber /= 2;
                }

                while(!stack.isEmpty()){
                    System.out.print(stack.pop());
                }
                // YOUR CODE GOES HERE



                System.out.println();
            } while (true);
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Done with conversion.\n");
        }
    }

    public void ancientMultiplier()
    {
        // TODO-DONE PROJECT #1
        // http://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication
        Stack<Integer> op1 = new Stack<>();
        Stack<Integer> op2 = new Stack<>();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter operand 1: " );
        int operand1 = keyboard.nextInt();
        System.out.println("Please enter operand 2: " );
        int operand2 = keyboard.nextInt();

        int count1 = 0;
        op1.push(1);
        while(op1.peek() + op1.peek() < operand1){
            op1.push(op1.peek() + op1.peek());
            count1++;
        }
        int count2 = 0;
        op2.push(operand2);
        while(count1 > count2){
            op2.push(op2.peek() + op2.peek());
            count2++;
        }
        int sum = 0;
        System.out.println("--> Creating the Mapping table: ");
        while(!op1.isEmpty()){
            int subtract = op1.pop();
            int addition = op2.pop();
            if (operand1 >= subtract) {
                operand1 -= subtract;
                sum += addition;
            }
            System.out.println(subtract +"-->" + addition );
        }
        System.out.println("--> Calculating the result");
        System.out.println("Sum is: " + sum);
    }

    public ArrayList<Integer> noAdjacentDuplicates(int... input)
    {
        // TODO-DONE PROJECT #1
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean repeat = false;
        int index = 0;
        while(index < input.length) {
            if(stack.isEmpty()){
                stack.push(input[index]);
                index++;
            }
            else {
                if (input[index] == stack.peek()) {
                    repeat = true;
                    index++;
                } else if (repeat) {
                    stack.pop();
                    repeat = false;

                } else {
                    stack.push(input[index]);
                    index++;
                }
                //do not move if they are different but flag is true
//                if(!((input[index] == stack.peek()) && repeat)) {
//                    index++;
//                }
            }
        }
        if(repeat && !stack.isEmpty()){
            stack.pop();
        }

        while(!stack.isEmpty()){
            result.add(0,stack.pop());
        }

        System.out.println("input = " + Arrays.toString(input));



        return result;
    }


    public static void main(String[] args)
    {
        FunWithStack funWithStack = new FunWithStack();
        System.out.println("*** DECIMAL TO BINARY CONVERTER ***");
        funWithStack.decimalToBinary();
        System.out.println("*** ANCIENT MULTIPLIER ***");
        funWithStack.ancientMultiplier();

        System.out.println("*** ELIMINATING ADJACENT DUPLICATES ***");

        System.out.println("--> testcase #1");
        ArrayList<Integer> result = funWithStack.noAdjacentDuplicates(1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #2");
        result = funWithStack.noAdjacentDuplicates(1, 9, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        expected.add(1);
        expected.add(9);
        expected.add(5);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #3");
        result = funWithStack.noAdjacentDuplicates(1, 1, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        expected.add(5);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #4");
        result = funWithStack.noAdjacentDuplicates(1, 1, 1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("Done!");
    }
}
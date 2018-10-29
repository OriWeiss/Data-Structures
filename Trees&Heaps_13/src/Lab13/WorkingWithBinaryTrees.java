package Lab13;

import java.util.*;

/**
 * @author Ori Weiss
 * @version 4/24/2018
 */

public class WorkingWithBinaryTrees
{
    public boolean isBST(BinaryNode<String> root)
    {
        Stack<BinaryNode<String>> stack = new Stack<>();
        BinaryNode<String> current = root;
        boolean result = true;
        BinaryNode<String> last = null;
        while((!stack.isEmpty() || current != null) && result ){
            while(current !=null && result) {
                stack.push(current);
                current = current.getLeftChild();
            }
            if(!stack.isEmpty()){

                BinaryNode<String> nextNode = stack.pop();

                assert nextNode != null;
               // System.out.println(nextNode.getData());
                if(last!= null && last.getData().compareTo(nextNode.getData()) > 0){
                    //if last > nextNode
                    result = false;
                }
                else {
                    last = nextNode;
                }
                current = nextNode.getRightChild();
            }
        }
        // TODO-DONE Project 3
        // implement iteratively


        return result;
    }

    public String getSmallest(BinaryNode<String> root)
    {
        BinaryNode<String> current = root;
        while(current.hasLeftChild()){
            current = current.getLeftChild();
        }

        // TODO Project 3
        // implement recursively

        return current.getData();
        //return null;
    }

    public String getSecondLargest(BinaryNode<String> root)
    {
        BinaryNode<String> parentNode = root;
        BinaryNode<String> largestNode = parentNode.getRightChild();
        BinaryNode<String> secondLargestNode = null;
// Find the largest node and its parent (if there is one)
        if (largestNode != null) {
            while (largestNode.getRightChild() != null)
            {
                parentNode = largestNode;
                largestNode = largestNode.getRightChild();
            }
        }
        else
        {
            largestNode = root;
        }

        if (largestNode.getLeftChild() != null)
        {
            secondLargestNode = largestNode.getLeftChild();
            while (secondLargestNode.getRightChild() != null)
            {
                secondLargestNode = secondLargestNode.getRightChild();
            }
        }
        else
        {
            secondLargestNode = parentNode;
        }
        return secondLargestNode.getData();


        // TODO-DONE Project 3
        // implement iteratively


        //return null;
    }

    public BinaryNode<String> buildBSTfromSortedArray(String[] sortedArray, int left, int right)
    { //used online resource https://www.youtube.com/watch?time_continue=109&v=XV-o-ZHrJVM
        if(left > right){
            return null;
        }
        int mid =(left + right)/2; //****find the correct mid formula
        BinaryNode<String> node = new BinaryNode<String> ();
        node.setData(sortedArray[mid]);

        node.setLeftChild(buildBSTfromSortedArray(sortedArray, left, mid-1));

        node.setRightChild(buildBSTfromSortedArray(sortedArray,mid+1,right));

        return node;
        // TODO-DONE Project 3
        // implement recursively


        //return null;
    }

    public void printBSTinLevelOrder(BinaryNode<String> root)
    {//used online resource https://www.youtube.com/watch?v=WheCF0n2Hl4
        ArrayDeque<BinaryNode<String>> queue = new ArrayDeque<BinaryNode<String> >();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()){
            int nodeCount = queue.size();
            //System.out.println("nodeCount = " + nodeCount);
            level++;
            //System.out.println("nodeCount = " + nodeCount);
            while (nodeCount > 0 ) { //adds and prints nodes in the ArrayDeque
                 BinaryNode<String> node = queue.peek();
                System.out.print(node.getData() + " ");
                queue.poll();
                if (node.getLeftChild() != null) { //adds left child of the just removed node
                     queue.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) { //adds right child of just removed node
                     queue.add(node.getRightChild());
                }
                nodeCount--;//decrements the size of the queue
            }
            System.out.println("<---- Level " + level);
        }
        // TODO-DONE Project 3
        // implement iteratively

    }

    public BinaryNode<String> createTree1()
    {
        // Leaves
        BinaryNode<String> dTree = new BinaryNode<>("D");
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> gTree = new BinaryNode<>("G");

        // Subtrees:
        BinaryNode<String> bTree = new BinaryNode<>("B", dTree, eTree);
        BinaryNode<String> cTree = new BinaryNode<>("C", fTree, gTree);

        System.out.println("\nTree 1:\n");
        System.out.println("     A      ");
        System.out.println("   /   \\   "); // '\\' is the escape character for backslash
        System.out.println("  B     C   ");
        System.out.println(" / \\   / \\");
        System.out.println("D   E  F  G ");
        System.out.println();
        return new BinaryNode<>("A", bTree, cTree);
    } // end createTree1

    public BinaryNode<String> createTree1a()
    {
        // Leaves
        BinaryNode<String> aTree = new BinaryNode<>("A");
        BinaryNode<String> cTree = new BinaryNode<>("C");
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> gTree = new BinaryNode<>("G");

        // Subtrees:
        BinaryNode<String> bTree = new BinaryNode<>("B", aTree, cTree);
        BinaryNode<String> fTree = new BinaryNode<>("F", eTree, gTree);

        System.out.println("\nTree 1a:\n");
        System.out.println("     D      ");
        System.out.println("   /   \\   "); // '\\' is the escape character for backslash
        System.out.println("  B     F   ");
        System.out.println(" / \\   / \\");
        System.out.println("A   C  E  G ");
        System.out.println();
        return new BinaryNode<>("D", bTree, fTree);

    } // end createTree1a

    public BinaryNode<String> createTree2() //  B has no left child
    {
        // Leaves
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> gTree = new BinaryNode<>("G");

        // Subtrees:
        BinaryNode<String> bTree = new BinaryNode<>("B", null, eTree);
        BinaryNode<String> cTree = new BinaryNode<>("C", fTree, gTree);

        System.out.println("\nTree 2:\n");
        System.out.println("     A      ");
        System.out.println("   /   \\   ");
        System.out.println("  B     C   ");
        System.out.println("   \\   / \\");
        System.out.println("    E  F  G ");
        System.out.println();
        return new BinaryNode<>("A", bTree, cTree);
    } // end createTree2

    public BinaryNode<String> createTree2a() //  B has no left child
    {
        // Leaves
        BinaryNode<String> bTree = new BinaryNode<>("B");
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> gTree = new BinaryNode<>("G");

        // Subtrees:
        BinaryNode<String> aTree = new BinaryNode<>("A", null, bTree);
        BinaryNode<String> fTree = new BinaryNode<>("F", eTree, gTree);

        System.out.println("\nTree 2a:\n");
        System.out.println("     C      ");
        System.out.println("   /   \\   ");
        System.out.println("  A     F   ");
        System.out.println("   \\   / \\");
        System.out.println("    B  E  G ");
        System.out.println();
        return new BinaryNode<>("C", aTree, fTree);
    } // end createTree2a

    public BinaryNode<String> createTree3()
    {
        // Leaves
        BinaryNode<String> dTree = new BinaryNode<>("D");
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> gTree = new BinaryNode<>("G");

        // Subtrees:
        BinaryNode<String> fTree = new BinaryNode<>("F", null, gTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", dTree, eTree);
        BinaryNode<String> cTree = new BinaryNode<>("C", fTree, null);

        System.out.println("\nTree 3:\n");
        System.out.println("     A      ");
        System.out.println("   /   \\  ");
        System.out.println("  B     C  ");
        System.out.println(" / \\   /  ");
        System.out.println("D   E  F   ");
        System.out.println("        \\ ");
        System.out.println("         G ");
        System.out.println();
        return new BinaryNode<>("A", bTree, cTree);
    } // end createTree3

    public BinaryNode<String> createTree3a()
    {
        // Leaves
        BinaryNode<String> aTree = new BinaryNode<>("A");
        BinaryNode<String> cTree = new BinaryNode<>("C");
        BinaryNode<String> fTree = new BinaryNode<>("F");

        // Subtrees:
        BinaryNode<String> bTree = new BinaryNode<>("B", aTree, cTree);
        BinaryNode<String> eTree = new BinaryNode<>("E", null, fTree);
        BinaryNode<String> gTree = new BinaryNode<>("G", eTree, null);

        System.out.println("\nTree 3a:\n");
        System.out.println("     D      ");
        System.out.println("   /   \\  ");
        System.out.println("  B     G  ");
        System.out.println(" / \\   /  ");
        System.out.println("A   C  E   ");
        System.out.println("        \\ ");
        System.out.println("         F ");
        System.out.println();
        return new BinaryNode<>("D", bTree, gTree);
    } // end createTree3a

    public BinaryNode<String> createTree4() //  D has H as right child
    {
        // Leaves
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> gTree = new BinaryNode<>("G");
        BinaryNode<String> hTree = new BinaryNode<>("H");

        // Subtrees:
        BinaryNode<String> dTree = new BinaryNode<>("D", null, hTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", dTree, eTree);
        BinaryNode<String> cTree = new BinaryNode<>("C", fTree, gTree);

        System.out.println("\nTree 4:\n");
        System.out.println("     A      ");
        System.out.println("   /   \\   ");
        System.out.println("  B     C   ");
        System.out.println(" / \\   / \\");
        System.out.println("D   E  F  G ");
        System.out.println(" \\         ");
        System.out.println("  H         ");
        System.out.println();
        return new BinaryNode<>("A", bTree, cTree);
    } // end createTree4

    public BinaryNode<String> createTree4a()
    {
        // Leaves
        BinaryNode<String> bTree = new BinaryNode<>("B");
        BinaryNode<String> dTree = new BinaryNode<>("D");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> hTree = new BinaryNode<>("H");

        // Subtrees:
        BinaryNode<String> aTree = new BinaryNode<>("A", null, bTree);
        BinaryNode<String> cTree = new BinaryNode<>("C", aTree, dTree);
        BinaryNode<String> gTree = new BinaryNode<>("G", fTree, hTree);

        System.out.println("\nTree 4a:\n");
        System.out.println("     E      ");
        System.out.println("   /   \\   ");
        System.out.println("  C     G   ");
        System.out.println(" / \\   / \\");
        System.out.println("A   D  F  H ");
        System.out.println(" \\         ");
        System.out.println("  B         ");
        System.out.println();
        return new BinaryNode<>("E", cTree, gTree);
    } // end createTree4a

    public BinaryNode<String> createTree5()
    {
        // Leaves
        BinaryNode<String> dTree = new BinaryNode<>("D");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> gTree = new BinaryNode<>("G");
        BinaryNode<String> hTree = new BinaryNode<>("H");

        // Subtrees:
        BinaryNode<String> eTree = new BinaryNode<>("E");
        eTree.setRightChild(hTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", dTree, eTree);
        BinaryNode<String> cTree = new BinaryNode<>("C", fTree, gTree);

        System.out.println("\nTree 5:\n");
        System.out.println("     A      ");
        System.out.println("   /   \\   ");
        System.out.println("  B     C   ");
        System.out.println(" / \\   / \\");
        System.out.println("D   E  F  G ");
        System.out.println("     \\     ");
        System.out.println("      H     ");
        System.out.println();
        return new BinaryNode<>("A", bTree, cTree);
    } // end createTree5

    public BinaryNode<String> createTree5a()
    {
        // Leaves
        BinaryNode<String> aTree = new BinaryNode<>("A");
        BinaryNode<String> dTree = new BinaryNode<>("D");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> hTree = new BinaryNode<>("H");

        // Subtrees:
        BinaryNode<String> cTree = new BinaryNode<>("C");
        cTree.setRightChild(dTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", aTree, cTree);
        BinaryNode<String> gTree = new BinaryNode<>("G", fTree, hTree);

        System.out.println("\nTree 5a:\n");
        System.out.println("     E      ");
        System.out.println("   /   \\   ");
        System.out.println("  B     G   ");
        System.out.println(" / \\   / \\");
        System.out.println("A   C  F  H ");
        System.out.println("     \\     ");
        System.out.println("      D     ");
        System.out.println();
        return new BinaryNode<>("E", bTree, gTree);
    } // end createTree5a

    public BinaryNode<String> createTree6()
    {
        // Leaves:
        BinaryNode<String> dTree = new BinaryNode<>("D");
        BinaryNode<String> fTree = new BinaryNode<>("F");
        BinaryNode<String> gTree = new BinaryNode<>("G");
        BinaryNode<String> hTree = new BinaryNode<>("H");

        // Subtrees:
        BinaryNode<String> eTree = new BinaryNode<>("E", fTree, gTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", dTree, eTree);
        BinaryNode<String> cTree = new BinaryNode<>("C");
        cTree.setRightChild(hTree);

        System.out.println("\nTree 6:\n");
        System.out.println("     A      ");
        System.out.println("   /   \\   ");
        System.out.println("  B     C   ");
        System.out.println(" / \\     \\");
        System.out.println("D   E     H ");
        System.out.println("   / \\     ");
        System.out.println("  F   G     ");
        System.out.println();
        return new BinaryNode<>("A", bTree, cTree);
    } // end createTree6

    public BinaryNode<String> createTree6a()
    {
        // Leaves:
        BinaryNode<String> aTree = new BinaryNode<>("A");
        BinaryNode<String> cTree = new BinaryNode<>("C");
        BinaryNode<String> eTree = new BinaryNode<>("E");
        BinaryNode<String> hTree = new BinaryNode<>("H");

        // Subtrees:
        BinaryNode<String> dTree = new BinaryNode<>("D", cTree, eTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", aTree, dTree);
        BinaryNode<String> gTree = new BinaryNode<>("G");
        gTree.setRightChild(hTree);

        System.out.println("\nTree 6a:\n");
        System.out.println("     F      ");
        System.out.println("   /   \\   ");
        System.out.println("  B     G   ");
        System.out.println(" / \\     \\");
        System.out.println("A   D     H ");
        System.out.println("   / \\     ");
        System.out.println("  C   E     ");
        System.out.println();
        return new BinaryNode<>("F", bTree, gTree);
    } // end createTree6a

    public BinaryNode<String> createTree7()
    {
        // Leaves:

        BinaryNode<String> aTree = new BinaryNode<>("A");

        // Subtrees:
        BinaryNode<String> eTree = new BinaryNode<>("E", null, aTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", null, eTree);

        System.out.println("\nTree 7:\n");
        System.out.println("      G      ");
        System.out.println("       \\     ");
        System.out.println("        B     ");
        System.out.println("         \\  ");
        System.out.println("          E   ");
        System.out.println("           \\");
        System.out.println("            A ");
        System.out.println();
        return new BinaryNode<>("G", null, bTree);
    } // end createTree7

    public BinaryNode<String> createTree7a()
    {
        // Leaves:

        BinaryNode<String> gTree = new BinaryNode<>("G");

        // Subtrees:
        BinaryNode<String> eTree = new BinaryNode<>("E", null, gTree);
        BinaryNode<String> bTree = new BinaryNode<>("B", null, eTree);

        System.out.println("\nTree 7a:\n");
        System.out.println("      A      ");
        System.out.println("       \\     ");
        System.out.println("        B     ");
        System.out.println("         \\  ");
        System.out.println("          E   ");
        System.out.println("           \\");
        System.out.println("            G ");
        System.out.println();
        return new BinaryNode<>("A", null, bTree);
    } // end createTree7a

    public BinaryNode<String> createTree8a()
    {
        // Leaves
        BinaryNode<String> bTree = new BinaryNode<>("B");
        BinaryNode<String> fTree = new BinaryNode<>("F");

        System.out.println("\nTree 8a:\n");
        System.out.println("     D      ");
        System.out.println("   /   \\   "); // '\\' is the escape character for backslash
        System.out.println("  B     F   ");
        System.out.println();
        return new BinaryNode<>("D", bTree, fTree);
    } // end createTree8a

    public BinaryNode<String> createTree9a()
    {
        // Leaves
        BinaryNode<String> bTree = new BinaryNode<>("B");

        System.out.println("\nTree 9a:\n");
        System.out.println("     D      ");
        System.out.println("   /        ");
        System.out.println("  B         ");
        System.out.println();
        return new BinaryNode<>("D", bTree, null);
    } // end createTree9a

    public BinaryNode<String> createTree10a()
    {
        // Leaves
        BinaryNode<String> bTree = new BinaryNode<>("B");

        System.out.println("\nTree 10a:\n");
        System.out.println("     D      ");
        System.out.println("   /        ");
        System.out.println("  B         ");
        System.out.println();
        return new BinaryNode<>("D", bTree, null);
    } // end createTree10a

    public BinaryNode<String> createTree11a()
    {
        System.out.println("\nTree 11a:\n");
        System.out.println("     D      ");
        System.out.println();
        return new BinaryNode<String>("D", null, null);
    } // end createTree11a


    public static void main(String[] args)
    {
        WorkingWithBinaryTrees tester = new WorkingWithBinaryTrees();
        BinaryNode<String> aTree = tester.createTree1();
        boolean result = tester.isBST(aTree);
        System.out.print("tree1 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree1a();
        result = tester.isBST(aTree);
        System.out.print("tree1a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");


        aTree = tester.createTree2();
        result = tester.isBST(aTree);
        System.out.print("tree2 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree2a();
        result = tester.isBST(aTree);
        System.out.print("tree2a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree3();
        result = tester.isBST(aTree);
        System.out.print("tree3 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree3a();
        result = tester.isBST(aTree);
        System.out.print("tree3a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree4();
        result = tester.isBST(aTree);
        System.out.print("tree4 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree4a();
        result = tester.isBST(aTree);
        System.out.print("tree4a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree5();
        result = tester.isBST(aTree);
        System.out.print("tree5 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree5a();
        result = tester.isBST(aTree);
        System.out.print("tree5a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree6();
        result = tester.isBST(aTree);
        System.out.print("tree6 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree6a();
        result = tester.isBST(aTree);
        System.out.print("tree6a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree7();
        result = tester.isBST(aTree);
        System.out.print("tree7 is BST = " + result);
        if (result)
            System.out.println(" -> INCORRECT");
        else
            System.out.println(" -> CORRECT");

        aTree = tester.createTree7a();
        result = tester.isBST(aTree);
        System.out.print("tree7a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree8a();
        result = tester.isBST(aTree);
        System.out.print("tree8a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree9a();
        result = tester.isBST(aTree);
        System.out.print("tree9a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree10a();
        result = tester.isBST(aTree);
        System.out.print("tree10a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");

        aTree = tester.createTree11a();
        result = tester.isBST(aTree);
        System.out.print("tree11a is BST = " + result);
        if (result)
        {
            System.out.println(" -> CORRECT");
            System.out.println("The smallest element = " + tester.getSmallest(aTree));
            System.out.println("The second largest element = " + tester.getSecondLargest(aTree));
        }
        else
            System.out.println(" -> INCORRECT");


        String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        System.out.println("\nWorking with tree built from the array: " + Arrays.toString(a));
        BinaryNode<String> root = tester.buildBSTfromSortedArray(a, 0, a.length - 1);
        if (root != null)
        {
            result = tester.isBST(root);
            System.out.println("The tree is BST = " + result);
            System.out.println("The tree has " + root.getNumberOfNodes()
                    + " nodes and the height of " + root.getHeight());
            System.out.println("The tree in level order: ");
            tester.printBSTinLevelOrder(root);
        }
        else
            System.out.println("The tree is empty.");
    }
}

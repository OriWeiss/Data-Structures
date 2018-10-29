package Lab13;import java.util.Stack;/** * <p/> * A class that creates an expression tree * from a postfix expression of binary operators * and one-letter operands. * * @author Ori Weiss * @version 4/24/2018 */public class PostfixTree{    private BinaryNode<Character> root;    private final static String OPERATORS = "+-*/";    public PostfixTree()    {        this.root = null;    } // end default constructor    public PostfixTree(String postfix)    {        // TODO-DONE Project 2        // This secondary constructor creates the postfix tree        // stack to put partial expressions on        Stack<BinaryNode<Character>> exprStack = new Stack<>();        for (int i = 0; i < postfix.length(); i++)        // #1 repeat for every character in the postfix        {            //  #2 create subExpression tree of type BinaryNode<Character> with the current character            BinaryNode<Character> current = new BinaryNode(postfix.charAt(i));            current.setData(postfix.charAt(i));//= postfix.charAt(i);            // #3 if the current character is an operator                char currentData = current.getData(); //char holder so i don't need to call getData() every time I want to compare                if(currentData == '+' || currentData == '-' || currentData =='*' || currentData == '/'){                    BinaryNode<Character> operandA = exprStack.pop();                    BinaryNode<Character> operandB = exprStack.pop();                    current.setRightChild(operandA);                    current.setLeftChild(operandB); //****may be the opposite                }                //IM ON 3B            // #3a get the operands from the stack            // #3b build up the subExpression by setting the left and right            //     subtrees to the appropriate operands removed from the stack            // #4 push subExpression on the stack            exprStack.push(current);        }        // #5 At the end of it all the entire expression should be the        //    top expression on the stack, so remove it from the stack        //    and point root to it.        //    Note: that the input postfix string and the postorder output        //          should be the same.        this.root = exprStack.pop();    } // end constructor    public void inOrderTraversal()    {        inOrder(this.root);        System.out.println();    } // end inOrderTraversal    private void inOrder(BinaryNode<Character> node)    {        if (node != null)        {            inOrder(node.getLeftChild());            System.out.print(node.getData() + " ");            inOrder(node.getRightChild());        } // end if    } // end inOrder    public void postOrderTraversal()    {        postOrder(this.root);        System.out.println();        // TODO-DONE Project 2        //System.out.println("You need to implement me - postOrderTraversal()");    } // end postOrderTraversal    private void postOrder(BinaryNode<Character> node)    {        if(node!= null){            BinaryNode<Character> leftNode = node.getLeftChild();            BinaryNode<Character> rightNode = node.getRightChild();            postOrder(leftNode);            postOrder(rightNode);            System.out.print(" " + node.getData() );        }        // TODO-DONE Project 2        //System.out.println("You need to implement me - postOrder()");    } // end postOrder    public static void main(String[] args)    {        String expression = "ab*c+";        System.out.println("The first postfix expression is:\n" + expression);        PostfixTree tree = new PostfixTree(expression);        System.out.println("\nThe inorder traversal is:");        tree.inOrderTraversal();        System.out.println("\nThe postorder traversal is:");        tree.postOrderTraversal();        // . . .        expression = "ab-c*def-+g/+";        System.out.println("\nThe second postfix expression is:\n" + expression);        tree = new PostfixTree(expression);        System.out.println("\nThe inorder traversal is:");        tree.inOrderTraversal();        System.out.println("\nThe postorder traversal is:");        tree.postOrderTraversal();    } // end main} // end PostfixTree		
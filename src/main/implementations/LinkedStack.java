package main.implementations;
import java.util.EmptyStackException;
import main.interfaces.StackInterface;
/**
 * Implementation of a stack that uses a LinkedList
 */
public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;

    public LinkedStack()
    {
        topNode = null;
    }

    /**
     * Adds a new entry onto the top of the stack.
     * @param newEntry An object of type <T> to be added to the stack.
     */   
    public void push(T newEntry)
    {
         Node newNode = new Node(newEntry, topNode);
         topNode = newNode;
    } //end push

    /**
     * Removes the entry on top of the stack.
     * @return The object of type <T> on the top of the stack.
     * @throws EmptyStackException if the stack is empty when called.
     */
    public T pop()
    {
         T top = peek();  // Might throw EmptyStackException
         // Assertion: topNode != null
         topNode = topNode.getNextNode();
         return top;
    } //end pop

    /**
     * Retrives the entry on top of the stack, withouth changing the stack.
     * @return A copy of the object on the top of the stack.
     * @throws EmptyStackException If the stack is empty when called.
     */
    public T peek()
    {
         if (isEmpty())
            throw new EmptyStackException();
         else
            return topNode.getData();
    } //end peek

    /**
     * Tests whether or not the stack is empty.
     * @return True if the stack is empty.
     */
    public boolean isEmpty()
    {
         return topNode == null;
    } //endIsEmpty

    /**
     * Removes all entries from the stack, so that that stack is empty.
     */
    public void clear()
    {
         topNode = null;
    } //end clear

    /**
     * A linked List node.
     */
    private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      /*private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode*/
    
	} // end Node
}
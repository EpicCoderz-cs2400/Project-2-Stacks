package main.implementations;
import java.util.EmptyStackException;

import main.interfaces.StackInterface;

public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;

    public LinkedStack()
    {
        topNode = null;
    }

    public void push(T newEntry)
    {
         Node newNode = new Node(newEntry, topNode);
         topNode = newNode;
    } //end push

    public T pop()
    {
         T top = peek();  // Might throw EmptyStackException
         // Assertion: topNode != null
         topNode = topNode.getNextNode();
         return top;
    } //end pop

    public T peek()
    {
         if (isEmpty())
            throw new EmptyStackException();
         else
            return topNode.getData();
    } //end peek

    public boolean isEmpty()
    {
         return topNode == null;
    } //endIsEmpty

    public void clear()
    {
         topNode = null;
    } //end clear

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
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
}
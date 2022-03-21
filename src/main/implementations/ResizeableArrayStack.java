package main.implementations;
import main.interfaces.StackInterface;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Implementation of a stack that uses a resizing array.
 */

public class ResizeableArrayStack<T> implements StackInterface<T>
{
    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Initializes an empty stack.
     */
    public ResizeableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initializes a stack with specified capacity.
     * @param initialCapacity The specified capcity of the stack.
     */
    public ResizeableArrayStack(int initialCapacity)
    {
         integrityOK = false;
         checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] tempStack = (T[])new Object[initialCapacity];
         stack = tempStack;
         topIndex = -1;
         integrityOK = true;
    }

    /**
     * Adds a new entry onto the top of the stack.
     * @param newEntry An object of type <T> to be added to the stack.
     */   
    public void push(T newEntry)
    {
         checkIntegrity();
         ensureCapacity();
         stack[topIndex + 1] = newEntry;
         topIndex++;
    }

    /**
     * Removes the entry on top of the stack.
     * @return The object of type <T> on the top of the stack.
     * @throws EmptyStackException if the stack is empty when called.
     */
    public T pop()
    {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      } //end if
    } //end pop

    /**
     * Retrives the entry on top of the stack, withouth changing the stack.
     * @return A copy of the object on the top of the stack.
     * @throws EmptyStackException If the stack is empty when called.
     */
    public T peek()
    {
       checkIntegrity();
       if(isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
    } //end peek
    
    /**
     * Tests whether or not the stack is empty.
     * @return True if the stack is empty.
     */
    public boolean isEmpty()
    {
      return  topIndex < 0;
    }

    /**
     * Removes all entries from the stack, so that that stack is empty.
     */
    public void clear()
    {
      checkIntegrity();

         //Remove references to the objects in the stack
         //but do not deallocate the array 
         while(topIndex > -1)
         {
            stack[topIndex] = null;
            topIndex--;
         } //end while
         //Assertion: topIndex is -1
    } //end clear

    /**
     * Checks if the array is at max capacity.
     * @param capacity The intended capcity when initializing an array.
     */   
    private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }

   /**
     * Checks the integrity of an array.
     */  
    private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException ("ArrayBag object is corrupt.");
   }

   /**
    * Checks if an array is full. If it is, the array is doubled in size.
    */
   private void ensureCapacity()
   {
      if (topIndex >= stack.length - 1) // If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      } // end if
   } // end ensureCapacity

}
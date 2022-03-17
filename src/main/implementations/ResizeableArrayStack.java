package main.implementations;
import main.interfaces.StackInterface;
import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizeableArrayStack<T> implements StackInterface<T>
{
    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizeableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }

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

    public void push(T newEntry)
    {
         checkIntegrity();
         ensureCapacity();
         stack[topIndex + 1] = newEntry;
         topIndex++;
    }

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

    public T peek()
    {
       checkIntegrity();
       if(isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
    } //end peek
    
    public boolean isEmpty()
    {
      return  topIndex < 0;
    }

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

    private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }

    private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException ("ArrayBag object is corrupt.");
   }

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
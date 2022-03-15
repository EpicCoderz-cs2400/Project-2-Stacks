package main.interfaces;

/**
 * Stack data structure interface
 * <p>
 * This interface describes the operations and properties of the ADT Stack for a stack of objects.
 * </p>
 * @param <T>: The data type for the entries of a given stack
 * @author 
 */
public interface StackInterface<T>
{
    /**
     * Adds a new entry onto the top of the stack.
     * @param newEntry An object of type <T> to be added to the stack.
     */   
    public void push(T newEntry);

    /**
     * Removes the entry on top of the stack.
     * @return The object of type <T> on the top of the stack.
     * @throws EmptyStackException if the stack is empty when called.
     */
    public T pop();

    /**
     * Retrives the entry on top of the stack, withouth changing the stack.
     * @return A copy of the object on the top of the stack.
     * @throws EmptyStackException If the stack is empty when called.
     */
    public T peek();

    /**
     * Tests whether or not the stack is empty.
     * @return True if the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Removes all entries from the stack, so that that stack is empty.
     */
    public void clear();
}
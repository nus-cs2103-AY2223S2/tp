package seedu.dengue.storage.temporary;

/**
 * An interface for a temporary memory stack for the undo function.
 */
public interface StackWithStorage<T> {

    /**
     * Pops the top layer of the stack then reveals the top layer.
     * @return the popped element.
     */
    T temporaryPop();

    /**
     * For one element previously popped, push the latest one back into the stack.
     */
    void pushOneFromTemporaryPop();

    /**
     * For all elements previously popped, push them back into the stack.
     */
    void pushAllFromTemporaryPop();

    /**
     * Return the current top element of the stack.
     * @return The top element of the stack.
     */
    T peek();

    /**
     * Pushes an element to the top of the stack.
     * @param t item to be pushed.
     * @return The item {@param t}.
     */
    T push(T t);

    /**
     * Clears all temporarily-stored popped elements.
     */
    void clearStorage();

    /**
     * Remove old values at the bottom of the stack.
     */
    T removeOld();


}

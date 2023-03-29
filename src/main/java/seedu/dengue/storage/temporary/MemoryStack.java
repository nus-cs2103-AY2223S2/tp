package seedu.dengue.storage.temporary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * A specialised Stack for the temporary memory storage for DengueHotspotTracker's undo command.
 * This stack has a fixed size of 10.
 */
public class MemoryStack<T> implements StackWithStorage<T> {

    protected static final int MAX_SIZE = 10;
    private final Deque<T> memory;
    private final Stack<T> storage;

    /**
     * Creates a specialised stack that stores {@Code T} objects.
     * @param latest A {@link T} object.
     */
    protected MemoryStack(T latest) {
        this.storage = new Stack<>();
        this.memory = new ArrayDeque<T>(MAX_SIZE + 1);
        this.memory.push(latest);
    }

    /**
     * Creates an empty {@code MemoryStack}.
     */
    public MemoryStack() {
        this.storage = new Stack<>();
        this.memory = new ArrayDeque<T>(MAX_SIZE + 1);
    }

    @Override
    public T temporaryPop() throws NoSuchElementException {
        T latest = this.memory.pop();
        this.storage.push(latest);
        return latest;
    }

    public Deque<T> getMemory() {
        return this.memory;
    }

    public Stack<T> getStorage() {
        return this.storage;
    }

    @Override
    public void pushOneFromTemporaryPop() throws EmptyStackException {
        this.memory.push(this.storage.pop());
    }
    @Override
    public void pushAllFromTemporaryPop() {
        while (!this.storage.isEmpty()) {
            this.memory.push(this.storage.pop());
        }
    }

    @Override
    public void clearStorage() {
        this.storage.clear();
    }

    @Override
    public T peek() {
        return this.memory.peek();
    }

    @Override
    public T push(T latest) {
        this.memory.push(latest);
        return latest;
    }

    @Override
    public T removeOld() throws NoSuchElementException {
        return this.memory.pollLast();
    }

    /**
     * Checks if the memory stack is full.
     * @return A boolean value.
     */
    public boolean isFull() {
        return this.memory.size() > MAX_SIZE;
    }

}

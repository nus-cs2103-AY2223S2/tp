package seedu.dengue.storage.temporary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

import seedu.dengue.model.ReadOnlyDengueHotspotTracker;

/**
 * A specialised Stack for the temporary memory storage for DengueHotspotTracker's undo command.
 * This stack has a fixed size of 10.
 */
public class MemoryStack implements StackWithStorage<ReadOnlyDengueHotspotTracker> {

    protected static final int MAX_SIZE = 10;
    private final Deque<ReadOnlyDengueHotspotTracker> memory;
    private final Stack<ReadOnlyDengueHotspotTracker> storage;

    /**
     * Creates a specialised stack that stores {@Code ReadOnlyDengueHotspotTracker} objects.
     * @param latest A {@link ReadOnlyDengueHotspotTracker} object.
     */
    protected MemoryStack(ReadOnlyDengueHotspotTracker latest) {
        this.storage = new Stack<>();
        this.memory = new ArrayDeque<ReadOnlyDengueHotspotTracker>(MAX_SIZE + 1);
        this.memory.push(latest);
    }

    protected MemoryStack() {
        this.storage = new Stack<>();
        this.memory = new ArrayDeque<ReadOnlyDengueHotspotTracker>(MAX_SIZE + 1);
    }

    @Override
    public ReadOnlyDengueHotspotTracker temporaryPop() throws NoSuchElementException {
        ReadOnlyDengueHotspotTracker latest = this.memory.pop();
        this.storage.push(latest);
        return latest;
    }

    public Deque<ReadOnlyDengueHotspotTracker> getMemory() {
        return this.memory;
    }

    public Stack<ReadOnlyDengueHotspotTracker> getStorage() {
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
    public ReadOnlyDengueHotspotTracker peek() {
        return this.memory.peek();
    }

    @Override
    public ReadOnlyDengueHotspotTracker push(ReadOnlyDengueHotspotTracker latest) {
        this.memory.push(latest);
        return latest;
    }

    @Override
    public ReadOnlyDengueHotspotTracker removeOld() throws NoSuchElementException {
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

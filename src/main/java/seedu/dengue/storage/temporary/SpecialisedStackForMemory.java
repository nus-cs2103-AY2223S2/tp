package seedu.dengue.storage.temporary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * A specialised Stack for the temporary redoHistory undoHistory for DengueHotspotTracker's undo command.
 * This stack has a fixed size of 10.
 */
public class SpecialisedStackForMemory<T> implements StackWithStorage<T> {

    public static final int MAX_SIZE = 10;
    private final Deque<T> redoHistory;
    private final Stack<T> undoHistory;

    /**
     * Creates a specialised stack that stores {@code T} objects.
     * @param latest A {@link T} object.
     */
    protected SpecialisedStackForMemory(T latest) {
        this.undoHistory = new Stack<>();
        this.redoHistory = new ArrayDeque<>(MAX_SIZE + 1);
        this.redoHistory.push(latest);
    }

    /**
     * Creates an empty {@code SpecialisedStackForMemory}.
     */
    public SpecialisedStackForMemory() {
        this.undoHistory = new Stack<>();
        this.redoHistory = new ArrayDeque<>(MAX_SIZE + 1);
    }

    /**
     * Pops an item from the {@code SpecialisedStackForMemory}.
     * Stores it in another location at {@code this.undoHistory}.
     * @return The popped item.
     * @throws NoSuchElementException When the {@code SpecialisedStackForMemory} is empty.
     */
    @Override
    public T temporaryPop() throws NoSuchElementException {
        T latest = this.redoHistory.pop();
        this.undoHistory.push(latest);
        return latest;
    }

    public Deque<T> getRedoHistory() {
        return this.redoHistory;
    }

    public Stack<T> getUndoHistory() {
        return this.undoHistory;
    }

    @Override
    public void pushOneFromTemporaryPop() throws EmptyStackException {
        this.redoHistory.push(this.undoHistory.pop());
    }

    @Override
    public void pushAllFromTemporaryPop() {
        while (!this.undoHistory.isEmpty()) {
            this.redoHistory.push(this.undoHistory.pop());
        }
    }

    @Override
    public void clearStorage() {
        this.undoHistory.clear();
    }

    @Override
    public T peek() {
        return this.redoHistory.peek();
    }

    @Override
    public T push(T latest) {
        this.redoHistory.push(latest);
        return latest;
    }

    @Override
    public T removeOld() throws NoSuchElementException {
        return this.redoHistory.pollLast();
    }

    /**
     * Checks if the redoHistory stack is full.
     * @return A boolean value.
     */
    public boolean isFull() {
        return this.redoHistory.size() > MAX_SIZE;
    }

}

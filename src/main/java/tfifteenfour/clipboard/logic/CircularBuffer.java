package tfifteenfour.clipboard.logic;

import java.util.ArrayList;

/**
 * A circular buffer implementation that supports adding, removing, and peeking
 * at elements in a circular manner.
 *
 * @param <T> the type of elements stored in the buffer
 */
public class CircularBuffer<T> {
    private ArrayList<T> buffer;
    private int maxSize;
    private int head;
    private int tail;

    /**
     * Constructs a new circular buffer with the specified size.
     *
     * @param size the size of the buffer
     */
    public CircularBuffer(int maxSize) {
        this.buffer = new ArrayList<T>(maxSize);
        this.maxSize = maxSize;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * Adds an element to the buffer. If the buffer is full, the oldest element
     * is overwritten.
     *
     * @param item the item to add
     */
    public void add(T item) {
        if (buffer.size() < maxSize) {
            buffer.add(tail, item);
            tail = (tail + 1) % maxSize;
        } else {
            buffer.set(head, item);
            head = (head + 1) % maxSize;
            tail = (tail + 1) % maxSize;
        }
    }

    /**
     * Removes and returns the oldest element from the buffer.
     *
     * @return the oldest element, or null if the buffer is empty
     */
    public T remove() {
        if (buffer.isEmpty()) {
            return null;
        }
        T item = buffer.get(head);
        head = (head + 1) % maxSize;
        return item;
    }

    /**
     * Removes and returns the most recently added element from the buffer.
     *
     * @return the most recently added element, or null if the buffer is empty
     */
    public T removeLast() {
        if (buffer.isEmpty()) {
            return null;
        }
        tail = (tail - 1 + maxSize) % maxSize;
        return buffer.remove(tail);
    }

    /**
     * Returns the most recently added element in the buffer, without removing it.
     *
     * @return the most recently added element, or null if the buffer is empty
     */
    public T peekLast() {
        if (buffer.isEmpty()) {
            return null;
        }
        return buffer.get((tail - 1 + maxSize) % maxSize);
    }

    /**
     * Returns the oldest element in the buffer, without removing it.
     *
     * @return the oldest element, or null if the buffer is empty
     */
    public T peek() {
        if (buffer.isEmpty()) {
            return null;
        }
        return buffer.get(head);
    }

    /**
     * Returns true if the buffer is empty, false otherwise.
     *
     * @return true if the buffer is empty, false otherwise
     */
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    /**
     * Returns the maximum size of the buffer.
     *
     * @return the maximum number of elements in the buffer
     */
    public int size() {
        return buffer.size();
    }
}

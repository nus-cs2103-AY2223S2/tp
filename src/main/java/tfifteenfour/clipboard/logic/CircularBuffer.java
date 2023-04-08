package tfifteenfour.clipboard.logic;

/**
 * A circular buffer implementation that supports adding, removing, and peeking
 * at elements in a circular manner.
 *
 * @param <T> the type of elements stored in the buffer
 */
public class CircularBuffer<T> {
    private T[] buffer;
    private int maxSize;
    private int head;
    private int tail;
    private int size;

    /**
     * Constructs a new circular buffer with the specified size.
     *
     * @param maxSize the size of the buffer
     */
    @SuppressWarnings("unchecked")
    public CircularBuffer(int maxSize) {
        this.buffer = (T[]) new Object[maxSize];
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
        if (buffer.length < maxSize) {
            buffer[tail] = item;
            tail = (tail + 1) % maxSize;
        } else {
            buffer[head] = item;
            head = (head + 1) % maxSize;
            tail = (tail + 1) % maxSize;
        }
        if (this.size < maxSize) {
            this.size += 1;
        }
    }

    /**
     * Removes and returns the most recently added element from the buffer.
     *
     * @return the most recently added element, or null if the buffer is empty
     */
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        tail = (tail - 1 + maxSize) % maxSize;
        T removed = buffer[tail];
        buffer[tail] = null;
        this.size -= 1;
        return removed;
    }

    /**
     * Returns the most recently added element in the buffer, without removing it.
     *
     * @return the most recently added element, or null if the buffer is empty
     */
    public T peekLast() {
        if (this.size == 0) {
            return null;
        }
        return buffer[((tail - 1 + maxSize) % maxSize)];
    }

    /**
     * Returns the oldest element in the buffer, without removing it.
     *
     * @return the oldest element, or null if the buffer is empty
     */
    public T peek() {
        if (this.size == 0) {
            return null;
        }
        return buffer[head];
    }

    /**
     * Returns true if the buffer is empty, false otherwise.
     *
     * @return true if the buffer is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the maximum size of the buffer.
     *
     * @return the maximum number of elements in the buffer
     */
    public int size() {
        return this.size;
    }
}

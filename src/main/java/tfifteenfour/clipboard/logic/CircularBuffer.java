package tfifteenfour.clipboard.logic;

import java.util.ArrayList;

public class CircularBuffer<T> {
    private ArrayList<T> buffer;
    private int size;
    private int head;
    private int tail;
    private int numElements;

    public CircularBuffer(int size) {
        this.buffer = new ArrayList<T>(size);
        this.size = size;
        this.head = 0;
        this.tail = 0;
    }

    public void add(T item) {
        if (buffer.size() < size) {
            buffer.add(tail, item);
            tail = (tail + 1) % size;
            numElements++;
        } else {
            buffer.set(head, item);
            head = (head + 1) % size;
            tail = (tail + 1) % size;
        }
    }

    public T remove() {
        if (buffer.isEmpty()) {
            return null;
        }
        T item = buffer.get(head);
        head = (head + 1) % size;
        numElements--;
        return item;
    }

	public T removeLast() {
		if (buffer.isEmpty()) {
			return null;
		}
		tail = (tail - 1 + size) % size;
        numElements--;
		return buffer.remove(tail);
	}

	public T peekLast() {
		if (buffer.isEmpty()) {
			return null;
		}
		return buffer.get((tail - 1 + size) % size);
	}


    public T peek() {
        if (buffer.isEmpty()) {
            return null;
        }
        return buffer.get(head);
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    public int size() {
        return buffer.size();
    }
}


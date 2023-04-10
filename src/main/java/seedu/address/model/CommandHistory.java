package seedu.address.model;

import java.util.Stack;

/**
 * Stores the history of commandText executed.
 */
public class CommandHistory {
    private Stack<String> previousCommandStack = new Stack<>();
    private Stack<String> nextCommandStack = new Stack<>();

    public void pushToPrevious(String command) {
        previousCommandStack.push(command);
    }

    public void pushToNext(String command) {
        nextCommandStack.push(command);
    }

    public String popFromPrevious() {
        return previousCommandStack.pop();
    }

    public String popFromNext() {
        return nextCommandStack.pop();
    }

    /**
     * Returns the previous command in the stack without removing it.
     */
    public String peekPrevious() {
        if (previousCommandStack.isEmpty()) {
            return "";
        }
        return previousCommandStack.peek();
    }

    /**
     * Returns the next command in the stack without removing it.
     */
    public String peekNext() {
        if (nextCommandStack.isEmpty()) {
            return "";
        }
        return nextCommandStack.peek();
    }

    public boolean isPreviousEmpty() {
        return previousCommandStack.isEmpty();
    }

    public boolean isNextEmpty() {
        return nextCommandStack.isEmpty();
    }

    public void clearPrevious() {
        previousCommandStack.clear();
    }

    public void clearNext() {
        nextCommandStack.clear();
    }

    /**
     * Remove and pushes all the commands in the nextCommandStack to the
     * previousCommandStack.
     */
    public void pushAllNextToPrevious() {
        while (!nextCommandStack.isEmpty()) {
            previousCommandStack.push(nextCommandStack.pop());
        }
    }
}

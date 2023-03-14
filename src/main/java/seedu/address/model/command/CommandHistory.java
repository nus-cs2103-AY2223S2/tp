package seedu.address.model.command;

import java.util.LinkedList;

import seedu.address.model.command.exceptions.OutOfBoundsCommandHistoryException;

/**
 * Represents a CommandHistory that consists of all the previously executed commands,
 * regardless of their validity.
 */
public class CommandHistory {
    /**
     * The maximum number of commands kept in memory.
     */
    public static final int MAXIMUM = 30;

    private static final LinkedList<String> commands = new LinkedList<>();
    private static int index = -1;

    /**
     * Adds a command to be the last one in the {@code CommandHistory}.
     * @param input The command input.
     */
    public static void push(String input) {
        if (commands.size() >= MAXIMUM) {
            commands.removeFirst();
        }

        commands.add(input);
        index = commands.size() - 1;
    }

    /**
     * Sets the pointer such that {@code CommandHistory.prev()} will return the last command.
     */
    public static void setLast() {
        index = commands.size();
    }

    private static boolean hasPrev() {
        return !commands.isEmpty() && index > 0;
    }

    private static boolean hasNext() {
        return index < commands.size() - 1;
    }

    /**
     * Retrieves the previous command in the {@code CommandHistory}.
     *
     * @return The previous command input.
     * @throws OutOfBoundsCommandHistoryException If there are no commands before the current one.
     */
    public static String prev() throws OutOfBoundsCommandHistoryException {
        if (hasPrev()) {
            index--;
            return commands.get(index);
        }

        throw new OutOfBoundsCommandHistoryException();
    }

    /**
     * Retrieves the next command in the {@code CommandHistory}.
     *
     * @return The next command input.
     * @throws OutOfBoundsCommandHistoryException If there are no commands after the current one.
     */
    public static String next() throws OutOfBoundsCommandHistoryException {
        if (hasNext()) {
            index++;
            return commands.get(index);
        }

        throw new OutOfBoundsCommandHistoryException();
    }
}

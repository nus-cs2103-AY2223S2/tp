package seedu.address.model.command;

import java.util.LinkedList;

import seedu.address.model.command.exceptions.OutOfBoundsCommandHistoryException;

/**
 *
 */
public class CommandHistory {
    /**
     * The maximum number of commands kept in memory.
     */
    public static final int MAXIMUM = 30;

    private static final LinkedList<String> commands = new LinkedList<>();
    private static int index = -1;

    public static void push(String input) {
        if (commands.size() >= MAXIMUM) {
            commands.removeFirst();
        }

        commands.add(input);
        index = commands.size() - 1;
    }

    public static void setLast() {
        index = commands.size();
    }

    public static String prev() throws OutOfBoundsCommandHistoryException {
        if (!commands.isEmpty() && index > 0) {
            index--;
            return commands.get(index);
        }

        throw new OutOfBoundsCommandHistoryException();
    }

    public static String next() throws OutOfBoundsCommandHistoryException {
        if (index < commands.size() - 1) {
            index++;
            return commands.get(index);
        }

        throw new OutOfBoundsCommandHistoryException();
    }
}

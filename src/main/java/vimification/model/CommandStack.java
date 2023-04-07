package vimification.model;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import vimification.internal.command.logic.UndoableLogicCommand;

/**
 * Responsible for keeping track of previous {@link UndoableLogicCommand} executed.
 * <p>
 * At most, 20 commands can be stored at a single time. Afterward, the oldest command will be
 * discarded, each time a new command is pushed into the stack.
 */
public class CommandStack {

    private static final int MAX_SIZE = 20;

    private Deque<UndoableLogicCommand> commands;

    /**
     * Creates a new, empty {@code CommandStack}.
     */
    public CommandStack() {
        this.commands = new ArrayDeque<>();
    }

    /**
     * Creates a new {@code CommandStack} with the given commands.
     *
     * @param commands the given commands to be pushed into the stack
     */
    public CommandStack(Collection<? extends UndoableLogicCommand> commands) {
        this.commands = new ArrayDeque<>(commands);
        ensureSize();
    }

    private void ensureSize() {
        while (commands.size() > MAX_SIZE) {
            commands.pollFirst();
        }
    }

    /**
     * Pushes a new command to the top of the stack.
     *
     * @param cmd the command to be pushed into the stack
     */
    public void push(UndoableLogicCommand cmd) {
        commands.offerLast(cmd);
        ensureSize();
    }

    /**
     * Pops a command from the top of the stack. Null will be returned if there is no command in the
     * stack.
     *
     * @return the command that is on the top of the stack
     */
    public UndoableLogicCommand pop() {
        return commands.pollLast();
    }

    /**
     * Returns the current size of the stack.
     *
     * @return the current size of the stack
     */
    public int size() {
        return commands.size();
    }

    /**
     * Checks whether the stack is empty or not.
     *
     * @return true if the stack is empty, otherwise false
     */
    public boolean isEmpty() {
        return commands.isEmpty();
    }

    @Override
    public String toString() {
        return "CommandStack [commands=" + commands + "]";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommandStack)) {
            return false;
        }
        CommandStack otherStack = (CommandStack) other;
        return commands.equals(otherStack.commands);
    }
}

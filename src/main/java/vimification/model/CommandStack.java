package vimification.model;

import java.util.Deque;
import java.util.stream.Stream;

import vimification.internal.command.logic.UndoableLogicCommand;

import java.util.ArrayDeque;
import java.util.Collection;

public class CommandStack {

    private static int MAX_SIZE = 20;

    private Deque<UndoableLogicCommand> commands;

    public CommandStack() {
        this.commands = new ArrayDeque<>();
    }

    public CommandStack(Collection<UndoableLogicCommand> commands) {
        this.commands = new ArrayDeque<>(commands);
        ensureCorrectSize();
    }

    private void ensureCorrectSize() {
        while (commands.size() > MAX_SIZE) {
            commands.pollFirst();
        }
    }

    public void push(UndoableLogicCommand cmd) {
        commands.offerLast(cmd);
        ensureCorrectSize();
    }

    public UndoableLogicCommand pop() {
        return commands.pollLast();
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    @Override
    public String toString() {
        return "CommandDeque: " + commands;
    }

    public Stream<UndoableLogicCommand> stream() {
        return commands.stream();
    }
}

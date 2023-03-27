package vimification.model;

import java.util.Deque;

import vimification.internal.command.logic.UndoableLogicCommand;

import java.util.ArrayDeque;

public class CommandStack {

    private static int MAX_SIZE = 20;

    private Deque<UndoableLogicCommand> commands;

    public CommandStack() {
        this.commands = new ArrayDeque<>();
    }

    public CommandStack(Deque<UndoableLogicCommand> commands) {
        while (commands.size() > MAX_SIZE) {
            commands.pollFirst();
        }
        this.commands = new ArrayDeque<>(commands);
    }

    public void push(UndoableLogicCommand cmd) {
        commands.offerLast(cmd);
        if (commands.size() > MAX_SIZE) {
            commands.pollFirst();
        }
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
}

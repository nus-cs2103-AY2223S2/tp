package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<String> commandList;
    private List<Integer> modifyHistoryCommandList;
    private int currentStatePointer;
    public static final String UNDO_HISTORY_FAILURE = "There has not been a recent modification to TeachMeSenpai to undo!";
    public static final String REDO_HISTORY_FAILURE = "TeachMeSenpai is already in the latest version! There is nothing to redo";

    public CommandHistory() {
        commandList = new ArrayList<>();
        modifyHistoryCommandList = new ArrayList<>();
        modifyHistoryCommandList.add(-1);
        currentStatePointer = 0;
    }

    public void updateCommandHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);
    }

    public void updateAsModifyingHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        assert (!commandList.isEmpty());
        int outdatedCommandIndex = currentStatePointer + 1;
        modifyHistoryCommandList.subList(outdatedCommandIndex, modifyHistoryCommandList.size()).clear();
        modifyHistoryCommandList.add(commandList.size() - 1);
        currentStatePointer++;
    }

    public String getLastExecutedCommand() throws CommandException {
        if (currentStatePointer <= 0) {
            throw new CommandException(UNDO_HISTORY_FAILURE);
        }
        currentStatePointer--;
        int index = modifyHistoryCommandList.get(currentStatePointer + 1);
        return commandList.get(index);
    }

    public String getLastModifyingCommand() {
        int index = modifyHistoryCommandList.get(currentStatePointer);
        return commandList.get(index);
    }

    public String getLatestModifyingCommand() throws CommandException {
        if (currentStatePointer <= modifyHistoryCommandList.size()) {
            throw new CommandException(REDO_HISTORY_FAILURE);
        }
        currentStatePointer++;
        int index = modifyHistoryCommandList.get(currentStatePointer);
        return commandList.get(index);
    }

    public boolean checkModifyingCommand(Command command) {
        return command.checkModifiable();
    }

}

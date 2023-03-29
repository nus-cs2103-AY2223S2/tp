package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHistory {
    private List<String> commandList;
    private List<Integer> modifyingCommandHistoryIndexes;
    private int currentStatePointer;

    public CommandHistory() {
        commandList = new ArrayList<>();
        modifyingCommandHistoryIndexes = new ArrayList<>();
        modifyingCommandHistoryIndexes.add(-1);
        currentStatePointer = 0;
    }

    public void updateCommandHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);
    }

    public void updateAsModifyingHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        assert (!commandList.isEmpty());
        modifyingCommandHistoryIndexes.add(commandList.size() - 1);
        commandList.add(lastExecutedCommand);
        currentStatePointer++;
    }

    public String getLastExecutedCommand() {
        return commandList.get(currentStatePointer);
    }

    public String getLastModifyingCommand() {
        int index = modifyingCommandHistoryIndexes.get(currentStatePointer);
        currentStatePointer--;
        return commandList.get(index);
    }

    public String getLatestModifyingCommand() {
        currentStatePointer++;
        int index = modifyingCommandHistoryIndexes.get(currentStatePointer);
        return commandList.get(index);
    }

    public boolean checkModifyingCommand(Command command) {
        return command.checkModifiable();
    }

}

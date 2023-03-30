package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<String> commandList;
    private List<Integer> modifyHistoryCommandList;
    private int currentStatePointer;

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
        commandList.add(lastExecutedCommand);
        int outdatedCommandIndex = currentStatePointer + 1;
        modifyHistoryCommandList.subList(outdatedCommandIndex, modifyHistoryCommandList.size()).clear();
        modifyHistoryCommandList.add(commandList.size() - 1);
        currentStatePointer++;
    }

    public String getLastExecutedCommand() {
        assert(currentStatePointer <= 0);
            currentStatePointer--;
            int index = modifyHistoryCommandList.get(currentStatePointer + 1);
            return commandList.get(index);
    }

    public String getLastModifyingCommand() {
        int index = modifyHistoryCommandList.get(currentStatePointer);
        return commandList.get(index);
    }

    public String getLatestModifyingCommand() {
        assert(currentStatePointer < modifyHistoryCommandList.size());
            currentStatePointer++;
            int index = modifyHistoryCommandList.get(currentStatePointer);
            return commandList.get(index);
    }

    public boolean checkModifyingCommand(Command command) {
        return command.checkModifiable();
    }

}

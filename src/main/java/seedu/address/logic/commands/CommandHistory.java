package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<String> commandList;
    private List<Integer> modifyHistoryCommandList;
    private int currentVersionPointer;

    public CommandHistory() {
        commandList = new ArrayList<>();
        modifyHistoryCommandList = new ArrayList<>();
        modifyHistoryCommandList.add(-1);
        currentVersionPointer = 0;
    }

    public void updateCommandHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);
    }

    public void updateAsModifyingHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);
        int outdatedCommandIndex = currentVersionPointer + 1;
        for (int i = modifyHistoryCommandList.size() - 1; i > outdatedCommandIndex; i--) {
            modifyHistoryCommandList.remove(i);
        }
        modifyHistoryCommandList.add(commandList.size() - 1);
        currentVersionPointer++;
    }

    public String getLastExecutedCommand() {
        assert(currentVersionPointer <= 0);
        currentVersionPointer--;
        int index = modifyHistoryCommandList.get(currentVersionPointer + 1);
        return commandList.get(index);
    }

    public String getLastModifyingCommand() {
        int index = modifyHistoryCommandList.get(currentVersionPointer);
        return commandList.get(index);
    }

    public String getLatestModifyingCommand() {
        assert(currentVersionPointer < modifyHistoryCommandList.size());
        currentVersionPointer++;
        int index = modifyHistoryCommandList.get(currentVersionPointer);
        return commandList.get(index);
    }

    public boolean checkModifyingCommand(Command command) {
        return command.checkModifiable();
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

//@@author wendy0107-reused
//Reused from https://github.com/AY2223S1-CS2103T-W17-4
// with modifications such as renaming and different implementation.

/**
 * Contains commands related to tracking the command history of TeachMeSenpai.
 */
public class CommandHistory {
    private List<String> commandList;
    private List<Integer> modifyHistoryCommandList;
    private int currentVersionPointer;

    /**
     * Constructs a {@code CommandHistory}.
     */
    public CommandHistory() {
        commandList = new ArrayList<>();
        modifyHistoryCommandList = new ArrayList<>();
        modifyHistoryCommandList.add(-1);
        commandList.add("default command");
        currentVersionPointer = 0;
    }

    /**
     * Updates the command history with commands that do not modify the model.
     * @param lastExecutedCommand The name of the command to be updated to command history.
     */
    public void updateCommandHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);
    }

    /**
     * Updates the command history with commands that modify the model.
     * @param lastExecutedCommand The name of the command to be updated to the command history.
     */
    public void updateAsModifyingHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);
        int outdatedCommandIndex = currentVersionPointer + 1;
//        for (int i = modifyHistoryCommandList.size() - 1; i > outdatedCommandIndex; i--) {
//            modifyHistoryCommandList.remove(i);
//        }
//        modifyHistoryCommandList.add(commandList.size() - 1);
//        commandList.subList(outdatedCommandIndex, commandList.size() - 1).clear();
        commandList.subList(outdatedCommandIndex, commandList.size());
        currentVersionPointer++;
    }

    /**
     * Returns the command name of the last executed command that modified AddressBook.
     * @return Command name as a string.
     */
    public String getLastExecutedCommand() {
//        assert currentVersionPointer <= 0;
        String lastExecCommand = commandList.get(currentVersionPointer);
        currentVersionPointer--;
        return lastExecCommand;
//        currentVersionPointer--;
//        int index = modifyHistoryCommandList.get(currentVersionPointer + 1);
//        return commandList.get(index);
    }

//    public String getLatestModifyingCommand() {
//        assert currentVersionPointer < modifyHistoryCommandList.size();
//        currentVersionPointer++;
//        int index = modifyHistoryCommandList.get(currentVersionPointer);
//        return commandList.get(index);
//    }

    /**
     * Returns the command name of the last command undone.
     * @return Command name as a String.
     */
    public String getLatestModifyingCommand() {
        currentVersionPointer++;
        return commandList.get(currentVersionPointer);
    }
    //@@author

//    public String getLastModifyingCommand() {
//        int index = modifyHistoryCommandList.get(currentVersionPointer);
//        return commandList.get(index);
//    }

}

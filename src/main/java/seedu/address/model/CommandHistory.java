package seedu.address.model;

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
     * Updates the command history with commands that modify the model.
     * @param lastExecutedCommand The name of the command to be updated to the command history.
     */
    public void updateAsModifyingHistory(String lastExecutedCommand) {
        requireNonNull(lastExecutedCommand);
        commandList.add(lastExecutedCommand);

        int outdatedCommandIndex = currentVersionPointer + 1;
        commandList.subList(outdatedCommandIndex, commandList.size());
        currentVersionPointer++;
    }

    /**
     * Returns the command name of the last executed command that modified AddressBook.
     * @return Command name as a string.
     */
    public String getLastExecutedCommand() {
        String lastExecCommand = commandList.get(currentVersionPointer);
        currentVersionPointer--;
        return lastExecCommand;
    }

    /**
     * Returns the command name of the last command undone.
     * @return Command name as a String.
     */
    public String getLatestModifyingCommand() {
        currentVersionPointer++;
        return commandList.get(currentVersionPointer);
    }
    //@@author
}

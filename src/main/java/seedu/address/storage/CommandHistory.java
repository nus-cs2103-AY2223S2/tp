package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.FileUtil.createIfMissing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Records all the commands that the user has ever typed.
 */
public class CommandHistory {
    private static final Logger logger = LogsCenter.getLogger(CommandHistory.class);
    private final Path commandHistoryFilePath;
    private final File file;
    private final CommandHistoryArrayList commandHistoryArrayList;

    /**
     * Instantiates a new {@code CommandHistory} object.
     */
    public CommandHistory(Path commandHistoryFilePath) {
        requireNonNull(commandHistoryFilePath);
        this.commandHistoryFilePath = commandHistoryFilePath;

        try {
            createIfMissing(commandHistoryFilePath);
            file = new File(commandHistoryFilePath.toString());
        } catch (IOException e) {
            logger.warning("Failed to create " + commandHistoryFilePath);
            throw new RuntimeException(e);
        }

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String commandString = sc.nextLine();
                arrayList.add(commandString);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // should never happen since we've already checked for the existence of the file
            logger.warning(commandHistoryFilePath + " does not exist");
        }

        commandHistoryArrayList = new CommandHistoryArrayList(arrayList);
    }

    public String getPreviousUserInput(String currentUserInput) {
        return commandHistoryArrayList.getPreviousUserInput(currentUserInput);
    }

    public String getNextUserInput() {
        return commandHistoryArrayList.getNextUserInput();
    }

    /**
     * Commits the {@code currentUserInput} to {@code CommandHistory}. Also updates
     * the {@code command_history.txt} file on disk.
     *
     * @param currentUserInput The user's input in the {@code CommandBox}.
     */
    public void commitUserInput(String currentUserInput) {
        commandHistoryArrayList.commitUserInput(currentUserInput);
        updateCommandHistoryFile();
    }

    private void updateCommandHistoryFile() {
        try {
            String commandHistoryString = String.join("\n", commandHistoryArrayList.getInternalArrayList());
            FileWriter fw = new FileWriter(file);
            fw.write(commandHistoryString);
            fw.close();
            logger.info("Updated " + commandHistoryFilePath + " successfully");
        } catch (IOException e) {
            logger.warning("Failed to write to " + commandHistoryFilePath);
            throw new RuntimeException(e);
        }
    }
}

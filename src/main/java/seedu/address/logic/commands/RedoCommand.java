package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.StateHistory;

/**
 * Redoes a number of the recently undone {@code Command}s.
 */
public class RedoCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("redo", "r", "heal"));

    private static final Path p = Paths.get("data", "redoCommand.txt");

    public static final String MESSAGE_USAGE = COMMAND_WORDS + ": Redoes the last undone command,"
            + "or a number of the most recently undone commands.\n"
            + "Parameters: [NUMBER_OF_COMMANDS]...\n"
            + "Example: " + COMMAND_WORDS + " 5";

    public static final String MESSAGE_SUCCESS = "Redone %1$d / %2$d commands";

    private final int numCommands;
    private StateHistory history = null;

    /**
     * Creates an RedoCommand to redo a given number of undone Commands.
     *
     * @param numCommands Number of commands to redo
     */
    public RedoCommand(int numCommands) {
        this.numCommands = numCommands;
    }

    @Override
    public void setHistory(StateHistory history) {
        this.history = history;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(history);
        int redoneCommands = history.redo(numCommands);
        Model redoneModel = history.presentModel();
        model.setAddressBook(redoneModel.getAddressBook());
        model.updateFilteredPersonList(redoneModel.getPredicate());
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, redoneCommands, numCommands), false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand // instanceof handles nulls
                && numCommands == ((RedoCommand) other).numCommands); // state check
    }
    public static void saveWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }

        try {
            FileOutputStream fos = new FileOutputStream(p.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(COMMAND_WORDS);
            oos.close();
        } catch (IOException ignored) {}
    }

    public static void loadWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }
        try {
            FileInputStream fis = new FileInputStream(p.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            COMMAND_WORDS = (List<String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ignored) {}
    }
}

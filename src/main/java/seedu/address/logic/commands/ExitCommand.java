package seedu.address.logic.commands;

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

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("exit", "ex"));

    private static final Path p = Paths.get("data", "exitCommand.txt");


    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

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

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, false, true);
    }

}

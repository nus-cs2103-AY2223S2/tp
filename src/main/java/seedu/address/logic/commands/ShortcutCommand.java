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

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ShortcutCommandParser;
import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in E-Lister.
 * [shortcut/s] COMMAND SHORT_FORM
 */
public class ShortcutCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("shortcut", "s"));

    private static final Path p = Paths.get("data", "shortcutCommand.txt");

    public static final String MESSAGE_USAGE = COMMAND_WORDS
            + ": Adds a shortcut to a command.\n"
            + "Parameters: COMMAND SHORT_FORM \n"
            + "Example: " + "shortcut edit e";

    public static final String MESSAGE_SUCCESS = "New shortcut added: Command: %1$s, Shortcut: %2$s";
    public static final String MESSAGE_INVALID_SHORTCUT = "Shortcut does not exist.";

    private final ShortcutCommandParser.CommandType command;
    private final String shortForm;

    /**
     * Creates a ShortcutCommand to add a shortcut alternative to a certain command.
     *
     * @param command The index of the person to add the tag to
     * @param shortForm The tag to add
     */
    public ShortcutCommand(ShortcutCommandParser.CommandType command, String shortForm) {
        this.command = command;
        this.shortForm = shortForm;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // replace with calling a function inherited by all subclasses
        // on top of adding, do serialization and saving
        if (command.equals(ShortcutCommandParser.CommandType.ADD)) {
            AddCommand.addWord(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.CLEAR)) {
            ClearCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.DELETE)) {
            DeleteCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.DELETE_TAG)) {
            DeleteTagCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.EDIT)) {
            EditCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.EXIT)) {
            ExitCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.EXPORT)) {
            ExportCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.FILTER)) {
            FilterCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.FIND)) {
            FindCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.HELP)) {
            HelpCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.IMPORT)) {
            ImportCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.REDO)) {
            RedoCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.UNDO)) {
            UndoCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.LIST)) {
            ListCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.SHORTCUT)) {
            ShortcutCommand.COMMAND_WORDS.add(this.shortForm);
        } else if (command.equals(ShortcutCommandParser.CommandType.TAG)) {
            TagCommand.COMMAND_WORDS.add(this.shortForm);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, command, shortForm), true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand // instanceof handles nulls
                && command.equals(((ShortcutCommand) other).command)
                && shortForm.equals(((ShortcutCommand) other).shortForm));
    }

    public static void saveWords() {
        Path p = Paths.get("data", "addCommand.txt");
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
        Path p = Paths.get("data", "addCommand.txt");
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

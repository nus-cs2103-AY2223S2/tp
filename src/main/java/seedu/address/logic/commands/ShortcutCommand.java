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
import seedu.address.storage.ShortcutCommandUtil;

/**
 * Changes the remark of an existing person in E-Lister.
 * [shortcut/s] COMMAND SHORT_FORM
 */
public class ShortcutCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("shortcut", "s"));

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
            AddCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.ADD_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.CLEAR)) {
            ClearCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.CLEAR_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.DELETE)) {
            DeleteCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.DELETE_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.DELETE_TAG)) {
            DeleteTagCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.DELETE_TAG_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.EDIT)) {
            EditCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.EDIT_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.EXIT)) {
            ExitCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.EXIT_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.EXPORT)) {
            ExportCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.EXPORT_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.FILTER)) {
            FilterCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.FILTER_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.FIND)) {
            FindCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.FIND_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.HELP)) {
            HelpCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.HELP_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.IMPORT)) {
            ImportCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.IMPORT_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.REDO)) {
            RedoCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.REDO_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.UNDO)) {
            UndoCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.UNDO_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.LIST)) {
            ListCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.LIST_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.SHORTCUT)) {
            ShortcutCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.SHORTCUT_PATH, AddCommand.COMMAND_WORDS);
        } else if (command.equals(ShortcutCommandParser.CommandType.TAG)) {
            TagCommand.COMMAND_WORDS.add(this.shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.TAG_PATH, AddCommand.COMMAND_WORDS);
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

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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

    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("shortcut", "s"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_USAGE = commandWords
            + ": Adds a shortcut to a command.\n"
            + "Parameters: COMMAND SHORT_FORM \n"
            + "Example: " + commandWords.get(0) + " edit e";

    public static final String MESSAGE_SUCCESS = "New shortcut added: Command: %1$s, Shortcut: %2$s";
    public static final String MESSAGE_INVALID_COMMAND = "Command \"%1$s\" does not exist.";
    public static final String MESSAGE_DEBUGGING_ERROR = "Contact an admin";
    public static final String MESSAGE_SHORTCUT_EXISTS = "Shortcut \"%1$s\" already exists.";

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
        boolean doesShortcutExist = ShortcutCommandUtil.checkIfShortcutExists(shortForm);
        if (doesShortcutExist) {
            throw new CommandException(String.format(MESSAGE_SHORTCUT_EXISTS, shortForm));
        }

        if (command.equals(ShortcutCommandParser.CommandType.ADD)) {
            AddCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.ADD_PATH, AddCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.CLEAR)) {
            ClearCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.CLEAR_PATH, ClearCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.DELETE)) {
            DeleteCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.DELETE_PATH, DeleteCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.DELETE_TAG)) {
            DeleteTagCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.DELETE_TAG_PATH, DeleteTagCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.EDIT)) {
            EditCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.EDIT_PATH, EditCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.EXIT)) {
            ExitCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.EXIT_PATH, ExitCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.EXPORT)) {
            ExportCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.EXPORT_PATH, ExportCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.FILTER)) {
            FilterCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.FILTER_PATH, FilterCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.FIND)) {
            FindCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.FIND_PATH, FindCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.FREEZE)) {
            FreezeCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.FREEZE_PATH, FreezeCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.HELP)) {
            HelpCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.HELP_PATH, HelpCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.IMPORT)) {
            ImportCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.IMPORT_PATH, ImportCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.REDO)) {
            RedoCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.REDO_PATH, RedoCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.UNDO)) {
            UndoCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.UNDO_PATH, UndoCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.LIST)) {
            ListCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.LIST_PATH, ListCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.SHORTCUT)) {
            ShortcutCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.SHORTCUT_PATH, ShortcutCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.TAG)) {
            TagCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.TAG_PATH, TagCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.MASS_OP)) {
            MassOpCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.MASS_OP_PATH, MassOpCommand.commandWords);
        } else if (command.equals(ShortcutCommandParser.CommandType.UNFREEZE)) {
            UnfreezeCommand.commandWords.add(shortForm);
            ShortcutCommandUtil.saveWords(ShortcutCommandUtil.UNFREEZE_PATH, UnfreezeCommand.commandWords);
        } else {
            throw new CommandException(MESSAGE_DEBUGGING_ERROR);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, command, shortForm), false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand // instanceof handles nulls
                && command.equals(((ShortcutCommand) other).command)
                && shortForm.equals(((ShortcutCommand) other).shortForm));
    }
}

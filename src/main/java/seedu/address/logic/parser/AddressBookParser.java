package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FreezeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MassOpCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnfreezeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.ShortcutCommandUtil;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses {@code input} into a command targeted at {@code index}.
     *
     * @param input full input string with Index omitted
     * @param index target index
     * @return the command based on {@code input} and {@code index}
     * @throws ParseException if the input does not conform to an expected format
     */
    public static Command parseCommandWithIndex(String input, Index index) throws ParseException {
        requireNonNull(index);
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        // inject index
        final String arguments = index.getOneBased() + " " + matcher.group("arguments");

        ShortcutCommandUtil.loadShortcuts();

        if (EditCommand.commandWords.contains(commandWord)) {
            return new EditCommandParser().parse(arguments);
        } else if (DeleteCommand.commandWords.contains(commandWord)) {
            return new DeleteCommandParser().parse(arguments);
        } else if (DeleteTagCommand.commandWords.contains(commandWord)) {
            return new DeleteTagCommandParser().parse(arguments);
        } else if (TagCommand.commandWords.contains(commandWord)) {
            return new TagCommandParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        ShortcutCommandUtil.loadShortcuts();

        // if the user's command fits a keyword for a command, create a parser
        if (AddCommand.commandWords.contains(commandWord)) {
            return new AddCommandParser().parse(arguments);
        } else if (EditCommand.commandWords.contains(commandWord)) {
            return new EditCommandParser().parse(arguments);
        } else if (DeleteCommand.commandWords.contains(commandWord)) {
            return new DeleteCommandParser().parse(arguments);
        } else if (DeleteTagCommand.commandWords.contains(commandWord)) {
            return new DeleteTagCommandParser().parse(arguments);
        } else if (ClearCommand.commandWords.contains(commandWord)) {
            return new ClearCommand();
        } else if (FilterCommand.commandWords.contains(commandWord)) {
            return new FilterCommandParser().parse(arguments);
        } else if (FreezeCommand.commandWords.contains(commandWord)) {
            return new FreezeCommand();
        } else if (UnfreezeCommand.commandWords.contains(commandWord)) {
            return new UnfreezeCommand();
        } else if (UndoCommand.commandWords.contains(commandWord)) {
            return new UndoCommandParser().parse(arguments);
        } else if (RedoCommand.commandWords.contains(commandWord)) {
            return new RedoCommandParser().parse(arguments);
        } else if (FindCommand.commandWords.contains(commandWord)) {
            return new FindCommandParser().parse(arguments);
        } else if (ListCommand.commandWords.contains(commandWord)) {
            return new ListCommand();
        } else if (ExitCommand.commandWords.contains(commandWord)) {
            return new ExitCommand();
        } else if (HelpCommand.commandWords.contains(commandWord)) {
            return new HelpCommand();
        } else if (TagCommand.commandWords.contains(commandWord)) {
            return new TagCommandParser().parse(arguments);
        } else if (ExportCommand.commandWords.contains(commandWord)) {
            return new ExportCommandParser().parse(arguments);
        } else if (ShortcutCommand.commandWords.contains(commandWord)) {
            return new ShortcutCommandParser().parse(arguments);
        } else if (ImportCommand.commandWords.contains(commandWord)) {
            return new ImportCommandParser().parse(arguments);
        } else if (MassOpCommand.commandWords.contains(commandWord)) {
            return new MassOpCommandParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

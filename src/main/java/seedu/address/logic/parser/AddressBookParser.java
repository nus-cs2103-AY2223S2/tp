package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearTaskCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAllCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindLeadStatusCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.FindTxnByPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.commands.txncommands.AddTxnCommand;
import seedu.address.logic.commands.txncommands.DeleteTxnCommand;
import seedu.address.logic.commands.txncommands.EditTxnCommand;
import seedu.address.logic.commands.txncommands.ListTxnCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.txncommandparser.AddTxnCommandParser;
import seedu.address.logic.parser.txncommandparser.DeleteTxnCommandParser;
import seedu.address.logic.parser.txncommandparser.EditTxnCommandParser;


/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

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
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindTagCommand.COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case FindLeadStatusCommand.COMMAND_WORD:
            return new FindLeadStatusCommandParser().parse(arguments);

        case FindAllCommand.COMMAND_WORD:
            return new FindAllCommandParser().parse(arguments);
        case FindTxnByPersonCommand.COMMAND_WORD:
            return new FindTxnByPersonCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case ClearTaskCommand.COMMAND_WORD:
            return new ClearTaskCommandParser().parse(arguments);

        case StatusCommand.COMMAND_WORD:
            return new StatusCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case AddTxnCommand.COMMAND_WORD:
            return new AddTxnCommandParser().parse(arguments);

        case DeleteTxnCommand.COMMAND_WORD:
            return new DeleteTxnCommandParser().parse(arguments);

        case EditTxnCommand.COMMAND_WORD:
            return new EditTxnCommandParser().parse(arguments);

        case ListTxnCommand.COMMAND_WORD:
            return new ListTxnCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

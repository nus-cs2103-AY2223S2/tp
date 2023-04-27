package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddConsultationCommand;
import seedu.address.logic.commands.AddLabCommand;
import seedu.address.logic.commands.AddNoteToEventCommand;
import seedu.address.logic.commands.AddStudentToEventCommand;
import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.commands.ChangeTabEventCommand;
import seedu.address.logic.commands.ChangeTabStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.DeleteStudentFromEventCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.OpenFileCommand;
import seedu.address.logic.commands.PerformanceCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SortStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case PerformanceCommand.COMMAND_WORD:
            return new PerformanceCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SortStudentCommand.COMMAND_WORD:
            return new SortStudentCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case AddConsultationCommand.COMMAND_WORD:
            return new AddConsultationParser().parse(arguments);

        case AddLabCommand.COMMAND_WORD:
            return new AddLabParser().parse(arguments);

        case AddTutorialCommand.COMMAND_WORD:
            return new AddTutorialParser().parse(arguments);

        case AddNoteToEventCommand.COMMAND_WORD:
            return new AddNoteParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case AddStudentToEventCommand.COMMAND_WORD:
            return new AddStudentToEventParser().parse(arguments);

        case DeleteStudentFromEventCommand.COMMAND_WORD:
            return new DeleteStudentFromEventParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case OpenFileCommand.COMMAND_WORD:
            return new OpenFileCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case EditNoteCommand.COMMAND_WORD:
            return new EditNoteCommandParser().parse(arguments);

        case ChangeTabStudentCommand.COMMAND_WORD:
            return new ChangeTabStudentCommand();

        case ChangeTabEventCommand.COMMAND_WORD:
            return new ChangeTabEventCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

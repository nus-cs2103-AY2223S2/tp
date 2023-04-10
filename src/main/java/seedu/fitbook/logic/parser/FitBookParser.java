package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.fitbook.logic.commands.AddCommand;
import seedu.fitbook.logic.commands.AddExerciseCommand;
import seedu.fitbook.logic.commands.AddRoutineCommand;
import seedu.fitbook.logic.commands.AddWeightCommand;
import seedu.fitbook.logic.commands.ClearCommand;
import seedu.fitbook.logic.commands.ClearRoutinesCommand;
import seedu.fitbook.logic.commands.Command;
import seedu.fitbook.logic.commands.DeleteCommand;
import seedu.fitbook.logic.commands.DeleteExerciseCommand;
import seedu.fitbook.logic.commands.DeleteRoutineCommand;
import seedu.fitbook.logic.commands.EditCommand;
import seedu.fitbook.logic.commands.EditRoutineCommand;
import seedu.fitbook.logic.commands.ExitCommand;
import seedu.fitbook.logic.commands.ExportCommand;
import seedu.fitbook.logic.commands.ExportRoutineCommand;
import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.logic.commands.FindRoutineCommand;
import seedu.fitbook.logic.commands.GraphCommand;
import seedu.fitbook.logic.commands.HelpCommand;
import seedu.fitbook.logic.commands.ListClientsCommand;
import seedu.fitbook.logic.commands.ListRoutinesCommand;
import seedu.fitbook.logic.commands.ViewDetailCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FitBookParser {

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

        case AddRoutineCommand.COMMAND_WORD:
            return new AddRoutineCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case EditRoutineCommand.COMMAND_WORD:
            return new EditRoutineCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ClearRoutinesCommand.COMMAND_WORD:
            return new ClearRoutinesCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListClientsCommand.COMMAND_WORD:
            return new ListClientsCommand();

        case ListRoutinesCommand.COMMAND_WORD:
            return new ListRoutinesCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case ExportRoutineCommand.COMMAND_WORD:
            return new ExportRoutineCommand();

        case FindRoutineCommand.COMMAND_WORD:
            return new FindRoutineCommandParser().parse(arguments);

        case DeleteRoutineCommand.COMMAND_WORD:
            return new DeleteRoutineCommandParser().parse(arguments);

        case AddExerciseCommand.COMMAND_WORD:
            return new AddExerciseCommandParser().parse(arguments);

        case DeleteExerciseCommand.COMMAND_WORD:
            return new DeleteExerciseCommandParser().parse(arguments);

        case AddWeightCommand.COMMAND_WORD:
            return new AddWeightCommandParser().parse(arguments);

        case GraphCommand.COMMAND_WORD:
            return new GraphCommandParser().parse(arguments);

        case ViewDetailCommand.COMMAND_WORD:
            return new ViewDetailCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

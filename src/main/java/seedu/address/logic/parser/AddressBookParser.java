package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewProfileCommand;
import seedu.address.logic.commands.exam.CreateExamCommand;
import seedu.address.logic.commands.exam.DeleteExamCommand;
import seedu.address.logic.commands.exam.UpdateExamCommand;
import seedu.address.logic.commands.exam.ViewExamCommand;
import seedu.address.logic.commands.homework.CreateHomeworkCommand;
import seedu.address.logic.commands.homework.DeleteHomeworkCommand;
import seedu.address.logic.commands.homework.MarkHomeworkAsDoneCommand;
import seedu.address.logic.commands.homework.MarkHomeworkAsUndoCommand;
import seedu.address.logic.commands.homework.UpdateHomeworkCommand;
import seedu.address.logic.commands.homework.ViewHomeworkCommand;
import seedu.address.logic.commands.lesson.CreateLessonCommand;
import seedu.address.logic.commands.lesson.DeleteLessonCommand;
import seedu.address.logic.commands.lesson.UpdateLessonCommand;
import seedu.address.logic.commands.lesson.ViewLessonCommand;
import seedu.address.logic.parser.exam.CreateExamCommandParser;
import seedu.address.logic.parser.exam.DeleteExamCommandParser;
import seedu.address.logic.parser.exam.UpdateExamCommandParser;
import seedu.address.logic.parser.exam.ViewExamCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.homework.CreateHomeworkCommandParser;
import seedu.address.logic.parser.homework.DeleteHomeworkCommandParser;
import seedu.address.logic.parser.homework.MarkHomeworkAsDoneCommandParser;
import seedu.address.logic.parser.homework.MarkHomeworkAsUndoCommandParser;
import seedu.address.logic.parser.homework.UpdateHomeworkCommandParser;
import seedu.address.logic.parser.homework.ViewHomeworkCommandParser;
import seedu.address.logic.parser.lesson.CreateLessonCommandParser;
import seedu.address.logic.parser.lesson.DeleteLessonCommandParser;
import seedu.address.logic.parser.lesson.UpdateLessonCommandParser;
import seedu.address.logic.parser.lesson.ViewLessonCommandParser;

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

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // Homework commands
        case CreateHomeworkCommand.COMMAND_WORD:
            return new CreateHomeworkCommandParser().parse(arguments);

        case ViewHomeworkCommand.COMMAND_WORD:
            return new ViewHomeworkCommandParser().parse(arguments);

        case MarkHomeworkAsUndoCommand.COMMAND_WORD:
            return new MarkHomeworkAsUndoCommandParser().parse(arguments);

        case DeleteHomeworkCommand.COMMAND_WORD:
            return new DeleteHomeworkCommandParser().parse(arguments);

        case MarkHomeworkAsDoneCommand.COMMAND_WORD:
            return new MarkHomeworkAsDoneCommandParser().parse(arguments);

        case UpdateHomeworkCommand.COMMAND_WORD:
            return new UpdateHomeworkCommandParser().parse(arguments);

        // Lesson commands
        case ViewLessonCommand.COMMAND_WORD:
            return new ViewLessonCommandParser().parse(arguments);

        case CreateLessonCommand.COMMAND_WORD:
            return new CreateLessonCommandParser().parse(arguments);

        case DeleteLessonCommand.COMMAND_WORD:
            return new DeleteLessonCommandParser().parse(arguments);

        // Exam commands
        case CreateExamCommand.COMMAND_WORD:
            return new CreateExamCommandParser().parse(arguments);

        case ViewExamCommand.COMMAND_WORD:
            return new ViewExamCommandParser().parse(arguments);

        case DeleteExamCommand.COMMAND_WORD:
            return new DeleteExamCommandParser().parse(arguments);

        case UpdateLessonCommand.COMMAND_WORD:
            return new UpdateLessonCommandParser().parse(arguments);

        case UpdateExamCommand.COMMAND_WORD:
            return new UpdateExamCommandParser().parse(arguments);

        case ViewProfileCommand.COMMAND_WORD:
            return new ViewProfileCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

package seedu.address.logic.parser.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.task.TaskAddCommand;
import seedu.address.logic.commands.task.TaskCommand;
import seedu.address.logic.commands.task.TaskDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for {@code Task} commands.
 */
public class TaskParser {

    /**
     * Used for separation of task command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<taskCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into {@code Task} command for execution.
     *
     * @param userInput User input string excluding the "Task" command word.
     * @return The {@code Task} command based on the user input.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }

        final String taskCommandWord = matcher.group("taskCommandWord");
        final String arguments = matcher.group("arguments");
        switch (taskCommandWord) {
        case TaskAddCommand.TASK_COMMAND_WORD:
            return new TaskAddCommandParser().parse(arguments);

        case TaskDeleteCommand.TASK_COMMAND_WORD:
            return new TaskDeleteCommandParser().parse(arguments);

        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }
    }

}


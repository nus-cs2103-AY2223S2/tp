package seedu.socket.logic.parser;


import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.model.project.Project.CATEGORIES;
import static seedu.socket.model.project.Project.PROJ_DEADLINE;

import seedu.socket.logic.commands.SortProjectCommand;
import seedu.socket.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortProjectCommand object
 */
public class SortProjectCommandParser implements Parser<SortProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortProjectCommand
     * and returns a SortProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortProjectCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new SortProjectCommand(PROJ_DEADLINE);
        } else if (CATEGORIES.contains(trimmedArgs)) {
            return new SortProjectCommand(trimmedArgs);
        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortProjectCommand.MESSAGE_USAGE));
        }
    }
}

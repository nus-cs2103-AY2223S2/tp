package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.GraphCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddRoutineCommand object
 */
public class GraphCommandParser implements Parser<GraphCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoutineCommand
     * and returns an AddRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GraphCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        return new GraphCommand(index);
    }
}

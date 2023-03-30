package seedu.address.logic.parser;

import seedu.address.logic.commands.UnorganiseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meetup.MeetUpIndex;

/**
 * Parses input arguments and creates a new UnorganiseCommand object
 */
public class UnorganiseCommandParser implements Parser<UnorganiseCommand> {

    private final static String MESSAGE_INVALID_DELETE_COMMAND_FORMAT = "Invalid index given";

    /**
     * Parses the given {@code String} of arguments in the context of the UnorganiseCommand
     * and returns a UnorganiseCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public UnorganiseCommand parse(String args) throws ParseException {
        try {
            MeetUpIndex meetUpIndex = new MeetUpIndex(Integer.parseInt(args.trim()));
            return new UnorganiseCommand(meetUpIndex);
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_DELETE_COMMAND_FORMAT);
        }
    }
}

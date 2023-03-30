package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MassOpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MassOpCommand object
 */
public class MassOpCommandParser implements Parser<MassOpCommand> {
    public static final String MESSAGE_INVALID_REGEX = "Invalid regex: %1$s\n(becomes: %2$s)";

    /**
     * Parses the given {@code String} of arguments in the context of the MassOpCommand
     * and returns a MassOpCommand object for execution.
     *
     * @param args The arguments to the MassOpCommand
     * @return The parsed MassOpCommand
     * @throws ParseException if {@code args} does not conform the expected format
     */
    public MassOpCommand parse(String args) throws ParseException {
        //trim the white spaces
        args = args.trim();
        try {
            AddressBookParser.parseCommandWithIndex(args, Index.fromZeroBased(0));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassOpCommand.MESSAGE_USAGE), pe);
        }

        return new MassOpCommand(args);
    }

}

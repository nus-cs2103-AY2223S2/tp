package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkContactCommand object.
 */
public class LinkContactCommandParser implements Parser<LinkContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LinkContactCommand
     * and returns an LinkContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkContactCommand parse(String args) throws ParseException {
        try {
            String[] argarr = args.trim().split(" ");
            if (argarr.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        LinkContactCommand.MESSAGE_USAGE));
            }
            Index index = ParserUtil.parseIndex(argarr[0]);
            String num = argarr[1];
            return new LinkContactCommand(index, num);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            LinkContactCommand.MESSAGE_USAGE), pe);
        }
    }
}

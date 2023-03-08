package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input commands and creates a View Command.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        // shows user information
        if (args.isEmpty()) {
            return new ViewCommand();
        }
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                args, Prefix.NAME);
        // show user by querying name
        if (argumentMultimap.getValue(Prefix.NAME).isPresent()) {
            String name = argumentMultimap.getValue(Prefix.NAME).get();
            return new ViewCommand(name);
        }
        // show user by querying index
        Index index;
        if (!argumentMultimap.getPreamble().isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
                return new ViewCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.USAGE));
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.USAGE));
    }
}

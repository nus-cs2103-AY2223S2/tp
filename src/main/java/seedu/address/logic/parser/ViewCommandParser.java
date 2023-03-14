package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;


/**
 * Parses input commands and creates a View Command.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform to the expected format
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        // shows user information
        if (args.isEmpty()) {
            return new ViewCommand(null, null);
        }
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                args, Prefix.NAME);
        // show user by querying name
        if (argumentMultimap.getValue(Prefix.NAME).isPresent()) {
            String name = argumentMultimap.getValue(Prefix.NAME).get();
            return new ViewCommand(name, null);
        }
        // show user by querying index
        ContactIndex index;
        if (!argumentMultimap.getPreamble().isEmpty()) {
            try {
                index = new ContactIndex(Integer.parseInt(argumentMultimap.getPreamble()));
                return new ViewCommand(null, index);
            } catch (Exception e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.USAGE));
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.USAGE));
    }
}

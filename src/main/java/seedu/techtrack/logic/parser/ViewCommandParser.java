package seedu.techtrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.logic.commands.ViewCommand;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }

        return new ViewCommand(index);
    }

}

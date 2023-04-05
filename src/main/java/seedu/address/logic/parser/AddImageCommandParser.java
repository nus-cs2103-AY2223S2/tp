package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_IMAGE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddImageCommand;
import seedu.address.logic.parser.exceptions.ParseException;




/**
 * Parses input arguments and creates a new {@code AddImageCommand} object
 */
public class AddImageCommandParser implements Parser<AddImageCommand> {

    /**
     * Parses given {@code String} of arguments in the context of the {@code AddImageCommand}.
     * @param args user input
     * @return {@code AddImageCommand} object for execution
     * @throws ParseException if the user input does not conform to expected format
     */
    public AddImageCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ADD_IMAGE);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddImageCommand.MESSAGE_USAGE), ive);
        }
        String path = argMultimap.getValue(PREFIX_ADD_IMAGE).orElse("");
        if (path == null || path.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddImageCommand.MESSAGE_USAGE));
        }
        return new AddImageCommand(index, path);

    }

}

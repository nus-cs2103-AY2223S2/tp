package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.UnassignCommand;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.person.Name;

/**
 * Parses input arguments and creates a new UnassignCommand object
 */
public class UnassignCommandParser implements Parser<UnassignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnassignCommand
     * and returns an UnassignCommand object for execution.
     * @param args
     * @return UnassignCommand
     * @throws ParseException
     */
    public UnassignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignCommand.MESSAGE_USAGE), pe);
        }
        Name name;
        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignCommand.MESSAGE_USAGE), pe);
        }
        return new UnassignCommand(index, name);
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InternshipStatus;

/**
 * Parses input arguments and creates a new EditStatusCommand object
 */
public class EditStatusCommandParser implements Parser<EditStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStatusCommand
     * and returns an EditStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStatusCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStatusCommand.MESSAGE_USAGE));
        }

        InternshipStatus status = ParserUtil.parseInternshipStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new EditStatusCommand(index, status);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

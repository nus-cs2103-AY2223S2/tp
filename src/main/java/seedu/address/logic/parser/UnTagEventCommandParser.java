package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TO_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_TO_TAG;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnTagEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.fields.Name;

/**
 * Parses input arguments and creates a new UnTagEventCommand object
 */
public class UnTagEventCommandParser implements Parser<UnTagEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnTagEventCommand
     * and returns a UnTagEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnTagEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_TO_TAG, PREFIX_PERSON_TO_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_TO_TAG, PREFIX_PERSON_TO_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnTagEventCommand.MESSAGE_USAGE));
        }

        Index eventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_TO_TAG).get());
        Name taggingPersonName = ParserUtil.parseName(argMultimap.getValue(PREFIX_PERSON_TO_TAG).get());

        return new UnTagEventCommand(eventIndex, taggingPersonName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the
     * given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

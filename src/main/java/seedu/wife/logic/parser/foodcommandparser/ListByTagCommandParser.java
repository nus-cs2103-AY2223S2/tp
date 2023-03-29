package seedu.wife.logic.parser.foodcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Set;

import seedu.wife.logic.commands.foodcommands.ListByTagCommand;
import seedu.wife.logic.parser.ArgumentMultimap;
import seedu.wife.logic.parser.ArgumentTokenizer;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.tag.Tag;

/**
 * Parses input arguments and creates a new ListByTagCommand object
 */
public class ListByTagCommandParser implements Parser<ListByTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListByTagCommand
     * and returns a ListByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListByTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (
            !ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()
        ) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListByTagCommand.MESSAGE_USAGE)
            );
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_NAME));

        return new ListByTagCommand(tagList);
    }

}

package arb.logic.parser.client;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.client.FindClientCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.Prefix;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Client;
import arb.model.client.predicates.ClientContainsTagPredicate;
import arb.model.client.predicates.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {

    public static final String EMPTY_NAME_ERROR = "Name cannot be empty!";
    public static final String EMPTY_TAG_ERROR = "Tag cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the FindClientCommand
     * and returns a FindClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_TAG);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Client>> predicates = new ArrayList<>();
        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (tags.stream().anyMatch(t -> t.isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_TAG_ERROR));
        }
        if (!tags.isEmpty()) {
            predicates.add(new ClientContainsTagPredicate(tags));
        }

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        if (nameKeywords.stream().anyMatch(t -> t.isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_NAME_ERROR));
        }
        if (!nameKeywords.isEmpty()) {
            predicates.add(new NameContainsKeywordsPredicate(nameKeywords));
        }

        return new FindClientCommand(new CombinedPredicate<>(predicates));
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

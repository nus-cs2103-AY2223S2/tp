package arb.logic.parser.client;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_NO_VALID_PARAMETERS;
import static arb.commons.util.StringUtil.splitKeywords;
import static arb.logic.parser.ArgumentMultimap.areAnyPrefixesPresent;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.client.FindClientCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Client;
import arb.model.client.Name;
import arb.model.client.predicates.ClientContainsTagsPredicate;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {

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

        // filter out all invalid tags
        Stream<String> tags = argMultimap.getAllValues(PREFIX_TAG).stream().flatMap(s -> splitKeywords(s))
                .filter(s -> Tag.isValidTagName(s));
        List<String> listOfTags = tags.collect(Collectors.toList());
        if (!listOfTags.isEmpty()) {
            predicates.add(new ClientContainsTagsPredicate(listOfTags));
        }

        // filter out all invalid names
        Stream<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME).stream().flatMap(s -> splitKeywords(s))
                .filter(s -> Name.isValidName(s));
        List<String> listOfNameKeywords = nameKeywords.collect(Collectors.toList());
        if (!listOfNameKeywords.isEmpty()) {
            predicates.add(new NameContainsKeywordsPredicate(listOfNameKeywords));
        }

        if (predicates.isEmpty()) {
            throw new ParseException(MESSAGE_NO_VALID_PARAMETERS);
        }

        return new FindClientCommand(new CombinedPredicate<>(predicates));
    }

}

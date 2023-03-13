package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Language;
import seedu.address.model.tag.LanguageContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;


/**
 * Parses the list command entered by user
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     */
    public ListCommand parse(String args) throws ParseException {
        boolean areKeywordsPresent = true;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LANGUAGE, PREFIX_TAG);

        if ((!arePrefixesPresent(argMultimap, PREFIX_TAG)
                && !arePrefixesPresent(argMultimap, PREFIX_LANGUAGE))) {
            areKeywordsPresent = false;

        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Set<Language> langList = ParserUtil.parseLanguages(argMultimap.getAllValues(PREFIX_LANGUAGE));

        return new ListCommand(new TagContainsKeywordsPredicate(tagList),
                new LanguageContainsKeywordsPredicate(langList), areKeywordsPresent);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

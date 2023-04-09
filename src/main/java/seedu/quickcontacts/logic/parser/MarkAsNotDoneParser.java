package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;

import java.util.ArrayList;
import java.util.List;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.MarkAsNotDoneCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;

/**
 * Parses a mark as not done command
 */
public class MarkAsNotDoneParser implements Parser<MarkAsNotDoneCommand> {
    public static final Prefix[] PREFIXES = {PREFIX_MEETING_TITLE};

    @Override
    public MarkAsNotDoneCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        List<String> indexStrings = argMultimap.getAllValues(PREFIX_MEETING_TITLE);
        if (indexStrings.size() < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAsNotDoneCommand.MESSAGE_USAGE));
        }
        List<Index> indexes = new ArrayList<>();
        for (String s : indexStrings) {
            try {
                if (!indexes.contains(ParserUtil.parseIndex(s))) {
                    indexes.add(ParserUtil.parseIndex(s));
                }
            } catch (ParseException parseException) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MarkAsNotDoneCommand.MESSAGE_USAGE),
                        parseException);
            }
        }
        return new MarkAsNotDoneCommand(indexes);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(PREFIX_MEETING_TITLE, false);
    }
}

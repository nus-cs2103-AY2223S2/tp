package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.ArrayList;
import java.util.List;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.ExportPersonsCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;

/**
 * Parser for exporting of persons command
 */
public class ExportPersonsParser implements Parser<ExportPersonsCommand> {

    public static final Prefix[] PREFIXES = {PREFIX_PERSON};

    @Override
    public ExportPersonsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        List<String> indexStrings = argMultimap.getAllValues(PREFIX_PERSON);
        if (indexStrings.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportPersonsCommand.MESSAGE_USAGE));
        }
        List<Index> indexes = new ArrayList<>();
        for (String s : indexStrings) {
            try {
                if (!indexes.contains(ParserUtil.parseIndex(s))) {
                    indexes.add(ParserUtil.parseIndex(s));
                }
            } catch (ParseException parseException) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ExportPersonsCommand.MESSAGE_USAGE),
                        parseException);
            }
        }

        return new ExportPersonsCommand(indexes);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(PREFIX_PERSON, false);
    }
}

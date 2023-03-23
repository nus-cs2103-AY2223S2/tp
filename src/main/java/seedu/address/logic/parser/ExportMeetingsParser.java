package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.ExportMeetingsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for parsing export meeting commands
 */
public class ExportMeetingsParser implements Parser<ExportMeetingsCommand> {
    public static final Prefix[] PREFIXES = {PREFIX_MEETING_TITLE};

    @Override
    public ExportMeetingsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        List<String> indexStrings = argMultimap.getAllValues(PREFIX_MEETING_TITLE);
        if (indexStrings.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportMeetingsCommand.MESSAGE_USAGE));
        }
        List<Index> indexes = new ArrayList<>();
        for (String s : indexStrings) {
            try {
                indexes.add(ParserUtil.parseIndex(s));
            } catch (ParseException parseException) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ExportMeetingsCommand.MESSAGE_USAGE),
                        parseException);
            }
        }

        return new ExportMeetingsCommand(indexes);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(PREFIX_MEETING_TITLE, false);
    }
}

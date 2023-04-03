package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_END;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_START;

import java.util.ArrayList;
import java.util.List;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.ExportMeetingsCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.meeting.DateTime;

/**
 * Parser for parsing export meeting commands
 */
public class ExportMeetingsParser implements Parser<ExportMeetingsCommand> {
    public static final Prefix[] PREFIXES = {PREFIX_MEETING_TITLE, PREFIX_START, PREFIX_END};

    @Override
    public ExportMeetingsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        List<String> indexStrings = argMultimap.getAllValues(PREFIX_MEETING_TITLE);
        List<Index> indexes = new ArrayList<>();
        for (String s : indexStrings) {
            try {
                if (!indexes.contains(ParserUtil.parseIndex(s))) {
                    indexes.add(ParserUtil.parseIndex(s));
                }
            } catch (ParseException parseException) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ExportMeetingsCommand.MESSAGE_USAGE),
                        parseException);
            }
        }
        DateTime start = null;
        DateTime end = null;
        try {
            if (argMultimap.getValue(PREFIX_START).isPresent()) {
                start = new DateTime(argMultimap.getValue(PREFIX_START).get());
            }
            if (argMultimap.getValue(PREFIX_END).isPresent()) {
                end = new DateTime(argMultimap.getValue(PREFIX_END).get());
            }
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportMeetingsCommand.MESSAGE_USAGE));
        }
        if (start == null && end == null && indexes.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportMeetingsCommand.MESSAGE_USAGE));
        }
        return new ExportMeetingsCommand(indexes, start, end);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        String[] argsSplit = input.split(" ");
        if (argsSplit.length == 0) {
            return new AutocompleteResult(PREFIXES[0], false);
        }
        String lastPrefix = argsSplit[argsSplit.length - 1];

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + lastPrefix, PREFIXES);
        boolean returnNext = false;

        for (Prefix p : PREFIXES) {
            if (returnNext) {
                return new AutocompleteResult(p, true);
            }

            if (argMultimap.getValue(p).isPresent() && p.toString().equals(lastPrefix)) {
                returnNext = true;
            }
        }

        return new AutocompleteResult(PREFIXES[0], true);
    }

}


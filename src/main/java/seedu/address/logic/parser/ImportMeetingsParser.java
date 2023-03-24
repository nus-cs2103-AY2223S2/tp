package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.ImportMeetingsCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORCE;

import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.ImportMeetingsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class represents a parser for importing of meetings
 */
public class ImportMeetingsParser implements Parser<ImportMeetingsCommand> {
    static final Prefix[] PREFIXES = new Prefix[]{PREFIX_FORCE};

    @Override
    public ImportMeetingsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        String json = argMultimap.getPreamble();
        boolean isForced = argMultimap.getValue(PREFIX_FORCE).isPresent();
        if (json.equals("")) {
            throw new ParseException(MESSAGE_USAGE);
        }
        return new ImportMeetingsCommand(json, isForced);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(null, false);
    }
}

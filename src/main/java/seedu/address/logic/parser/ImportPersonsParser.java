package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.ImportPersonsCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORCE;

import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.ImportPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class represents a parser for importing of persons
 */
public class ImportPersonsParser implements Parser<ImportPersonsCommand> {
    static final Prefix[] PREFIXES = new Prefix[]{PREFIX_FORCE};

    @Override
    public ImportPersonsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        String json = argMultimap.getPreamble();
        boolean isForced = argMultimap.getValue(PREFIX_FORCE).isPresent();
        if (json.equals("")) {
            throw new ParseException(MESSAGE_USAGE);
        }
        return new ImportPersonsCommand(json, isForced);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(null, false);
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.ImportPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class represents a parser for importing of persons
 */
public class ImportPersonsParser implements Parser<ImportPersonsCommand> {

    @Override
    public ImportPersonsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput);
        String json = argMultimap.getPreamble();
        if (json.equals("")) {
            throw new ParseException("Wrong format!");
        }
        return new ImportPersonsCommand(json);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult(null, false);
    }
}

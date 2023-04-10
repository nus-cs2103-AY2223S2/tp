package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.commands.ImportPersonsCommand.MESSAGE_USAGE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_FORCE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.quickcontacts.commons.exceptions.IllegalValueException;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.ImportPersonsCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.storage.JsonAdaptedPerson;

/**
 * This class represents a parser for importing of persons
 */
public class ImportPersonsParser implements Parser<ImportPersonsCommand> {
    static final Prefix[] PREFIXES = new Prefix[]{PREFIX_FORCE};
    static final String MALFORMED_JSON = "JSON input malformed";

    @Override
    public ImportPersonsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIXES);
        String json = argMultimap.getPreamble();
        boolean isForced = argMultimap.getValue(PREFIX_FORCE).isPresent();
        if (json.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        List<Person> people = new ArrayList<>();
        try {
            JsonAdaptedPerson[] jsonAdaptedPeople = JsonUtil.fromJsonString(json, JsonAdaptedPerson[].class);
            for (JsonAdaptedPerson jsonAdaptedPerson : jsonAdaptedPeople) {
                people.add(jsonAdaptedPerson.toModelType());
            }
        } catch (IOException | IllegalValueException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MALFORMED_JSON));
        }
        return new ImportPersonsCommand(people, isForced);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult();
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ImportPersonsCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORCE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.ImportPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAdaptedPerson;

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
        return new AutocompleteResult(null, false);
    }
}

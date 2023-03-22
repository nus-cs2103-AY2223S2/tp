package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.storage.JsonAdaptedPerson;

/**
 * This class represents a command for importing of persons
 */
public class ImportPersonsCommand extends Command {
    public static final String COMMAND_WORD = "import";
    static final String MALFORMED_JSON = "JSON input malformed";
    static final String DUPLICATE_PERSON = "Duplicate person found";
    static final String SUCCESS = "Persons imported";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports persons in JSON format\n" + "Parameters: f/ "
            + "true: to force imports regardless of duplicates";
    private final String json;
    private final boolean isForced;

    /**
     * Creates a new ImportPersonsCommand
     *
     * @param json     person to be imported
     * @param isForced whether to force imports regardless of duplicates
     */
    public ImportPersonsCommand(String json, boolean isForced) {
        this.json = json;
        this.isForced = isForced;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            JsonAdaptedPerson[] personList = JsonUtil.fromJsonString(json, JsonAdaptedPerson[].class);
            if (isForced) {
                for (JsonAdaptedPerson jsonAdaptedPerson : personList) {
                    if (!model.hasPerson(jsonAdaptedPerson.toModelType())) {
                        model.addPerson(jsonAdaptedPerson.toModelType());
                    }
                }
            } else {
                for (JsonAdaptedPerson jsonAdaptedPerson : personList) {
                    if (model.hasPerson(jsonAdaptedPerson.toModelType())) {
                        throw new DuplicatePersonException();
                    }
                }
                for (JsonAdaptedPerson p : personList) {
                    model.addPerson(p.toModelType());
                }
            }
            return new CommandResult(SUCCESS);
        } catch (IOException | IllegalValueException e) {
            throw new CommandException(MALFORMED_JSON);
        } catch (DuplicatePersonException e) {
            throw new CommandException(DUPLICATE_PERSON);
        }
    }
}

package seedu.quickcontacts.logic.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.storage.JsonAdaptedPerson;

/**
 * This class represents a command for exporting of persons
 */
public class ExportPersonsCommand extends Command {
    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports persons in JSON format\n" + "Parameters: p/ "
            + "index of person";
    public static final String INDEX_NOT_FOUND = "One of the provided indices is not found";
    public static final String COMMAND_DESCRIPTION = "Exports persons in JSON format.";

    private final List<Index> indexList;

    public ExportPersonsCommand(List<Index> indexList) {
        this.indexList = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            List<Person> personList = model.getPersonsByIndexes(indexList);
            List<JsonAdaptedPerson> adapted =
                    personList
                            .stream()
                            .map(JsonAdaptedPerson::new)
                            .collect(Collectors.toList());
            return new CommandResult(JsonUtil.toJsonString(adapted));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(INDEX_NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new CommandException("Error turning to json");
        }
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportPersonsCommand
                && indexList.equals(((ExportPersonsCommand) other).indexList));
    }

}

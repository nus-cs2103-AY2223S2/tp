package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ModuleTag;

/**
 * Sorts the persons by any field.
 * If it is a tag field, then sort by the number of common tags.
 * Else, sort by alphanumerical order.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // we copy here as the above personList is immutable
        List<Person> sortedPersonList = new ArrayList<>(model.getFilteredPersonList());
        Set<ModuleTag> userModuleTags = model.getUser().getImmutableModuleTags();

        // caches the common modules in each ModuleTagSet as running set
        // intersection is expensive if we only use it in the compareTo method
        sortedPersonList.forEach(person -> person.setCommonModules(userModuleTags));
        sortedPersonList.sort(comparator);

        // currently the only way to sort persons in the list
        model.resetPersons();
        sortedPersonList.forEach(model::addPerson);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}

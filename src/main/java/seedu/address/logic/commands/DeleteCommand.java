package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.INDEX_PLACEHOLDER;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes multiple persons identified using their displayed indexes from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(
            INDEX_PLACEHOLDER.setExamples("1"),
            INDEX_PLACEHOLDER.asOptional().asRepeatable().setExamples("3", "5")
    ));

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the persons identified by the index numbers used in the displayed person list."
            + "\n" + getParameterUsage(ARGUMENT_PREFIXES)
            + "\n" + getExampleUsage(COMMAND_WORD, ARGUMENT_PREFIXES);

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Persons: %1$s";

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        List<Person> personsToDelete = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            personsToDelete.add(lastShownList.get(targetIndex.getZeroBased()));
        }


        for (Person personToDelete : personsToDelete) {
            model.deletePerson(personToDelete);
        }

        String deletedPersons = String.join(", ", personsToDelete.stream()
                .map(Person::toString)
                .toArray(String[]::new));
        model.commitAddressBook(COMMAND_WORD);
        model.setDefaultShowPerson();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPersons));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteCommand
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes));
    }
}

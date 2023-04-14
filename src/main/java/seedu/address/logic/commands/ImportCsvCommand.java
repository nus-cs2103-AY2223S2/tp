package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.util.Pair;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonUtil;

/**
 * Adds persons from a given CSV file to the address book.
 */
public class ImportCsvCommand extends Command {

    public static final String COMMAND_WORD = "importcsv";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds person(s) from a CSV file to the address book. "
            + "Parameters: PATH (to CSV file)";

    public static final String MESSAGE_SUCCESS = "%1$d new person(s) added.";
    public static final String MESSAGE_DUPLICATE_PERSON_CSV = "Rows %1$d and %2$d (%3$s) are duplicates";
    public static final String MESSAGE_DUPLICATE_PERSON_ADDRESS_BOOK =
            "Row %1$d (%2$s) already exists in the address book";
    public static final int HEADER_AND_ZERO_INDEX_OFFSET = 2;

    private final List<Person> personsToAdd;
    private final int numOfPersons;

    /**
     * Creates an ImportCsvCommand to add the specified {@code Person}s
     */
    public ImportCsvCommand(List<Person> personsToAdd) {
        requireNonNull(personsToAdd);
        this.personsToAdd = personsToAdd;
        this.numOfPersons = personsToAdd.size();
    }

    private static void requireNoDuplicates(List<Person> personList) throws CommandException {
        if (PersonUtil.hasDuplicates(personList)) {
            Pair<Integer, Integer> pairOfDuplicates = PersonUtil.findDuplicates(personList);
            String duplicatedFieldString = PersonUtil.findDuplicateFieldString(
                    personList.get(pairOfDuplicates.getKey()), personList.get(pairOfDuplicates.getValue()));
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON_CSV,
                    pairOfDuplicates.getKey() + HEADER_AND_ZERO_INDEX_OFFSET, pairOfDuplicates.getValue()
                            + HEADER_AND_ZERO_INDEX_OFFSET, duplicatedFieldString));
        }
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
        requireNoDuplicates(personsToAdd);
        addPersonsToModel(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, numOfPersons));
    }

    private void addPersonsToModel(Model model) throws CommandException {
        if (model.hasPersons(personsToAdd)) {
            int index = model.getDuplicateIndex(personsToAdd);
            assert index >= 0 : "no duplicate found even though duplicates between CSV and address book were reported";
            String duplicatedField = model.getDuplicateString(personsToAdd.get(index));
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON_ADDRESS_BOOK,
                    index + HEADER_AND_ZERO_INDEX_OFFSET, duplicatedField));
        }

        model.addPersons(personsToAdd);

        model.commit(model.getAddressBook());
    }
}

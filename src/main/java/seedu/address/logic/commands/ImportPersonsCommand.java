package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * This class represents a command for importing of persons
 */
public class ImportPersonsCommand extends Command {
    public static final String COMMAND_WORD = "import";
    static final String DUPLICATE_PERSON = "Duplicate person found";
    static final String SUCCESS = "Persons imported";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports persons in JSON format\n" + "Parameters: -f"
            + " to force imports regardless of duplicates";
    private final List<Person> people;
    private final boolean isForced;

    /**
     * Creates a new ImportPersonsCommand
     *
     * @param people   people to import
     * @param isForced whether to force imports regardless of duplicates
     */
    public ImportPersonsCommand(List<Person> people, boolean isForced) {
        this.people = people;
        this.isForced = isForced;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            if (isForced) {
                for (Person person : people) {
                    if (!model.hasPerson(person)) {
                        model.addPerson(person);
                    }
                }
            } else {
                for (Person person : people) {
                    if (model.hasPerson(person)) {
                        throw new DuplicatePersonException();
                    }
                }
                for (Person person : people) {
                    model.addPerson(person);
                }
            }
            return new CommandResult(SUCCESS);
        } catch (DuplicatePersonException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DUPLICATE_PERSON));
        }
    }

    @Override
    public boolean equals(Object o) {
        return o == this // short circuit if same object
                || (o instanceof ImportPersonsCommand
                && people.equals(((ImportPersonsCommand) o).people));
    }
}

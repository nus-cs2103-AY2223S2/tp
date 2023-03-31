package seedu.quickcontacts.logic.commands;

import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_FORCE;

import java.util.List;

import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.model.person.exceptions.DuplicatePersonException;

/**
 * This class represents a command for importing of persons
 */
public class ImportPersonsCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String COMMAND_DESCRIPTION = "Imports persons in JSON format.";
    static final String DUPLICATE_PERSON = "Duplicate person found";
    static final String SUCCESS = "Persons imported";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports persons in JSON format\n" + "Parameters: "
            + PREFIX_FORCE
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
            throw new CommandException(DUPLICATE_PERSON);
        }
    }

    @Override
    public boolean equals(Object o) {
        return o == this // short circuit if same object
                || (o instanceof ImportPersonsCommand
                && people.equals(((ImportPersonsCommand) o).people));
    }
}

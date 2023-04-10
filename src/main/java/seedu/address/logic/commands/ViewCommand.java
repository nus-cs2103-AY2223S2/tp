package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;


/**
 * Allows users to view full profile of their friends at the right panel.
 */
public class ViewCommand extends Command {


    public static final String COMMAND_WORD = "view";
    public static final String USAGE = "view <index> : Allows you to view the profile of the person who "
            + "has the index\n"
            + "view n/FULL_NAME : Allows you to view profile for the specific person\n"
            + "view : Shows your own profile instead";
    private static final Logger logger = LogsCenter.getLogger(ViewCommand.class);
    private final Optional<String> name;
    private final Optional<ContactIndex> index;

    /**
     * Creates a View Command with the person's name and index.
     * @param index
     */
    public ViewCommand(String name, ContactIndex index) {
        this.name = Optional.ofNullable(name);
        this.index = Optional.ofNullable(index);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public ViewCommandResult execute(Model model) throws CommandException {
        Optional<Person> person = retrievePerson(model);
        logger.info(String.format("Attempting to execute in ViewCommand"));
        if (person.isEmpty()) {
            return new ViewCommandResult("No such person found!", model.getUser());
        }
        Person contact = person.get();
        return new ViewCommandResult(contact.toString(), contact);
    }

    /**
     * Retrieves either a Person by its index or name or the User itself.
     * @param model {@code Model} which the command should operate on.
     * @return
     */
    public Optional<Person> retrievePerson(Model model) {
        List<Person> personList = model.getObservablePersonList();
        if (index.isPresent()) {
            return Optional.ofNullable(personList
                    .stream()
                    .filter(person -> person.getContactIndex().equals(index.get()))
                    .findFirst().orElseGet(() -> null));
        }
        return name.map(contact -> personList.stream()
                .filter(friend -> friend.getName().toString().equals(contact))
                .findFirst())
                .orElseGet(() -> Optional.ofNullable(model.getUser()));
    }

    public Optional<ContactIndex> getIndex() {
        return index;
    }

    public Optional<String> getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ViewCommand) {
            ViewCommand otherCommand = (ViewCommand) other;
            return otherCommand.getIndex().equals(getIndex())
                    && otherCommand.getName().equals(getName());
        }
        return false;

    }
}

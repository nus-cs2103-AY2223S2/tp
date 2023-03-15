package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;


/**
 * Allows users to view full profile of their friends at the right panel.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String USAGE = "view <index> : Allows you to view the profile of the person who"
            + "has the index\n"
            + "view n/<name> : Allows you to view profile for the specific person"
            + "view : Shows your own profile instead";
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
    public CommandResult execute(Model model) throws CommandException {
        Optional<Person> person = retrievePerson(model);
        if (person.isEmpty()) {
            return new CommandResult("No such person found!", false, false,
                    model.getUser(), true);
        }
        Person contact = person.get();
        return new CommandResult(contact.toString(), false, false, contact, true);
    }

    /**
     * Retrieves either a Person by its index or name or the User itself.
     * @param model
     * @return
     */
    public Optional<Person> retrievePerson(Model model) {
        List<Person> personList = model.getFilteredPersonList();
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
        } else {
            return false;
        }
    }
}

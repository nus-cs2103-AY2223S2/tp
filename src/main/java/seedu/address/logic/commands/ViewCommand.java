package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
    private final String name;
    private final Index index;

    /**
     * Creates a View Command with the person's name.
     * @param name
     */
    public ViewCommand(String name) {
        this.name = name;
        this.index = null;
    }

    /**
     * Creates a View Command.
     */
    public ViewCommand() {
        this.name = null;
        this.index = null;
    }

    /**
     * Creates a View Command with the person's index.
     * @param index
     */
    public ViewCommand(Index index) {
        this.name = null;
        this.index = index;
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
        System.out.println(person);
        if (person.isEmpty()) {
            return new CommandResult("No such name found!", false, false,
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
        if (index != null) {
            return Optional.ofNullable(personList.get(this.index.getZeroBased()));
        } else if (name != null) {
            return personList.stream()
                    .filter(x -> x.getName().toString().equals(name))
                    .findFirst();
        } else {
            return Optional.ofNullable(model.getUser());
        }
    }
}

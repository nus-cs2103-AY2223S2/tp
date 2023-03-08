package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

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
    private String name;
    private Index index;

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
        Person person = retrievePerson(model);
        if (person == null) {
            return new CommandResult("No such name found!", false, false,
                    model.getUser(), true);
        }
        System.out.println(person);
        return new CommandResult(person.toString(), false, false, person, true);
    }

    /**
     * Retrieves either a Person by its index or name or the User itself.
     * @param model
     * @return
     */
    public Person retrievePerson(Model model) {
        List<Person> personList = model.getFilteredPersonList();
        Person person;
        if (index != null) {
            person = personList.get(this.index.getZeroBased());
        } else if (name != null) {
            List<Person> peopleWithMatchingName = new ArrayList<>();
            personList.stream()
                    .filter(x -> x.getName().toString().equals(name))
                    .forEach(friend -> {
                        peopleWithMatchingName.add(friend);
                    });
            if (peopleWithMatchingName.size() == 0) {
                person = null;
            } else {
                person = peopleWithMatchingName.get(0);
            }
        } else {
            person = model.getUser();
        }
        return person;
    }
}

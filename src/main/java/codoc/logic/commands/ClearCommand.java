package codoc.logic.commands;

import static java.util.Objects.requireNonNull;

import codoc.model.Codoc;
import codoc.model.Model;
import codoc.model.person.Person;

/**
 * Clears CoDoc.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "CoDoc has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCodoc(new Codoc());
        model.setProtagonist(Person.getDummyPerson());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

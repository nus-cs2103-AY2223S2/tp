package codoc.logic.commands;

import static codoc.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static codoc.model.Model.PREDICATE_SHOW_ALL_PERSONS_INPUT;
import static java.util.Objects.requireNonNull;

import codoc.model.Model;

/**
 * Lists all persons in CoDoc to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_PERSONS_INPUT);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

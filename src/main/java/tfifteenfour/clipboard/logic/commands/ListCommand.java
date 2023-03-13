package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import tfifteenfour.clipboard.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

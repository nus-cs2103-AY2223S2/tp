package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.model.Model;

/**
 * Lists all students in the application.
 */
//@@author cyli133-reused
//Reused from https://se-education.org/addressbook-level3/
// with minor modifications
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

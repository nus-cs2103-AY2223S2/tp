package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.internship.model.Model;

/**
 * Lists all internships in InternBuddy to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all internships";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        model.updateSelectedInternship(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

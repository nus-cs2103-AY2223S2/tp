package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_APPLICATIONS;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Lists all internship applications which are archived in the list of internship applications to the user.
 */
public class ListArchivedCommand extends Command {

    public static final String COMMAND_WORD = "list_archived";

    public static final String MESSAGE_SUCCESS = "Listed all archived applications";
    public static final String MESSAGE_NO_APPLICATIONS = "No archived applications at the moment";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ARCHIVED_APPLICATIONS);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();
        if (lastShownList.size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS);
        }
    }
}

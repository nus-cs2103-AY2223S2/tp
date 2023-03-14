package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";
    public static final String MESSAGE_NO_APPLICATIONS = "No applications at the moment";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
        List<InternshipApplication> lastShownList = model.getFilteredInternshipList();
        if (lastShownList.size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS);
        }
    }
}

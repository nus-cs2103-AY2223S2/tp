package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Reverts all deleted or cleared internship applications that are stored in the cache list for current session only.
 */
public class RevertAllCommand extends Command {

    public static final String COMMAND_WORD = "revert_all";

    public static final String MESSAGE_SUCCESS = "All cleared internship applications are reverted!";
    public static final String MESSAGE_NO_APPLICATIONS = "No applications at the moment";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<InternshipApplication> cacheList = model.getCachedInternshipList();

        if (cacheList.size() > 0) {
            model.setEmptyInternshipCacheList();
            model.addApplications(cacheList);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS);
        }
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Reverts a most recently deleted or cleared internship application stored in the cache list for current session only.
 */
public class RevertCommand extends Command {

    public static final String COMMAND_WORD = "revert";

    public static final String MESSAGE_SUCCESS = "Previous deletion of internship application is reverted: %1$s";
    public static final String MESSAGE_NO_APPLICATIONS = "No applications at the moment";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This internship application "
            + "already exists in the tracker";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<InternshipApplication> cacheList = model.getCachedInternshipList();

        if (cacheList.size() > 0) {
            InternshipApplication application = model.getAndRemoveCachedApplication();
            return hasApplication(model, application);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS);
        }
    }

    private CommandResult hasApplication(Model model, InternshipApplication application) {
        if (model.hasApplication(application)) {
            return new CommandResult(MESSAGE_DUPLICATE_APPLICATION);
        } else {
            model.addApplication(application);
            return new CommandResult(String.format(MESSAGE_SUCCESS, application));
        }
    }
}

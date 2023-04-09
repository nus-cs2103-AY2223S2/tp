package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.model.Model;

/**
 * Navigates back to homepage and lists all courses in CLIpboard to the user.
 */
public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "Back to homepage, listed all courses";

    public HomeCommand() {
        super(true);
    }

    @Override
    public CommandResult execute(Model model) {
        CurrentSelection currentSelection = model.getCurrentSelection();
        ShowAllListedPredicate.resetAllFilters(model, currentSelection);
        currentSelection.getSelectedGroup().unMarkAllSessions();
        currentSelection.getSelectedGroup().unMarkAllTasks();
        currentSelection.navigateBackToCoursePage();

        return new CommandResult(this, MESSAGE_SUCCESS, willModifyState);
    }

}

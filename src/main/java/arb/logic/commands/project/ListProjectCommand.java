package arb.logic.commands.project;

import static arb.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static arb.model.Model.PROJECT_NO_COMPARATOR;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Lists all projects in the address book to the user.
 */
public class ListProjectCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all projects";
    private static final String MAIN_COMMAND_WORD = "list-project";
    private static final String ALIAS_COMMAND_WORD = "lp";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        model.updateSortedProjectList(PROJECT_NO_COMPARATOR);
        String formattedMessage = getMessageProjectsContent(model);

        return new CommandResult(MESSAGE_SUCCESS + "\n" + formattedMessage, ListType.PROJECT);
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }

    /**
     * Returns message of contents of the project list.
     */
    public static String getMessageProjectsContent(Model model) {
        int projectSize = model.noProjectSize();
        int overdueProjects = model.noOverdueProjects();
        int doneProjects = model.noDoneProjects();
        int notDoneProjects = model.noNotDoneProjects();
        String formattedMessage = "Total number of Projects: " + projectSize + "\n"
                + "Total number of Projects OVERDUE: " + overdueProjects + "\n"
                + "Total number of Projects DONE: " + doneProjects + "\n"
                + "Total number of Projects NOT DONE: " + notDoneProjects;
        return formattedMessage;
    }

}

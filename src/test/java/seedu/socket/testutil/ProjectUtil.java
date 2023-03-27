package seedu.socket.testutil;

import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;

import seedu.socket.logic.commands.AddProjectCommand;
import seedu.socket.model.project.Project;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an addpj command string for adding the {@code project}.
     */
    public static String getAddProjectCommand(Project project) {
        return AddProjectCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + project.getName().projectName + " ");
        sb.append(PREFIX_REPO_HOST + project.getRepoHost().value + " ");
        sb.append(PREFIX_REPO_NAME + project.getRepoName().value + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().deadline + " ");
        sb.append(PREFIX_MEETING + project.getMeeting().meeting + " ");
        return sb.toString();
    }
}

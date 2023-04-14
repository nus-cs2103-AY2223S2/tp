package tfifteenfour.clipboard.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;

/**
 * Adds a group to the roster.
 */
public class AddGroupCommand extends AddCommand {
    public static final String COMMAND_TYPE_WORD = "group";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Adds a group to the selected course. "
            + "Parameters: "
            + "GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + " T15-4";

    public static final String MESSAGE_SUCCESS = "New group added in %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the course";
    public static final String MESSAGE_WRONG_PAGE = "Wrong page. Navigate to group page to add group";

    private final Group groupToAdd;

    public AddGroupCommand(Group group) {
        this.groupToAdd = group;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.GROUP_PAGE) {
            throw new CommandException(MESSAGE_WRONG_PAGE);
        }

        Course targetCourse = currentSelection.getSelectedCourse();
        if (targetCourse.hasGroup(groupToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        targetCourse.addGroup(groupToAdd);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, targetCourse, groupToAdd), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupCommand // instanceof handles nulls
                && groupToAdd.equals(((AddGroupCommand) other).groupToAdd));
    }
}

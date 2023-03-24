package tfifteenfour.clipboard.logic.commands.deleteCommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;

public class DeleteGroupCommand extends DeleteCommand {
	public static final String COMMAND_TYPE_WORD = "group";
	public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
			+ ": Deletes a specified group and all its students."
            + "Parameters: "
            + "GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + " T15-4";

    public static final String MESSAGE_SUCCESS = "New group added in %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the course";

	private final Index index;

	public DeleteGroupCommand(Index index) {
		this.index = index;
	}

	public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
		requireNonNull(model);

		if (currentSelection.getCurrentPage() != PageType.COURSE_PAGE) {
			throw new CommandException("Wrong page. Navigate to course page to add course");
		}

		Course selectedCourse = currentSelection.getSelectedCourse();
		List<Group> lastShownList = selectedCourse.getUnmodifiableGroupList();

		if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

		Group groupToDelete = lastShownList.get(index.getZeroBased());

		return new CommandResult(this, String.format(MESSAGE_SUCCESS, groupToDelete), willModifyState);
	}

	@Override
    public boolean equals(Object other) {
        return other == this;
    }
}

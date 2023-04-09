package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Changes the right-side component of Ui based on the condition of the TaskList after editing a
 * task.
 */
public class EditTaskUiCommand extends UiCommand {

    private static final String SUCCESS_MESSAGE = "";

    public EditTaskUiCommand() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(MainScreen mainScreen) {
        mainScreen.getTaskListPanel().loadTaskDetailPanel();
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Command to edit a task from the displayed list.
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

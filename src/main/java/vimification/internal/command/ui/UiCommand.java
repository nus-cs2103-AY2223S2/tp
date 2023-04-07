package vimification.internal.command.ui;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Common class for {@code Command}s that do not change the state of the application when executed.
 */
public abstract class UiCommand implements Command {

    /**
     * Executes this command.
     *
     * @param mainScreen the main screen that is shown to the user
     * @return a structure that contains relevant information about the execution of this command
     */
    public abstract CommandResult execute(MainScreen mainScreen);
}

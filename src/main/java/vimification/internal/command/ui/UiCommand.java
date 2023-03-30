package vimification.internal.command.ui;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Common class for {@code Command}s that do not change the state of the application when executed.
 */
public abstract class UiCommand implements Command {

    public abstract CommandResult execute(MainScreen mainSreen);

}

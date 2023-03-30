package vimification.internal.command.misc;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;

/**
 * Common class for {@code Command}s that do not change the state of the application when executed.
 */
public abstract class MiscCommand implements Command {

    public abstract CommandResult execute();

}

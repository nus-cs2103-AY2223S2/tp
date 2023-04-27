package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Displays the manual page.
 */
public class HelpCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "Displaying manual page.";

    /**
     * Creates a new {@code HelpCommand} instance.
     */
    public HelpCommand() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(MainScreen mainScreen) {
        mainScreen.loadRightComponent(mainScreen.getHelpManualPanel());
        return new CommandResult(SUCCESS_MESSAGE, false);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof HelpCommand;
    }
}

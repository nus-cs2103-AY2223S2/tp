package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;
import vimification.ui.ManualPanel;

/**
 * Displays the manual page.
 */
public class HelpCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "Displaying manual page.";

    /**
     * Creates a new {@code HelpCommand} instance.
     */
    public HelpCommand() {}

    @Override
    public CommandResult execute(MainScreen mainScreen) {
        ManualPanel manualPanel = new ManualPanel();
        mainScreen.loadRightComponent(manualPanel);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof HelpCommand;
    }
}

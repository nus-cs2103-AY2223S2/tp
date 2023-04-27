package vimification.internal.command.ui;

import javafx.application.Platform;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Quits the application.
 */
public class QuitCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "Closing Vimification...";

    /**
     * Creates a new {@code QuitCommand} instance.
     */
    public QuitCommand() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(MainScreen mainScreen) {
        Platform.exit();
        return new CommandResult(SUCCESS_MESSAGE, false);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof QuitCommand;
    }
}

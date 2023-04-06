package vimification.internal.command.ui;

import javafx.application.Platform;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

public class QuitCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "Closing Vimification...";

    @Override
    public CommandResult execute(MainScreen mainScreen) {
        Platform.exit();
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

package vimification.internal.command.ui;

import javafx.application.Platform;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

public class QuitCommand extends UiCommand {

    public CommandResult execute(MainScreen mainScreen) {
        Platform.exit();
        return new CommandResult("Quiting Vimification.");
    }
}

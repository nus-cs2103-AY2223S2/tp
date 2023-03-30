package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;
import vimification.ui.ManualPanel;

public class HelpCommand extends UiCommand {

    public CommandResult execute(MainScreen mainScreen) {
        ManualPanel manualPanel = new ManualPanel();
        mainScreen.loadRightComponent(manualPanel);
        return new CommandResult("");
    }
}

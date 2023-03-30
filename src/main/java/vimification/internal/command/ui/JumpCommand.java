package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

public class JumpCommand extends UiCommand {
    private int jumpIndex;

    public JumpCommand(int jumpIndex) {
        this.jumpIndex = jumpIndex;
    }

    public CommandResult execute(MainScreen mainScreen) {
        mainScreen.getTaskTabPanel().scrollToTaskIndex(jumpIndex);
        return new CommandResult("");
    }
}

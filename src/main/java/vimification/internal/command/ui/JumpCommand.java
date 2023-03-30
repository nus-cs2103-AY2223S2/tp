package vimification.internal.command.ui;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

public class JumpCommand extends UiCommand {
    private Index jumpIndex;

    public JumpCommand(Index jumpIndex) {
        this.jumpIndex = jumpIndex;
    }

    public CommandResult execute(MainScreen mainScreen) {
        mainScreen.getTaskTabPanel().scrollToTaskIndex(jumpIndex.getOneBased());
        return new CommandResult("");
    }
}

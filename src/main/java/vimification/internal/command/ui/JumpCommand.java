package vimification.internal.command.ui;

import java.util.Objects;

import vimification.common.core.Index;
import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

/**
 * Jumps to a particular task in the displayed list.
 */
public class JumpCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "";

    private final Index jumpIndex;

    /**
     * Creates a new {@code JumpCommand} instance.
     *
     * @param jumpIndex index of the task to jump to
     */
    public JumpCommand(Index jumpIndex) {
        this.jumpIndex = jumpIndex;
    }

    @Override
    public CommandResult execute(MainScreen mainScreen) {
        mainScreen.getTaskTabPanel().scrollToTaskIndex(jumpIndex.getOneBased());
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JumpCommand)) {
            return false;
        }
        JumpCommand otherCommand = (JumpCommand) other;
        return Objects.equals(jumpIndex, otherCommand.jumpIndex);
    }
}

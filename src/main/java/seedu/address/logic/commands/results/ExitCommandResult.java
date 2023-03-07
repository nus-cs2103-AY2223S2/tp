package seedu.address.logic.commands.results;

/**
 * CommandResult to be shown when executing an ExitCommand.
 */
public class ExitCommandResult extends CommandResult {

    public ExitCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExitCommandResult;
    }
}

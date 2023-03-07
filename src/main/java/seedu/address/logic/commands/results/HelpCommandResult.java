package seedu.address.logic.commands.results;

/**
 * CommandResult to be shown when executing a HelpCommand.
 */
public class HelpCommandResult extends CommandResult {

    /**
     * Constructs a {@code HelpCommandResult} with the specified fields.
     */
    public HelpCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    @Override
    public boolean isShowHelp() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof HelpCommandResult;
    }
}

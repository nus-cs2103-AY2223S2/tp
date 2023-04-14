package seedu.powercards.logic.commands.commandresult;

/**
 * Represents the result of a Clear command execution.
 */
public class ClearCommandResult extends CommandResult {

    public ClearCommandResult(String feedback) {
        super(feedback);
    }

    @Override
    public boolean isClear() {
        return true;
    }
}

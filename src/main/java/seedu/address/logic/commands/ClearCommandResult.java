package seedu.address.logic.commands;

public class ClearCommandResult extends CommandResult {

    public ClearCommandResult(String feedback) {
        super(feedback);
    }

    @Override
    public boolean isClear() {
        return true;
    }
}

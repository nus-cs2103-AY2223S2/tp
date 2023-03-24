package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.ui.SummaryWindow;

/**
 * Format full summary view for display.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SUMMARY_MESSAGE = "Opened summary window.";
    public static int SIZE;

    @Override
    public CommandResult execute(Model model) {
        this.SIZE = model.getAddressBook().size();
        SummaryWindow.setSize(this.SIZE);
        return new CommandResult(SHOWING_SUMMARY_MESSAGE, false, true, false);
    }
}

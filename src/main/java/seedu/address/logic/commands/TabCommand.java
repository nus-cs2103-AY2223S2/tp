package seedu.address.logic.commands;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.ui.tab.TabInfo;
import seedu.address.model.Model;
import seedu.address.ui.result.ResultDisplay;

/**
 * Switches between UI tabs in the application.
 */
public class TabCommand extends Command {
    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD,
                    "Switches between tabs in the application by the index of the tab.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS, "INDEX (must be a positive integer)")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD, "1");

    public static final String MESSAGE_INVALID_INDEX = "The provided tab index "
            + "should be in the range of %d to %d (inclusive).";

    public static final String MESSAGE_SUCCESS = "Switched to tab %s";
    public static final String MESSAGE_NO_CHANGE = "Already on tab %s";

    private final Index index;

    /**
     * @param index Index of the tab to switch to.
     */
    public TabCommand(Index index) {
        Objects.requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isValidTabIndex(index)) {
            int lowerBound = 1;
            int upperBound = model.getTabUtil().getTabInfoList().size();
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, lowerBound, upperBound));
        }

        TabInfo prevTab = model.getSelectedTab().get();
        model.setSelectedTab(index);
        TabInfo currTab = model.getSelectedTab().get();
        if (Objects.equals(prevTab, currTab)) {
            return new CommandResult(String.format(MESSAGE_NO_CHANGE, currTab));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, currTab));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabCommand)) {
            return false;
        }

        // state check
        TabCommand e = (TabCommand) other;
        return index.equals(e.index);
    }
}

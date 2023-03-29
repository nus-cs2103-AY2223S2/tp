package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should select & view a specific customer */
    private final Integer customerIndex;

    /** The application should select & view a specific order */
    private final Integer orderIndex;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         Integer customerIndex, Integer orderIndex) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.customerIndex = customerIndex;
        this.orderIndex = orderIndex;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * The index of the customer in the current displayed listview to display.
     * @return The index of the customer in the current listview, 0-indexed.
     */
    public Integer getCustomerIndex() {
        return customerIndex;
    }

    /**
     * The index of the order in the current displayed listview to display.
     * @return The index of the order in the current listview, 0-indexed.
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowCustomerSelection() {
        return customerIndex != null;
    }

    public boolean isShowOrderSelection() {
        return orderIndex != null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && Objects.equals(customerIndex, otherCommandResult.customerIndex)
                && Objects.equals(orderIndex, otherCommandResult.orderIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, customerIndex, orderIndex);
    }

}

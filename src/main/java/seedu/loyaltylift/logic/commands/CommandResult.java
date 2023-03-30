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

    /** The application should show the customer list */
    private final boolean showCustomerSelection;

    /** The application should show the order list */
    private final boolean showOrderSelection;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code showHelp}, and {@code exit}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false, null, null);
    }

    /**
     * Constructs a {@code CommandResult} to show the customer/order list.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showCustomerSelection, boolean showOrderSelection) {
        this(requireNonNull(feedbackToUser), showHelp, exit,
                showCustomerSelection, showOrderSelection,
                null, null);
    }

    /**
     * Constructs a {@code CommandResult} show a specific customer/order index.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         Integer customerIndex, Integer orderIndex) {
        this(requireNonNull(feedbackToUser), showHelp, exit,
                false, false,
                customerIndex, orderIndex);
    }

    /**
     * Private constructor for a {@code CommandResult} with all fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showCustomerSelection, boolean showOrderSelection,
                         Integer customerIndex, Integer orderIndex) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.customerIndex = customerIndex;
        this.orderIndex = orderIndex;
        this.showCustomerSelection = showCustomerSelection;
        this.showOrderSelection = showOrderSelection;
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

    /**
     * Returns {@code true} if the customer index is set.
     * @return {@code true} if the customer index is set.
     */
    public boolean hasCustomerIndex() {
        return customerIndex != null;
    }

    /**
     * Returns {@code false} if the order index is set.
     * @return {@code false} if the order index is set.
     */
    public boolean hasOrderIndex() {
        return orderIndex != null;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowCustomerSelection() {
        return showCustomerSelection || hasCustomerIndex();
    }

    public boolean isShowOrderSelection() {
        return showOrderSelection || hasOrderIndex();
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
                && Objects.equals(orderIndex, otherCommandResult.orderIndex)
                && Objects.equals(showCustomerSelection, otherCommandResult.showCustomerSelection)
                && Objects.equals(showOrderSelection, otherCommandResult.showOrderSelection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, customerIndex, orderIndex);
    }

}

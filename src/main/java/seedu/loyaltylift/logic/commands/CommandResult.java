package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** Represents the actions the UI should do with respect to the ListView UI components**/
    public enum ListViewGuiAction {
        LIST_CUSTOMERS_ONLY,
        LIST_ORDERS_ONLY,
        LIST_AND_SHOW_CUSTOMER,
        LIST_AND_SHOW_ORDER,
        REMOVE_INFO_FROM_VIEW,
    };

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should display customers / orders **/
    private final ListViewGuiAction listViewGuiAction;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code showHelp}, and {@code exit}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, null);
    }

    /**
     * Constructs a {@code CommandResult} list customer / orders and/or display
     * customer or order in the information panel.
     */
    public CommandResult(String feedbackToUser, ListViewGuiAction listViewGuiAction) {
        this(requireNonNull(feedbackToUser), false, false, listViewGuiAction);
    }

    /**
     * Private constructor for a {@code CommandResult} with all fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         ListViewGuiAction listViewGuiAction) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.listViewGuiAction = listViewGuiAction;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }


    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * Returns true if UI should put the customer listview in focus
     */
    public boolean isShowCustomerList() {
        return listViewGuiAction == ListViewGuiAction.LIST_CUSTOMERS_ONLY
                || listViewGuiAction == ListViewGuiAction.LIST_AND_SHOW_CUSTOMER;
    }

    /**
     * Returns true if UI should put the order listview in focus
     */
    public boolean isShowOrderList() {
        return listViewGuiAction == ListViewGuiAction.LIST_ORDERS_ONLY
                || listViewGuiAction == ListViewGuiAction.LIST_AND_SHOW_ORDER;
    }

    /**
     * Returns true if UI should display a customer in the information panel
     */
    public boolean isShowCustomerInfo() {
        return listViewGuiAction == ListViewGuiAction.LIST_AND_SHOW_CUSTOMER;
    }

    /**
     * Returns true if UI should display an order in the information panel
     */
    public boolean isShowOrderInfo() {
        return listViewGuiAction == ListViewGuiAction.LIST_AND_SHOW_ORDER;
    }

    /**
     * Returns true if UI should clear the information panel
     */
    public boolean isClearInfoView() {
        return listViewGuiAction == ListViewGuiAction.REMOVE_INFO_FROM_VIEW;
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
                && Objects.equals(listViewGuiAction, otherCommandResult.listViewGuiAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, listViewGuiAction);
    }

}

package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.fitbook.model.client.Client;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;
    private final boolean showRoutine;
    private final boolean showGraph;

    /** The application should exit. */
    private final boolean exit;
    private final boolean view;
    private final Client clientToView;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, Client clientToView,
                         boolean showHelp, boolean exit, boolean view, boolean showRoutine, boolean showGraph) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.view = view;
        this.clientToView = clientToView;
        this.showRoutine = showRoutine;
        this.showGraph = showGraph;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false,
                false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowRoutine() {
        return showRoutine;
    }
    public boolean isShowGraph() {
        return showGraph;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isView() {
        return view;
    }

    public Client getClientToView() {
        return clientToView;
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
                && view == otherCommandResult.view
                && showRoutine == otherCommandResult.showRoutine
                && clientToView == otherCommandResult.clientToView;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, view);
    }

}

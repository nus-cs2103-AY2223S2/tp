package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.client.Client;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final Client targetClient;

    /**
     * Selects a particular client
     */
    private final boolean select;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, Client targetClient, boolean select, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.targetClient = targetClient;
        this.select = select;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean select, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.targetClient = null;
        this.select = select;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code targetClient},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Client targetClient) {
        this(feedbackToUser, targetClient, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        if (targetClient == null) {
            return feedbackToUser;
        }
        return String.format(feedbackToUser, targetClient);
    }

    public Client getTargetClient() {
        return targetClient;
    }

    public boolean isSelect() {
        return select;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
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
        boolean isEqualTargetClients;

        // handles case where targetClient is null
        if (targetClient == null) {
            isEqualTargetClients = otherCommandResult.targetClient == null;
        } else {
            isEqualTargetClients = targetClient.equals(otherCommandResult.targetClient);
        }

        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isEqualTargetClients
                && select == otherCommandResult.select
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, targetClient, select, showHelp, exit);
    }

}

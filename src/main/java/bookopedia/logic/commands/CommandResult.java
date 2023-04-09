package bookopedia.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import bookopedia.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;
    private final boolean view;
    private final Person person;
    private final int id;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean view, Person person, int id) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.view = view;
        this.person = person;
        this.id = id;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * specified {@code view} and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean view, Person person, int id) {
        this(feedbackToUser, false, false, view, person, id);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * except {@code view}, {@code person} and {@code id}.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, null, -1);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, null, -1);
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
    public boolean isView() {
        return view;
    }

    public Person getPerson() {
        return person;
    }

    public int getId() {
        return id;
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
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}

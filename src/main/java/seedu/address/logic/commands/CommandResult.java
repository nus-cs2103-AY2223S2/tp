package seedu.address.logic.commands;

import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.Specialty;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** This command interacts directly with the GUI */
    private final boolean hasGuiInteraction;
    /** This command interacts directly with the GUI */
    private final Optional<Doctor> selectedDoctor;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean hasGuiInteraction) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.hasGuiInteraction = hasGuiInteraction;
        this.selectedDoctor = Optional.empty();
    }

    /**
     * Constructs a {@code CommandResult} with selected doctor.
     */
    public CommandResult(String feedbackToUser, boolean hasGuiInteraction, Doctor selectedDoctor) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.hasGuiInteraction = hasGuiInteraction;
        this.selectedDoctor = Optional.ofNullable(selectedDoctor);
    }

    /**
     * Constructs a {@code CommandResult} with the specified arguments,
     * and {@code hasGuiInteraction} set to its default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
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

    public boolean hasGuiInteraction() {
        return this.hasGuiInteraction;
    }
    public Optional<Doctor> getSelectedDoctor() {
        return this.selectedDoctor;
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
                && hasGuiInteraction == otherCommandResult.hasGuiInteraction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, hasGuiInteraction);
    }

}

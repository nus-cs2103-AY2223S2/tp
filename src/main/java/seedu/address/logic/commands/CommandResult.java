package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

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

    /** Doctor selected by user through command */
    private final Optional<Doctor> selectedDoctor;

    /** Patient selected by user through command */
    private final Optional<Patient> selectedPatient;

    /**
     * Constructs a {@code CommandResult} with all fields specified.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean hasGuiInteraction, Doctor selectedDoctor, Patient selectedPatient) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.hasGuiInteraction = hasGuiInteraction;
        this.selectedDoctor = Optional.ofNullable(selectedDoctor);
        this.selectedPatient = Optional.ofNullable(selectedPatient);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean hasGuiInteraction) {
        this(feedbackToUser, showHelp, exit, hasGuiInteraction, null, null);
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

    /**
     * Constructs a {@code CommandResult} with selected doctor.
     */
    public CommandResult(String feedbackToUser, boolean hasGuiInteraction, Doctor selectedDoctor) {
        this(feedbackToUser, false, false, hasGuiInteraction, selectedDoctor, null);
    }

    /**
     * Constructs a {@code CommandResult} with selected patient.
     */
    public CommandResult(String feedbackToUser, boolean hasGuiInteraction, Patient selectedPatient) {
        this(feedbackToUser, false, false, hasGuiInteraction, null, selectedPatient);
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

    public boolean hasSelectedDoctor() {
        return this.selectedDoctor.isPresent();
    }

    public boolean hasSelectedPatient() {
        return this.selectedPatient.isPresent();
    }

    public Optional<Doctor> getSelectedDoctor() {
        return this.selectedDoctor;
    }

    public Optional<Patient> getSelectedPatient() {
        return this.selectedPatient;
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

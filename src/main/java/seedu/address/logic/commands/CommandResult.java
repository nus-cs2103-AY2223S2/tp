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

    /** Feedback to be shown to the user through CLI. */
    private final String cliFeedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean shouldShowHelp;

    /** The application should exit. */
    private final boolean shouldExit;

    /** This command interacts directly with the GUI */
    private final boolean hasGuiInteraction;

    /** Doctor selected by user through command */
    private final Optional<Doctor> selectedDoctor;

    /** Patient selected by user through command */
    private final Optional<Patient> selectedPatient;

    /**
     * Constructs a {@code CommandResult} with all fields specified.
     */
    public CommandResult(String cliFeedbackToUser, boolean shouldShowHelp, boolean shouldExit,
                         boolean hasGuiInteraction, Doctor selectedDoctor, Patient selectedPatient) {
        this.cliFeedbackToUser = requireNonNull(cliFeedbackToUser);
        this.shouldShowHelp = shouldShowHelp;
        this.shouldExit = shouldExit;
        this.hasGuiInteraction = hasGuiInteraction;
        this.selectedDoctor = Optional.ofNullable(selectedDoctor);
        this.selectedPatient = Optional.ofNullable(selectedPatient);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String cliFeedbackToUser, boolean shouldShowHelp,
                         boolean shouldExit, boolean hasGuiInteraction) {
        this(cliFeedbackToUser, shouldShowHelp, shouldExit,
                hasGuiInteraction, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified arguments,
     * and {@code hasGuiInteraction} set to its default value.
     */
    public CommandResult(String cliFeedbackToUser, boolean shouldShowHelp, boolean shouldExit) {
        this(cliFeedbackToUser, shouldShowHelp, shouldExit, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String cliFeedbackToUser) {
        this(cliFeedbackToUser, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with selected doctor.
     */
    public CommandResult(String cliFeedbackToUser, boolean hasGuiInteraction,
                         Doctor selectedDoctor) {
        this(cliFeedbackToUser, false, false,
                hasGuiInteraction, selectedDoctor, null);
    }

    /**
     * Constructs a {@code CommandResult} with selected patient.
     */
    public CommandResult(String cliFeedbackToUser, boolean hasGuiInteraction,
                         Patient selectedPatient) {
        this(cliFeedbackToUser, false, false,
                hasGuiInteraction, null, selectedPatient);
    }


    public String getCliFeedbackToUser() {
        return cliFeedbackToUser;
    }

    public boolean shouldShowHelp() {
        return shouldShowHelp;
    }

    public boolean shouldExit() {
        return shouldExit;
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
        return cliFeedbackToUser.equals(otherCommandResult.cliFeedbackToUser)
                && shouldShowHelp == otherCommandResult.shouldShowHelp
                && shouldExit == otherCommandResult.shouldExit
                && hasGuiInteraction == otherCommandResult.hasGuiInteraction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliFeedbackToUser, shouldShowHelp, shouldExit,
                hasGuiInteraction);
    }

}

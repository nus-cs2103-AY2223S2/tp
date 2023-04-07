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
    private String cliFeedbackToUser = "";

    /** Help information should be shown to the user. */
    private boolean shouldShowHelp = false;

    /** The application should exit. */
    private boolean shouldExit = false;

    /** This command interacts directly with the GUI */
    private boolean hasGuiInteraction = false;

    /** Doctor selected by user through command. This value can be null */
    private Doctor selectedDoctor = null;

    /** Patient selected by user through command. This value can be null */
    private Patient selectedPatient = null;

    /**
     * Constructs a {@code CommandResult} with the specified arguments,
     * and other fields set to its default value.
     */
    public CommandResult(String cliFeedbackToUser, boolean shouldShowHelp, boolean shouldExit) {
        this.cliFeedbackToUser = requireNonNull(cliFeedbackToUser);
        this.shouldShowHelp = shouldShowHelp;
        this.shouldExit = shouldExit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String cliFeedbackToUser) {
        this.cliFeedbackToUser = requireNonNull(cliFeedbackToUser);
    }

    /**
     * Constructs a {@code CommandResult} with selected doctor.
     */
    public CommandResult(String cliFeedbackToUser,
                         Doctor selectedDoctor) {
        this.cliFeedbackToUser = requireNonNull(cliFeedbackToUser);
        this.selectedDoctor = requireNonNull(selectedDoctor);
        this.hasGuiInteraction = true;
    }

    /**
     * Constructs a {@code CommandResult} with selected patient.
     */
    public CommandResult(String cliFeedbackToUser,
                         Patient selectedPatient) {
        this.cliFeedbackToUser = requireNonNull(cliFeedbackToUser);
        this.selectedPatient = requireNonNull(selectedPatient);
        this.hasGuiInteraction = true;
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

    public Optional<Doctor> getSelectedDoctor() {
        return Optional.ofNullable(selectedDoctor);
    }

    public Optional<Patient> getSelectedPatient() {
        return Optional.ofNullable(selectedPatient);
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

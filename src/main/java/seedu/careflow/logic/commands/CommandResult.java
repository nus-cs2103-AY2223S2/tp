package seedu.careflow.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.patient.Patient;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private String feedbackToUser;

    /** Help information should be shown to the user. */
    private boolean showHelp;

    /** The application should exit. */
    private boolean exit;

    private boolean displayPatient;

    private boolean displayDrug;

    private Patient selectedPatient;

    private Drug selectedDrug;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.displayPatient = false;
        this.displayDrug = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code selectedPatient, feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(Patient selectedPatient, String feedbackToUser) {
        this.selectedPatient = selectedPatient;
        this.displayPatient = true;
        this.feedbackToUser = feedbackToUser;
        this.displayDrug = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code selectedDrug, feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(Drug selectedDrug, String feedbackToUser) {
        this.selectedDrug = selectedDrug;
        this.displayDrug = true;
        this.feedbackToUser = feedbackToUser;
        this.displayPatient = false;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isDisplayPatient() {
        return displayPatient;
    }

    public boolean isDisplayDrug() {
        return displayDrug;
    }

    public Patient getSelectedPatient() {
        return this.selectedPatient;
    }

    public Drug getSelectedDrug() {
        return this.selectedDrug;
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
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}

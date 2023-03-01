package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import javafx.collections.ObservableList;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Status;

/**
 * Lists all applicants in HMHero to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static String MESSAGE_SUCCESS_FORMAT =
            "Listed all applicants\nTotal Applicants: %d\nApplied: %d\n" +
                    "Shortlisted: %d\nAccepted: %d\nRejected: %d";

    /**
     * Returns the list of all applicants in HMHero as well as the breakdown of
     * the number of applicants in each application stages.
     *
     * @param updatedModel updated model of applicants
     */
    public String getSuccessMessage(Model updatedModel) {
        return String.format(MESSAGE_SUCCESS_FORMAT, getNumApplicants(updatedModel), getNumApplied(updatedModel),
                getNumShortlisted(updatedModel), getNumAccepted(updatedModel), getNumRejected(updatedModel)));
    }

    /**
     * Returns the number of applicants in total
     */
    private int getNumApplicants(Model updatedModel) {
        ObservableList<Applicant> applicants = updatedModel.getFilteredPersonList();
        return applicants.size();
    }

    /**
     * Returns the number of applicants whose status is "Applied"
     */
    private int getNumApplied(Model updatedModel) {
        ObservableList<Applicant> applicants = updatedModel.getFilteredPersonList();
        int numApplied = 0;
        for (Applicant applicant : applicants) {
            if (applicant.getStatus().equals(Status.APPLIED)) {
                numApplied++;
            }
        }
        return numApplied;
    }

    /**
     * Returns the number of applicants whose status is "Shortlisted"
     */
    private int getNumShortlisted(Model updatedModel) {
        ObservableList<Applicant> applicants = updatedModel.getFilteredPersonList();
        int numShortlisted = 0;
        for (Applicant applicant : applicants) {
            if (applicant.getStatus().equals(Status.SHORTLISTED)) {
                numShortlisted++;
            }
        }
        return numShortlisted;
    }

    /**
     * Returns the number of applicants whose status is "Accepted"
     */
    private int getNumAccepted(Model updatedModel) {
        ObservableList<Applicant> applicants = updatedModel.getFilteredPersonList();
        int numAccepted = 0;
        for (Applicant applicant : applicants) {
            if (applicant.getStatus().equals(Status.ACCCEPTED)) {
                numAccepted++;
            }
        }
        return numAccepted;
    }

    private int getNumRejected(Model updatedModel) {
        ObservableList<Applicant> applicants = updatedModel.getFilteredPersonList();
        int numRejected = 0;
        for (Applicant applicant : applicants) {
            if (applicant.getStatus().equals(Status.REJECTED)) {
                numRejected++;
            }
        }
        return numRejected;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        String successMessage = getSuccessMessage(model);
        return new CommandResult(successMessage);
    }
}

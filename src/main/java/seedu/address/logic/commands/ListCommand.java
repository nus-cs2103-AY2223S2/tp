package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Lists all applicants in HMHero to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_FORMAT =
            "Listed all applicants\nTotal Applicants: %d\nApplied: %d\n"
                    + "Shortlisted: %d\nAccepted: %d\nRejected: %d";

    /**
     * Returns the list of all applicants in HMHero as well as the breakdown of
     * the number of applicants in each application stages.
     *
     * @param model model of all applicants
     */
    public String getSuccessMessage(Model model) {
        model.sortFilteredPersonList((p1, p2) -> 0); //revert back to original ordering
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return String.format(MESSAGE_SUCCESS_FORMAT, getNumApplicants(model), getNumApplied(model),
                getNumShortlisted(model), getNumAccepted(model), getNumRejected(model));
    }

    /**
     * Returns the number of applicants in total
     */
    private int getNumApplicants(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        return applicants.size();
    }

    /**
     * Returns the number of applicants whose status is "Applied"
     */
    private int getNumApplied(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        int numApplied = 0;
        for (Person applicant : applicants) {
            if (applicant.getStatus().equals(Status.APPLIED)) {
                numApplied++;
            }
        }
        return numApplied;
    }

    /**
     * Returns the number of applicants whose status is "Shortlisted"
     */
    private int getNumShortlisted(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        int numShortlisted = 0;
        for (Person applicant : applicants) {
            if (applicant.getStatus().equals(Status.SHORTLISTED)) {
                numShortlisted++;
            }
        }
        return numShortlisted;
    }

    /**
     * Returns the number of applicants whose status is "Accepted"
     */
    private int getNumAccepted(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        int numAccepted = 0;
        for (Person applicant : applicants) {
            if (applicant.getStatus().equals(Status.ACCEPTED)) {
                numAccepted++;
            }
        }
        return numAccepted;
    }

    private int getNumRejected(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        int numRejected = 0;
        for (Person applicant : applicants) {
            if (applicant.getStatus().equals(Status.REJECTED)) {
                numRejected++;
            }
        }
        return numRejected;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(getSuccessMessage(model));
    }
}

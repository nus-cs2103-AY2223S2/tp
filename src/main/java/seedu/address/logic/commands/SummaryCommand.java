package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Produces a summary statistic of lead time between applicants applying
 * to getting an interview to the user.
 */
public class SummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";
    public static final String MESSAGE_SUCCESS_FORMAT =
            "Plotted summary of time to interview for applicants\n%.2f days";

    /**
     * Returns the average 'time to interview' for applicants in HMHero.
     * @param model model of all applicants
     */
    public String getSuccessMessage(Model model) {
        model.sortFilteredPersonList((p1, p2) -> 0); //revert back to original ordering
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // get average time to interview for applicants
        return String.format(MESSAGE_SUCCESS_FORMAT, getAverageTimeToInterview(model));
    }

    /**
     * Returns the average `time to interview` for applicants that got an interview.
     * Unit of time is `Days`
     * @param model model of all applicants.
     */
    public float getAverageTimeToInterview(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        long averageTimeToInterview = 0;
        int numApplicantsWithInterview = 0;
        for (Person applicant : applicants) {
            if (applicant.getInterviewDateTime().isEmpty() != true) {
                LocalDateTime interviewDateTime = applicant.getInterviewDateTime().get().getDateTime();
                Duration duration = Duration.between(interviewDateTime,
                        applicant.getApplicationDateTime());
                averageTimeToInterview += duration.toDays();
                numApplicantsWithInterview++;
            }
        }
        if (numApplicantsWithInterview == 0) {
            return 0;
        }
        return averageTimeToInterview / numApplicantsWithInterview;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(getSuccessMessage(model));
    }
}

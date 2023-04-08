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
            "Here are some summary statistics for your hiring cycle\n\n"
            + "Average days-to-interview (rounded down nearest day): %.2f days\n"
            + "Percentage of applicants with interview: %.1f";

    public static final String MESSAGE_NO_INTERVIEW =
            "No summary statistic is available at the moment because there are no scheduled interviews currently!";


    /**
     * Returns the average 'time to interview' for applicants in HMHero.
     * @param model model of all applicants
     */
    public String getSuccessMessage(Model model) {
        model.sortFilteredPersonList((p1, p2) -> 0); //revert back to original ordering
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        float[] values = getAverageTimeToInterview(model);
        if (values[0] == 0) {
            return MESSAGE_NO_INTERVIEW;
        }
        return String.format(MESSAGE_SUCCESS_FORMAT, values[0], values[1]) + "%";
    }

    /**
     * Returns the mean `time-to-interview` and percentage of applicants that got an interview.
     * @param model model of all applicants.
     */
    public float[] getAverageTimeToInterview(Model model) {
        ObservableList<Person> applicants = model.getFilteredPersonList();
        long sumTimeTaken = 0;
        int numApplicantsWithInterview = 0;
        int size = applicants.size();
        for (Person applicant : applicants) {
            if (applicant.getInterviewDateTime().isEmpty() != true) {
                LocalDateTime interviewDateTime = applicant.getInterviewDateTime().get().getDateTime();
                Duration duration = Duration.between(applicant.getApplicationDateTime().getApplicationDateTime(),
                        interviewDateTime);
                sumTimeTaken += duration.toDays();
                numApplicantsWithInterview++;
            }
        }
        if (numApplicantsWithInterview == 0) {
            return new float[] {0, 0};
        }
        float averageTimeTaken = sumTimeTaken / numApplicantsWithInterview;
        float percentageApplicantsGotInterview = (numApplicantsWithInterview / (float) size) * 100;
        return new float[] {averageTimeTaken, percentageApplicantsGotInterview};
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(getSuccessMessage(model));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.util.SortByInterviewDate;



/**
 * List all applicants that are going to have interview in three days
 */
public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";
    public static final String MESSAGE_SUCCESS_FORMAT = "Listed all applicants that going to have "
            + "interview within three days!";
    public static final Predicate<Person> INTERVIEW_IN_THREE_DAYS_PREDICATE =
            person -> (person.getStatus() == Status.SHORTLISTED
                    && person.getInterviewDateTime().get().isWithinThreeDays() == true);

    /**
     * Return a filtered list of applicants that are going to have interview within three days.
     */
    public String getSuccessMessage(Model model) {
        model.updateFilteredPersonList(INTERVIEW_IN_THREE_DAYS_PREDICATE);
        model.sortFilteredPersonList(new SortByInterviewDate());
        return MESSAGE_SUCCESS_FORMAT;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(getSuccessMessage(model));
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.util.SortByInterviewDate;


public class InterviewCommand extends Command {
    public static final String COMMAND_WORD = "interview";

    public static final Predicate<Person> shortlistedPredicate = person -> (person.getStatus() == Status.SHORTLISTED);

    public static final String MESSAGE_SUCCESS_FORMAT = "Listed all shortlisted applicants!";

    /**
     * Returns a filtered list of applicants with SHORTLISTED status and sorts by earliest interview date
     */
    public String getSuccessMessage(Model model) {
        model.updateFilteredPersonList(shortlistedPredicate);
        model.sortFilteredPersonList(new SortByInterviewDate());
        return MESSAGE_SUCCESS_FORMAT;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(getSuccessMessage(model));
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InterviewDateTime;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Advances an applicant in HMHero.
 */
public class AdvanceCommand extends Command {
    public static final String COMMAND_WORD = "advance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Advance an applicant in HMHero. "
            + "If applicant's current status is APPLIED, please provide an interview date time\n\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_DATETIME + "INTERVIEW DATETIME]\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_DATETIME + "08-03-2023 18:00";

    public static final String MESSAGE_ADVANCE_PERSON_SUCCESS = "Advanced Applicant: %s\n\n"
            + "Old Status: %s\nNew Status: %s";

    public static final String MESSAGE_PERSON_CANNOT_BE_ADVANCED = "%s's status cannot be advanced!";

    private final NamePhoneNumberPredicate predicate;
    private final Optional<InterviewDateTime> interviewDateTime;

    /**
     * Creates an AdvanceCommand to advance the specified {@code Person}
     *
     * @param predicate predicate to check the name and phone of applicant provided by user
     * @param interviewDateTime interviewDateTime provided by the user (if any)
     */
    public AdvanceCommand(NamePhoneNumberPredicate predicate, Optional<InterviewDateTime> interviewDateTime) {
        this.predicate = predicate;
        this.interviewDateTime = interviewDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        List<Person> personList = model.getFilteredPersonList();
        assert personList.size() <= 1;

        if (personList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
        }

        Person personToAdvance = personList.get(0);

        checkAdvanceApplicant(personToAdvance);
        checkValidInterviewDateInput(model, personToAdvance, this.interviewDateTime);
        Person advancedPerson = createAdvancedPerson(personToAdvance, this.interviewDateTime);
        model.setPerson(personToAdvance, advancedPerson);
        model.refreshListWithPredicate(predicate);
        return new CommandResult(String.format(MESSAGE_ADVANCE_PERSON_SUCCESS,
                personToAdvance.getName(), personToAdvance.getStatus(), advancedPerson.getStatus()));
    }

    /**
     * Checks whether the interview date-time input is needed or not needed
     *
     * @param personToAdvance the applicant to be advanced
     * @param interviewDateTime the interview date-time input
     * @throws CommandException if there is a mismatch of whether interview date-time is required
     */
    private void checkValidInterviewDateInput(Model model, Person personToAdvance,
            Optional<InterviewDateTime> interviewDateTime) throws CommandException {
        model.refreshListWithPredicate(predicate);
        // interviewDateTime is only required if the applicant's status is Applied
        if (interviewDateTime.isPresent()) {
            if (!isValidForAdvanceWithDateTime(model, personToAdvance, interviewDateTime.get())) {
                throw new CommandException(Messages.MESSAGE_INTERVIEW_DATETIME_NOT_REQUIRED);
            } else if (!isAfterApplicationDateTime(personToAdvance, interviewDateTime.get())) {
                throw new CommandException(String.format(Messages.MESSAGE_INTERVIEW_BEFORE_APPLICATION,
                        personToAdvance.getName(), personToAdvance.getApplicationDateTime()));
            }
        } else {
            if (personToAdvance.getStatus() == Status.APPLIED) {
                throw new CommandException(Messages.MESSAGE_INTERVIEW_DATETIME_IS_REQUIRED);
            }
        }
    }

    /**
     * Checks whether applicant's interviewDateTIme is after applicationDateTime
     * @param personToAdvance Applicant that user wants to advance to the next stage in application cycle
     * @param interviewDateTime date and time of the interview for the applicant
     * @return true if interviewDateTime is after applicationDateTime
     */
    private boolean isAfterApplicationDateTime(Person personToAdvance, InterviewDateTime interviewDateTime) {
        LocalDateTime applicationDateTime = personToAdvance.getApplicationDateTime().getApplicationDateTime();
        return interviewDateTime.getDateTime().isAfter(applicationDateTime);
    }

    /**
     * Checks whether applicant that user wants to advance is of status {@code APPLIED}
     * Checks whether the interview date time has any clashes with current applicants who have the
     * status {@code SHORTLISTED}
     *
     * @param model current state of the model
     * @param personToAdvance Applicant that user wants to advance to the next stage in application cycle
     * @param interviewDateTime date and time of the interview for the applicant
     */
    private boolean isValidForAdvanceWithDateTime(Model model, Person personToAdvance,
            InterviewDateTime interviewDateTime) throws CommandException {
        return personToAdvance.getStatus() == Status.APPLIED
                && !isDuplicateDateTime(model, personToAdvance, interviewDateTime);
    }


    /**
     * Checks with the current list of Shortlisted applicants to see if there are any clashing interview date timings.
     *
     * @param interviewDateTime new date and time of the interview for the applicant
     */
    private boolean isDuplicateDateTime(
            Model model, Person personToAdvance, InterviewDateTime interviewDateTime) throws CommandException {
        Predicate<Person> shortlistedPredicate = person -> (person.getStatus() == Status.SHORTLISTED);
        ObservableList<Person> shortlistedApplicants =
                model.getAddressBook().getPersonList().filtered(shortlistedPredicate);
        for (Person applicant : shortlistedApplicants) {
            if (applicant.getInterviewDateTime().get().equals(interviewDateTime)) {
                Predicate<Person> sameInterviewDTPredicate =
                        person -> (person.equals(applicant) || person.equals(personToAdvance));
                model.refreshListWithPredicate(sameInterviewDTPredicate);
                throw new CommandException(String.format(MESSAGE_DUPLICATE_INTERVIEW_DATE, applicant.getName()));
            }
        }
        return false;
    }

    /**
     * Checks whether applicant can be advanced
     */
    private void checkAdvanceApplicant(Person personToAdvance) throws CommandException {
        Status status = personToAdvance.getStatus();
        if (status.equals(Status.ACCEPTED) || status.equals(Status.REJECTED)) {
            throw new CommandException(String.format(MESSAGE_PERSON_CANNOT_BE_ADVANCED,
                    personToAdvance.getName().fullName));
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToAdvance}
     */
    private static Person createAdvancedPerson(Person personToAdvance, Optional<InterviewDateTime> interviewDateTime) {
        assert personToAdvance != null;

        return personToAdvance.advancePerson(interviewDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdvanceCommand // instanceof handles nulls
                && predicate.equals(((AdvanceCommand) other).predicate)); // state check
    }
}

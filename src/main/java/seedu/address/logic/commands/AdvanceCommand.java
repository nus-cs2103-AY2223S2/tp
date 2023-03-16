package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_INTERVIEWDATE;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_CANNOT_BE_ADVANCED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.person.*;

/**
 * Advances an applicant in HMHero.
 */
public class AdvanceCommand extends Command {
    public static final String COMMAND_WORD = "advance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Advance an applicant in HMHero.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_DATETIME + "INTERVIEW DATETIME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_DATETIME + "08-03-2023 18:00";

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
        Person advancedPerson;

        /* this if-statement will check whether the applicant can be advanced and also whether
        the interview datetime input is valid.
        If any exception occurs along the way, then CommandException will be thrown */
        if (canAdvanceApplicant(model, personToAdvance)
                && isValidInterviewDateInput(model, personToAdvance, this.interviewDateTime)) {
            advancedPerson = createAdvancedPerson(personToAdvance, this.interviewDateTime);
            model.setPerson(personToAdvance, advancedPerson);
        }
        return new CommandResult("Successfully advanced " + personToAdvance.getName());
    }

    /**
     * Checks whether the interview date-time input is needed or not needed
     *
     * @param personToAdvance the applicant to be advanced
     * @param interviewDateTime the interview date-time input
     * @throws CommandException if there is a mismatch of whether interview date-time is required
     */
    private boolean isValidInterviewDateInput(Model model, Person personToAdvance,
            Optional<InterviewDateTime> interviewDateTime) throws CommandException {

        // interviewDateTime is only required if the applicant's status is Applied
        if (interviewDateTime.isPresent()) {
            if (isValidForAdvanceWithDateTime(model, personToAdvance, interviewDateTime.get())) {
                return true;
            } else {
                throw new CommandException(Messages.MESSAGE_INTERVIEW_DATETIME_NOT_REQUIRED);
            }
        } else {
            if (personToAdvance.getStatus() != Status.APPLIED) {
                return true;
            } else {
                throw new CommandException(Messages.MESSAGE_INTERVIEW_DATETIME_IS_REQUIRED);
            }
        }
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
        return personToAdvance.getStatus() == Status.APPLIED && !isDuplicateDateTime(model, interviewDateTime);
    }


    /**
     * Checks with the current list of Shortlisted applicants to see if there are any clashing interview date timings.
     *
     * @param interviewDateTime new date and time of the interview for the applicant
     */
    private boolean isDuplicateDateTime(Model model, InterviewDateTime interviewDateTime) throws CommandException{
        Predicate<Person> shortlistedPredicate = person -> (person.getStatus() == Status.SHORTLISTED);
        model.refreshListWithPredicate(shortlistedPredicate);
        ObservableList<Person> shortlistedApplicants = model.getFilteredPersonList();
        for (Person applicant : shortlistedApplicants) {
            if (applicant.getInterviewDateTime().get().equals(interviewDateTime)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_INTERVIEWDATE, applicant.getName()));
            }
        }
        return false;
    }

    /**
     * Checks whether applicant can be advanced
     * @throws CommandException if applicant cannot be advanced any further in the application cycle
     */
    private boolean canAdvanceApplicant(Model model, Person personToAdvance) throws CommandException {
        if (!model.advancePerson(personToAdvance)) {
            throw new CommandException(String.format(MESSAGE_PERSON_CANNOT_BE_ADVANCED,
                    personToAdvance.getName().fullName));
        }
        return true;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToAdvance}
     */
    private static Person createAdvancedPerson(Person personToAdvance, Optional<InterviewDateTime> interviewDateTime) {
        assert personToAdvance != null;

        Name updatedName = personToAdvance.getName();
        Phone updatedPhone = personToAdvance.getPhone();
        Email updatedEmail = personToAdvance.getEmail();
        Address updatedAddress = personToAdvance.getAddress();
        Status updatedStatus = personToAdvance.getStatus(); //User not allowed to edit applicant status directly
        Set<Note> updatedNotes = personToAdvance.getNotes();

        Person advancedPerson = new Person(updatedName, updatedPhone, updatedEmail,
                updatedAddress, updatedStatus, interviewDateTime, updatedNotes);
        advancedPerson.advancePerson();
        return advancedPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdvanceCommand // instanceof handles nulls
                && predicate.equals(((AdvanceCommand) other).predicate)); // state check
    }
}

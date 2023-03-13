package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_CANNOT_BE_ADVANCED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Optional;

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

        /* this if-statement will check whether the applicant can be advanced and also whether
        the interview datetime input is valid.
        If any exception occurs along the way, then CommandException will be thrown */
        if (canAdvanceApplicant(model, personToAdvance)
                && isValidInterviewDateInput(personToAdvance, this.interviewDateTime)) {
            advanceApplicant(model, personToAdvance, this.interviewDateTime);
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
    private boolean isValidInterviewDateInput(Person personToAdvance, Optional<InterviewDateTime> interviewDateTime)
            throws CommandException {
        // interviewDateTime is only required if the applicant's status is Applied
        if (interviewDateTime.isPresent()) {
            if (personToAdvance.getStatus() == Status.APPLIED) {
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

    private void advanceApplicant(Model model, Person personToAdvance, Optional<InterviewDateTime> interviewDateTime) {
        personToAdvance.setInterviewDateTime(interviewDateTime);
        personToAdvance.advancePerson();
        model.refreshListWithPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdvanceCommand // instanceof handles nulls
                && predicate.equals(((AdvanceCommand) other).predicate)); // state check
    }
}

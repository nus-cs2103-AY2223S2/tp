package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InterviewDateTime;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;

/**
 * Advances an applicant in HMHero.
 */
public class AdvanceCommand extends Command {
    public static final String COMMAND_WORD = "advance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Advance an applicant in HMHero.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_DATETIME + "08-03-2023 18:00";

    private final NamePhoneNumberPredicate predicate;
    private final InterviewDateTime interviewDateTime;

    /**
     * Creates an AdvanceCommand to advance the specified {@code Person}
     */
    public AdvanceCommand(NamePhoneNumberPredicate predicate, InterviewDateTime interviewDateTime) {
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
        // Check if the interviewDateTime is nonNull else throw CommandException
        // See if we want to provide the d/ field for all advance commands
        if (interviewDateTime == null) {
            throw new CommandException("No interview datetime provided!");
        }

        Person personToAdvance = personList.get(0);
        if (model.advancePerson(personToAdvance)) {
            model.refreshListWithPredicate(predicate);
            personToAdvance.setInterviewDateTime(interviewDateTime);
            return new CommandResult("Successfully advanced " + personToAdvance.getName().fullName);
        } else {
            return new CommandResult(personToAdvance.getName().fullName + " cannot be advanced!");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdvanceCommand // instanceof handles nulls
                && predicate.equals(((AdvanceCommand) other).predicate)); // state check
    }
}

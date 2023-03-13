package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_CANNOT_BE_REJECTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;

/**
 * Rejects an applicant in HMHero.
 */
public class RejectCommand extends Command {
    public static final String COMMAND_WORD = "reject";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reject an applicant in HMHero.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432";

    private final NamePhoneNumberPredicate predicate;

    /**
     * Creates an RejectCommand to reject the specified {@code Person}
     */
    public RejectCommand(NamePhoneNumberPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> personList = model.getFilteredPersonList();
        assert personList.size() <= 1;

        if (personList.isEmpty()) {
            return new CommandResult(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
        }

        Person personToReject = personList.get(0);

        /* this if-statement will check whether the applicant can be rejected.
        If applicant cannot be rejected, CommandException will be thrown */
        if (canRejectApplicant(model, personToReject)) {
            rejectApplicant(model, personToReject);
        }
        return new CommandResult("Successfully rejected " + personToReject.getName());
    }

    /**
     * Checks whether applicant can be rejected
     * @throws CommandException if applicant is already rejected
     */
    private boolean canRejectApplicant(Model model, Person personToReject) throws CommandException {
        if (!model.rejectPerson(personToReject)) {
            throw new CommandException(String.format(MESSAGE_PERSON_CANNOT_BE_REJECTED,
                    personToReject.getName().fullName));
        }
        return true;
    }

    private void rejectApplicant(Model model, Person personToReject) {
        personToReject.rejectPerson();
        model.refreshListWithPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectCommand // instanceof handles nulls
                && predicate.equals(((RejectCommand) other).predicate)); // state check
    }
}

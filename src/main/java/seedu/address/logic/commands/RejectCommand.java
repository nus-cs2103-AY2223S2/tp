package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Note;

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
    public static final String MESSAGE_REJECT_PERSON_SUCCESS = "Rejected Applicant: %1$s";

    public static final String MESSAGE_PERSON_CANNOT_BE_REJECTED = "%s has already been rejected!";


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
            throw new CommandException(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
        }

        Person personToReject = personList.get(0);
        Person rejectedPerson;

        /* this if-statement will check whether the applicant can be rejected.
        If applicant cannot be rejected, CommandException will be thrown */
        if (canRejectApplicant(model, personToReject)) {
            rejectedPerson = createRejectedPerson(personToReject);
            model.setPerson(personToReject,rejectedPerson);
        }
        model.refreshListWithPredicate(predicate);
        return new CommandResult(String.format(MESSAGE_REJECT_PERSON_SUCCESS, personToReject.getName()));
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

    /**
     * Creates and returns a {@code Person} with the details of {@code personToReject}
     */
    private static Person createRejectedPerson(Person personToReject) {
        assert personToReject != null;

        Name updatedName = personToReject.getName();
        Phone updatedPhone = personToReject.getPhone();
        Email updatedEmail = personToReject.getEmail();
        Address updatedAddress = personToReject.getAddress();
        Status updatedStatus = personToReject.getStatus(); //User not allowed to edit applicant status directly
        Set<Note> updatedTags = personToReject.getNotes();

        Person rejectedPerson =
                new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStatus, updatedTags);
        rejectedPerson.rejectPerson();
        return rejectedPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectCommand // instanceof handles nulls
                && predicate.equals(((RejectCommand) other).predicate)); // state check
    }
}

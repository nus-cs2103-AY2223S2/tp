package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InterviewDateTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.testutil.PersonBuilder;

public class AdvanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        AdvanceCommand advanceCommand = new AdvanceCommand(new NamePhoneNumberPredicate(
                new Name("invalid name"), new Phone("9999")), Optional.empty());
        CommandException exceptionThrown = assertThrows(CommandException.class, () -> {
            advanceCommand.execute(model);
        });
        assertEquals(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE, exceptionThrown.getMessage());
    }

    @Test
    public void execute_validShortlistedPersonWithDateTime_throwsCommandException() throws ParseException {
        Person personToAdvance = new PersonBuilder().withStatus(String.valueOf(Status.SHORTLISTED)).build();
        model.addPerson(personToAdvance);
        InterviewDateTime dateTime = new InterviewDateTime("01-01-2023 12:00");
        AdvanceCommand advanceCommand = new AdvanceCommand(new NamePhoneNumberPredicate(
                personToAdvance.getName(), personToAdvance.getPhone()), Optional.ofNullable(dateTime));
        CommandException exceptionThrown = assertThrows(CommandException.class, () -> {
            advanceCommand.execute(model);
        });

        assertEquals(Messages.MESSAGE_INTERVIEW_DATETIME_NOT_REQUIRED, exceptionThrown.getMessage());
        model.deletePerson(personToAdvance);
    }

    @Test
    public void execute_validAppliedPersonWithNoDateTime_throwsCommandException() {
        Person personToAdvance = new PersonBuilder().withStatus(String.valueOf(Status.APPLIED)).build();
        model.addPerson(personToAdvance);
        AdvanceCommand advanceCommand = new AdvanceCommand(new NamePhoneNumberPredicate(
                personToAdvance.getName(), personToAdvance.getPhone()), Optional.empty());
        CommandException exceptionThrown = assertThrows(CommandException.class, () -> {
            advanceCommand.execute(model);
        });

        assertEquals(Messages.MESSAGE_INTERVIEW_DATETIME_IS_REQUIRED, exceptionThrown.getMessage());
        model.deletePerson(personToAdvance);
    }

    @Test
    public void execute_validAcceptedPerson_throwsCommandException() {
        Person personToAdvance = new PersonBuilder().withStatus(String.valueOf(Status.ACCEPTED)).build();
        model.addPerson(personToAdvance);
        AdvanceCommand advanceCommand = new AdvanceCommand(new NamePhoneNumberPredicate(
                personToAdvance.getName(), personToAdvance.getPhone()), Optional.empty());
        CommandException exceptionThrown = assertThrows(CommandException.class, () -> {
            advanceCommand.execute(model);
        });

        assertEquals(String.format(Messages.MESSAGE_PERSON_CANNOT_BE_ADVANCED, personToAdvance.getName().fullName),
                exceptionThrown.getMessage());
        model.deletePerson(personToAdvance);
    }

    @Test
    public void execute_validShortlistedPerson_shouldAdvanceToAccepted() throws Exception {
        Person personToAdvance = new PersonBuilder().withStatus(String.valueOf(Status.SHORTLISTED)).build();
        model.addPerson(personToAdvance);
        AdvanceCommand advanceCommand = new AdvanceCommand(new NamePhoneNumberPredicate(
                personToAdvance.getName(), personToAdvance.getPhone()), Optional.empty());

        advanceCommand.execute(model);

        assertEquals(Status.ACCEPTED, personToAdvance.getStatus());
        model.deletePerson(personToAdvance);
    }

    @Test
    public void execute_validAppliedPersonWithInterviewDate_shouldAdvanceToShortlisted() throws Exception {
        Person personToAdvance = new PersonBuilder().withStatus(String.valueOf(Status.APPLIED)).build();
        model.addPerson(personToAdvance);
        InterviewDateTime dateTime = new InterviewDateTime("01-01-2023 12:00");
        AdvanceCommand advanceCommand = new AdvanceCommand(new NamePhoneNumberPredicate(
                personToAdvance.getName(), personToAdvance.getPhone()), Optional.ofNullable(dateTime));

        advanceCommand.execute(model);

        assertEquals(Status.SHORTLISTED, personToAdvance.getStatus());
        model.deletePerson(personToAdvance);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        NamePhoneNumberPredicate alicePredicate = new NamePhoneNumberPredicate(alice.getName(), alice.getPhone());
        NamePhoneNumberPredicate bobPredicate = new NamePhoneNumberPredicate(bob.getName(), bob.getPhone());
        AdvanceCommand advanceFirstCommand = new AdvanceCommand(alicePredicate, Optional.empty());
        AdvanceCommand advanceSecondCommand = new AdvanceCommand(bobPredicate, Optional.empty());

        // same object -> returns true
        assertTrue(advanceFirstCommand.equals(advanceFirstCommand));

        // same values -> returns true
        AdvanceCommand advanceFirstCommandCopy = new AdvanceCommand(alicePredicate, Optional.empty());
        assertTrue(advanceFirstCommand.equals(advanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(advanceFirstCommand.equals(1));

        // null -> returns false
        assertFalse(advanceFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(advanceFirstCommand.equals(advanceSecondCommand));
    }
}

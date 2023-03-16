package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
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

        assertEquals(String.format(AdvanceCommand.MESSAGE_PERSON_CANNOT_BE_ADVANCED, personToAdvance.getName().fullName),
                exceptionThrown.getMessage());
        model.deletePerson(personToAdvance);
    }

    @Test
    public void execute_duplicateInterviewDateApplicant_failure() throws Exception {
        Person personToAdvance = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person clashingPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        InterviewDateTime clashingDateTime = new InterviewDateTime("02-03-2023 14:00");

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(personToAdvance.getName(), personToAdvance.getPhone());
        NamePhoneNumberPredicate clashingNamePhonePredicate =
                new NamePhoneNumberPredicate(clashingPerson.getName(), clashingPerson.getPhone());
        AdvanceCommand advanceCommand = new AdvanceCommand(namePhonePredicate, Optional.of(clashingDateTime));

        String expectedMessage = String.format(Messages.MESSAGE_DUPLICATE_INTERVIEW_DATE,
                clashingPerson.getName());
        model.refreshListWithPredicate(clashingNamePhonePredicate);

        assertCommandFailure(advanceCommand, model, expectedMessage);
    }

    @Test
    public void execute_validShortlistedPerson_shouldAdvanceToAccepted() throws Exception {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToAdvance = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToAdvance);
        Person advancedPerson = personInList.withStatus("ACCEPTED").build();
        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(advancedPerson.getName(), advancedPerson.getPhone());
        AdvanceCommand advanceCommand = new AdvanceCommand(namePhonePredicate, Optional.empty());

        String expectedMessage = String.format(AdvanceCommand.MESSAGE_ADVANCE_PERSON_SUCCESS,
                personToAdvance.getName());

        expectedModel.setPerson(personToAdvance, advancedPerson);
        expectedModel.updateFilteredPersonList(namePhonePredicate);

        assertCommandSuccess(advanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAppliedPersonWithInterviewDate_shouldAdvanceToShortlisted() throws Exception {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToAdvance = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToAdvance);
        InterviewDateTime dateTime = new InterviewDateTime("01-01-2023 13:00");
        Person advancedPerson = personInList.withStatus("SHORTLISTED")
                .withInterviewDateTime("01-01-2023 13:00").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(advancedPerson.getName(), advancedPerson.getPhone());
        AdvanceCommand advanceCommand = new AdvanceCommand(namePhonePredicate, Optional.of(dateTime));

        String expectedMessage = String.format(AdvanceCommand.MESSAGE_ADVANCE_PERSON_SUCCESS,
                personToAdvance.getName());

        expectedModel.setPerson(personToAdvance, advancedPerson);
        expectedModel.updateFilteredPersonList(namePhonePredicate);

        assertCommandSuccess(advanceCommand, model, expectedMessage, expectedModel);
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

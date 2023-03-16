package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NAME_PHONE_PREDICATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_PHONE_PREDICATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FORTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RejectCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_appliedStatusApplicant_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToReject = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person rejectedPerson = personInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), rejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(RejectCommand.MESSAGE_REJECT_PERSON_SUCCESS, rejectedPerson.getName());

        expectedModel.setPerson(personToReject, rejectedPerson);
        expectedModel.updateFilteredPersonList(namePhonePredicate);

        assertCommandSuccess(rejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_shortlistedStatusApplicant_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToReject = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person rejectedPerson = personInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), rejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(RejectCommand.MESSAGE_REJECT_PERSON_SUCCESS, rejectedPerson.getName());

        expectedModel.setPerson(personToReject, rejectedPerson);
        expectedModel.updateFilteredPersonList(namePhonePredicate);

        assertCommandSuccess(rejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_acceptedStatusApplicant_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToReject = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person rejectedPerson = personInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), rejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(RejectCommand.MESSAGE_REJECT_PERSON_SUCCESS, rejectedPerson.getName());

        expectedModel.setPerson(personToReject, rejectedPerson);
        expectedModel.updateFilteredPersonList(namePhonePredicate);

        assertCommandSuccess(rejectCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Edits filtered list to show the person that the user wants to reject.
     * User is still able to reject applicants from outside the filtered list
     * without having to call the list command again to show the unfiltered list
     */
    @Test
    public void execute_validNamePhoneFilteredList_success() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_FORTH_PERSON.getZeroBased());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person rejectedPerson = personInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), rejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(RejectCommand.MESSAGE_REJECT_PERSON_SUCCESS, rejectedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToReject, rejectedPerson);
        expectedModel.updateFilteredPersonList(namePhonePredicate);

        assertCommandSuccess(rejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectedStatusApplicant_failure() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person personToReject = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person rejectedPerson = personInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), rejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(RejectCommand.MESSAGE_PERSON_CANNOT_BE_REJECTED,
                rejectedPerson.getName());

        assertCommandFailure(rejectCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidNameUnfilteredList_failure() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person invalidNamePerson = personInList.withName("wrongName").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(invalidNamePerson.getName(), invalidNamePerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        assertCommandFailure(rejectCommand, model, Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
    }

    /**
     * Edit filtered list but user is still able to reject applicants from outside the filtered list
     * without having to call the list command again to show the unfiltered list
     */
    @Test
    public void execute_invalidNameFilteredList_failure() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person invalidNamePerson = personInList.withName("wrongName").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(invalidNamePerson.getName(), invalidNamePerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        assertCommandFailure(rejectCommand, model, Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
    }

    @Test
    public void execute_invalidPhoneUnfilteredList_failure() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person invalidNamePerson = personInList.withPhone("999").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(invalidNamePerson.getName(), invalidNamePerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        assertCommandFailure(rejectCommand, model, Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
    }

    /**
     * Edit filtered list but user is still able to reject applicants from outside the filtered list
     * without having to call the list command again to show the unfiltered list
     */
    @Test
    public void execute_invalidPhoneFilteredList_failure() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        PersonBuilder personInList = new PersonBuilder(personToReject);
        Person invalidNamePerson = personInList.withPhone("999").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(invalidNamePerson.getName(), invalidNamePerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        assertCommandFailure(rejectCommand, model, Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);
    }

    @Test
    public void execute_invalidNamePhoneCombinationFilteredList_failure() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_FORTH_PERSON.getZeroBased());
        Person wrongPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        PersonBuilder personInList = new PersonBuilder(personToReject);
        PersonBuilder wrongPersonInList = new PersonBuilder(wrongPerson);

        Person rejectedPerson = personInList.withStatus("REJECTED").build();
        Person wrongRejectedPerson = wrongPersonInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), wrongRejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);

        assertCommandFailure(rejectCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidNamePhoneCombinationUnfilteredList_failure() {
        Person personToReject = model.getFilteredPersonList().get(INDEX_FORTH_PERSON.getZeroBased());
        Person wrongPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(personToReject);
        PersonBuilder wrongPersonInList = new PersonBuilder(wrongPerson);

        Person rejectedPerson = personInList.withStatus("REJECTED").build();
        Person wrongRejectedPerson = wrongPersonInList.withStatus("REJECTED").build();

        NamePhoneNumberPredicate namePhonePredicate =
                new NamePhoneNumberPredicate(rejectedPerson.getName(), wrongRejectedPerson.getPhone());
        RejectCommand rejectCommand = new RejectCommand(namePhonePredicate);

        String expectedMessage = String.format(Messages.MESSAGE_NO_PERSON_WITH_NAME_AND_PHONE);

        assertCommandFailure(rejectCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final RejectCommand standardCommand = new RejectCommand(NAME_PHONE_PREDICATE_AMY);

        // same values -> returns true
        RejectCommand commandWithSamePredicate = new RejectCommand(NAME_PHONE_PREDICATE_AMY);
        assertTrue(standardCommand.equals(commandWithSamePredicate));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different predicates -> returns false
        assertFalse(standardCommand.equals(new RejectCommand(NAME_PHONE_PREDICATE_BOB)));
    }

}

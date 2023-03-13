package seedu.sudohr.logic.commands;

import static seedu.sudohr.logic.commands.CommandTestUtil.*;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalPersons.BOB;
import static seedu.sudohr.testutil.TypicalPersons.getTypicalSudoHr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.person.Person;
import seedu.sudohr.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getSudoHr().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateIdOnly_throwsCommandException() {
        Person personInList = model.getSudoHr().getPersonList().get(0);
        Person duplicateIdPerson = new PersonBuilder(BOB).withId(personInList.getId().value)
                .build();
        assertCommandFailure(new AddCommand(duplicateIdPerson), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateEmailOnly_throwsCommandException() {
        Person personInList = model.getSudoHr().getPersonList().get(0);
        Person duplicateEmailPerson = new PersonBuilder(BOB).withEmail(personInList.getEmail().value)
                .build();
        assertCommandFailure(new AddCommand(duplicateEmailPerson), model, AddCommand.MESSAGE_DUPLICATE_EMAIL);
    }

    @Test
    public void execute_duplicatePhoneNumberOnly_throwsCommandException() {
        Person personInList = model.getSudoHr().getPersonList().get(0);
        Person duplicatePhonePerson = new PersonBuilder(BOB).withPhone(personInList.getPhone().value)
                .build();
        assertCommandFailure(new AddCommand(duplicatePhonePerson), model, AddCommand.MESSAGE_DUPLICATE_PHONE);
    }

    // Duplicate phone number should be identified first
    @Test
    public void execute_differentIdOnly_throwsCommandException() {
        Person personInList = model.getSudoHr().getPersonList().get(0);
        Person differentIdPerson = new PersonBuilder(personInList).withId(VALID_ID_BOB)
                .build();
        assertCommandFailure(new AddCommand(differentIdPerson), model, AddCommand.MESSAGE_DUPLICATE_PHONE);
    }

}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_nothingToUndo_showErrorMessage() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_NOTHING_TO_UNDO;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoAddPerson_success() {
        Person rachel = new PersonBuilder().withName("Rachel").build();
        AddCommand addRachelCommand = new AddCommand(rachel);

        try {
            addRachelCommand.execute(model);
        } catch (CommandException e) {
            // should never happen since there is no person with name "Rachel" in TypicalPersons
            throw new RuntimeException(e);
        }

        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoEditPerson_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        try {
            editCommand.execute(model);
        } catch (CommandException e) {
            // should never happen since the EditPersonDescriptor is valid
            throw new RuntimeException(e);
        }

        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoClearCommand_success() {
        ClearCommand clearCommand = new ClearCommand();
        clearCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoDeletePerson_success() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        try {
            deleteCommand.execute(model);
        } catch (CommandException e) {
            // should never happen since there is at least one person in the TypicalPersons
            throw new RuntimeException(e);
        }

        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }
}

package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.commons.core.Messages.MESSAGE_MODULE_MISSING;
import static seedu.modtrek.logic.commands.CommandTestUtil.DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.IS1103;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.testutil.EditModuleDescriptorBuilder;
import seedu.modtrek.testutil.ModuleBuilder;

class EditCommandTest {

    private Model model = new ModelManager(getTypicalDegreeProgression(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder().build();
        EditCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder(editedModule).build();
        EditCommand editCommand = new EditCommand(CS2100.getCode(),
                descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule);

        Model expectedModel = new ModelManager(new DegreeProgression(model
                .getDegreeProgression()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Code moduleToEdit = IS1103.getCode();
        Module lastModule = IS1103;

        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule = moduleInList.withCode(VALID_CODE_MA2002).withCredit(VALID_CREDIT_MA2002)
                .withTags(VALID_TAG_CS1101S).build();

        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_MA2002)
                .withCredit(VALID_CREDIT_MA2002).withTags(VALID_TAG_CS1101S).build();
        EditCommand editCommand = new EditCommand(moduleToEdit, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new DegreeProgression(model.getDegreeProgression()), new UserPrefs());
        expectedModel.setModule(lastModule, editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(CS2100.getCode(),
                new EditCommand.EditModuleDescriptor());
        Module editedModule = model.getFilteredModuleList().get(0);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule);

        Model expectedModel = new ModelManager(
                new DegreeProgression(model.getDegreeProgression()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Module moduleInFilteredList = model.getFilteredModuleList().get(0);
        Module editedModule = new ModuleBuilder(moduleInFilteredList).withCode(VALID_CODE_MA2002).build();
        EditCommand editCommand = new EditCommand(CS2100.getCode(),
                new EditModuleDescriptorBuilder().withCode(VALID_CODE_MA2002).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new DegreeProgression(model.getDegreeProgression()),
                new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(0);
        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditCommand editCommand = new EditCommand(ST2334.getCode(), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        // edit module in filtered list into a duplicate in address book
        Module moduleInList = model.getDegreeProgression().getModuleList().get(0);
        EditCommand editCommand = new EditCommand(ST2334.getCode(),
                new EditModuleDescriptorBuilder(moduleInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCode(VALID_CODE_MA2002).build();
        EditCommand editCommand = new EditCommand(CS1101S.getCode(), descriptor);

        assertCommandFailure(editCommand, model, String.format(MESSAGE_MODULE_MISSING, CS1101S.getCode()));
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(CS2100.getCode(), DESC_CS1101S);

        // same values -> returns true
        EditCommand.EditModuleDescriptor copyDescriptor = new EditCommand.EditModuleDescriptor(DESC_CS1101S);
        EditCommand commandWithSameValues = new EditCommand(CS2100.getCode(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ViewCommand(false)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(ST2334.getCode(), DESC_CS1101S)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(CS2100.getCode(), DESC_MA2002)));
    }

}

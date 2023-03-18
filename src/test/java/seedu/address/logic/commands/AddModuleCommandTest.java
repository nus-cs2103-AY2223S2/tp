package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalModules;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws CommandException {
        Module module = TypicalModules.CS2040S;
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();

        CommandResult result = new AddModuleCommand(module).execute(modelStub);

        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, module),
                result.getFeedbackToUser());
        assertEquals(Arrays.asList(module), modelStub.modulesAdded);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddModuleCommand command = new AddModuleCommand(TypicalModules.CS2040S);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module module = TypicalModules.CS2040S;
        AddModuleCommand command = new AddModuleCommand(module);
        ModelStub modelStub = new ModelStubWithModule(module);

        assertThrows(CommandException.class,
                AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        AddModuleCommand addCs2040sCommand = new AddModuleCommand(TypicalModules.CS2040S);
        AddModuleCommand addSt2334Command = new AddModuleCommand(TypicalModules.ST2334);

        // same object -> returns true
        assertTrue(addCs2040sCommand.equals(addCs2040sCommand));

        // same values -> returns true
        AddModuleCommand addCs2040sCommandCopy = new AddModuleCommand(TypicalModules.CS2040S);
        assertTrue(addCs2040sCommand.equals(addCs2040sCommandCopy));

        // different types -> returns false
        assertFalse(addCs2040sCommand.equals(1));

        // null -> returns false
        assertFalse(addCs2040sCommand.equals(null));

        // different module -> return false
        assertFalse(addCs2040sCommand.equals(addSt2334Command));
    }

    /**
     * A {@code Model} stub that always accepts the module being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(ReadOnlyModule module) {
            return false;
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }
    }

    /**
     * A {@code Model} stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

        public ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(ReadOnlyModule module) {
            requireNonNull(module);
            return this.module.isSameModule((Module) module);
        }
    }
}

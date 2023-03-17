package seedu.vms.logic.commands.vaccination;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.vaccination.DeleteVaxTypeParser;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.testutil.SampleVaxTypeData;

public class DeleteVaxTypeCommandTest {

    private static final VaxType TO_DELETE = SampleVaxTypeData.TYPE_REAL;
    private static final VaxType NON_EXISTENT = SampleVaxTypeData.TYPE_1;

    private VaxTypeModelStub model;


    @BeforeEach
    public void addVaxType() {
        model = new VaxTypeModelStub();
        model.manager.add(SampleVaxTypeData.TYPE_REAL);
    }


    @Test
    public void execute_validName_vaxTypeDeleted() throws Exception {
        attemptDelete(TO_DELETE.getName());
        assertTrue(!model.manager.contains(TO_DELETE.getName()));
    }


    @Test
    public void execute_nonExistent_exceptionThrown() {
        assertThrows(CommandException.class, () -> attemptDelete(NON_EXISTENT.getName()));
        assertTrue(model.manager.contains(TO_DELETE.getName()));
    }


    private void attemptDelete(String command) throws Exception {
        ArgumentMultimap argsMap = ArgumentTokenizer.tokenize(command);
        new DeleteVaxTypeParser().parse(argsMap).execute(model);
    }
}

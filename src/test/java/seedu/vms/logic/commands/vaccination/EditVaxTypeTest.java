package seedu.vms.logic.commands.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.vaccination.EditVaxTypeParser;
import seedu.vms.model.Age;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.testutil.SampleVaxTypeData;

public class EditVaxTypeTest {
    private static final VaxType ORIGINAL = SampleVaxTypeData.TYPE_1;
    private static final VaxType EDITED_NO_RENAME = new VaxType(
            ORIGINAL.getGroupName(),
            SampleVaxTypeData.GROUPS_REAL,
            ORIGINAL.getMinAge(),
            ORIGINAL.getMaxAge(),
            ORIGINAL.getAllergyReqs(),
            ORIGINAL.getHistoryReqs());
    private static final VaxType EDITED_RENAME = SampleVaxTypeData.TYPE_REAL;

    private static final String CMD_VALID_NO_RENAME = String.join(" ",
            SampleVaxTypeData.CMD_NAME_1,
            SampleVaxTypeData.CMD_GROUPS_REAL);
    private static final String CMD_VALID_RENAME = String.join(" ",
            SampleVaxTypeData.CMD_NAME_1,
            "--" + CliSyntax.PREFIX_NAME + " " + SampleVaxTypeData.CMD_NAME_REAL,
            SampleVaxTypeData.CMD_GROUPS_REAL,
            SampleVaxTypeData.CMD_MIN_AGE_REAL,
            "--" + CliSyntax.PREFIX_MAX_AGE + " " + Age.MAX_VALUE,
            SampleVaxTypeData.CMD_ALLERGY_REQS_REAL,
            SampleVaxTypeData.CMD_HISTORY_REQS_REAL);
    private static final String CMD_NON_EXISTENT_NAME = String.join(" ",
            SampleVaxTypeData.CMD_NAME_REAL,
            SampleVaxTypeData.CMD_GROUPS_REAL);
    private static final String CMD_EXISTING_RENAME = String.join(" ",
            SampleVaxTypeData.CMD_NAME_1,
            "--" + CliSyntax.PREFIX_NAME + " " + SampleVaxTypeData.CMD_NAME_REAL);

    private VaxTypeModelStub model;


    @BeforeEach
    public void initializeModel() {
        model = new VaxTypeModelStub();
        model.manager.add(ORIGINAL);
    }


    @Test
    public void execute_validEditNoRename_vaxTypeEdited() throws Exception {
        attemptExecution(CMD_VALID_NO_RENAME);
        assertEquals(EDITED_NO_RENAME, model.manager.get(EDITED_NO_RENAME.getName()).get());
        assertTrue(model.manager.contains(ORIGINAL.getName()));
    }


    @Test
    public void execute_validEdit_vaxTypeEdited() throws Exception {
        attemptExecution(CMD_VALID_RENAME);
        assertEquals(EDITED_RENAME, model.manager.get(EDITED_RENAME.getName()).get());
        assertTrue(!model.manager.contains(ORIGINAL.getName()));
    }


    @Test
    public void execute_nonExistentName_exceptionThrown() {
        assertThrows(CommandException.class, () -> attemptExecution(CMD_NON_EXISTENT_NAME));
    }


    @Test
    public void execute_existingRename_exceptionThrown() {
        model.manager.add(EDITED_RENAME);
        assertThrows(CommandException.class, () -> attemptExecution(CMD_EXISTING_RENAME));
    }


    private void attemptExecution(String command) throws Exception {
        new EditVaxTypeParser().parse(command).execute(model);
    }
}

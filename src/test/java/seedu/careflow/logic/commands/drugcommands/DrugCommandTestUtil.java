package seedu.careflow.logic.commands.drugcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class DrugCommandTestUtil {
    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, CareFlowModel actualModel,
                                            CommandResult expectedCommandResult, CareFlowModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, CareFlowModel, CommandResult, CareFlowModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, CareFlowModel actualModel, String expectedMessage,
                                            CareFlowModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the CareFlow, filtered drug list and selected drug in {@code actualModel} remain unchanged
     */
    public static void assertDrugCommandFailure(Command command, CareFlowModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        DrugInventory expectedDrugRecord = new DrugInventory(actualModel.getDrugInventory());
        List<Drug> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDrugList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDrugRecord, actualModel.getDrugInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredDrugList());
    }

    /**
     * Updates {@code CareFlowModel}'s filtered list to show only the drug at the given {@code targetIndex} in the
     * {@code model}'s CareFlow.
     */
    public static void showDrugAtIndex(CareFlowModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDrugList().size());

        Drug drug = model.getFilteredDrugList().get(targetIndex.getZeroBased());
        final String[] splitName = drug.getTradeName().tradeName.split("\\s+");
        model.updateFilteredDrugList(new TradeNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDrugList().size());
    }
}


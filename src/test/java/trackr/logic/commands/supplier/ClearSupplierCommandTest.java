package trackr.logic.commands.supplier;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;

public class ClearSupplierCommandTest {

    @Test
    public void execute_emptySupplierList_success() throws ParseException {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearSupplierCommand(),
                model,
                String.format(ClearSupplierCommand.MESSAGE_SUCCESS, ModelEnum.SUPPLIER),
                expectedModel);
    }

    @Test
    public void execute_nonEmptySupplierList_success() throws ParseException {
        Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
        expectedModel.setItemList(ModelEnum.SUPPLIER);

        assertCommandSuccess(new ClearSupplierCommand(),
                model,
                String.format(ClearSupplierCommand.MESSAGE_SUCCESS, ModelEnum.SUPPLIER),
                expectedModel);
    }

}

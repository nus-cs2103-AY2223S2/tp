package trackr.logic.commands.menu;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showMenuItemAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.commands.ListItemCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListMenuItemCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
        expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() throws ParseException {
        assertCommandSuccess(new ListMenuItemCommand(),
                model,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.MENUITEM.toString().toLowerCase()),
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() throws ParseException {
        showMenuItemAtIndex(model, INDEX_FIRST_OBJECT);
        assertCommandSuccess(new ListMenuItemCommand(),
                model,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.MENUITEM.toString().toLowerCase()),
                expectedModel);
    }
}

package trackr.logic.commands.menu;

import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.menu.MenuItem;
import trackr.testutil.MenuItemBuilder;

public class AddMenuCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
    }

    @Test
    public void execute_newMenuItem_sucess() throws ParseException {
        MenuItem validItem = new MenuItemBuilder().build();

        Model expected = new ModelManager(model.getSupplierList(), model.getTaskList(),
                            model.getMenu(), model.getOrderList(), new UserPrefs());
        expected.addItem(validItem, ModelEnum.MENUITEM);

        assertCommandSuccess(new AddMenuItemCommand(validItem), model,
                            String.format(AddMenuItemCommand.MESSAGE_SUCCESS,
                                        ModelEnum.MENUITEM,
                                        validItem),
                            expected);
    }

    @Test
    public void execute_duplicateMenuItem_throwsCommandException() {
        MenuItem inList = model.getMenu().getItemList().get(0);
        assertCommandFailure(new AddMenuItemCommand(inList),
                            model,
                            String.format(AddMenuItemCommand.MESSAGE_DUPLICATE_ITEM,
                                            ModelEnum.MENUITEM,
                                            ModelEnum.MENUITEM));
    }
}

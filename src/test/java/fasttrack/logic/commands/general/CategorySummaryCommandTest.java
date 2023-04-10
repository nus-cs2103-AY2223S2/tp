package fasttrack.logic.commands.general;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.ui.ScreenType;

public class CategorySummaryCommandTest {

    private Model dataModel = new ModelManager();
    private Category category = new UserDefinedCategory("Food", "Food");
    private Category miscCategory = new MiscellaneousCategory();

    @BeforeEach
    public void setUp() {
        dataModel.addCategory(category);
        dataModel.addCategory(miscCategory);
    }

    @Test
    public void execute_categorySummary_success() throws CommandException {
        CommandResult expectedCommandResult = new CommandResult(
                String.format(category.getCategoryName()
                        + " summary:\n" + category.getSummary()),
                ScreenType.CATEGORY_SCREEN);
        CategorySummaryCommand categorySummaryCommand = new CategorySummaryCommand(Index.fromOneBased(1));
        assertEquals(expectedCommandResult, categorySummaryCommand.execute(dataModel));
    }

    @Test
    public void execute_categorySummaryMisc_success() throws CommandException {
        CommandResult expectedCommandResult = new CommandResult(
                String.format(miscCategory.getCategoryName()
                    + " summary:\n" + miscCategory.getSummary()),
                ScreenType.CATEGORY_SCREEN);

        CategorySummaryCommand categorySummaryCommand = new CategorySummaryCommand(Index.fromOneBased(2));
        assertEquals(categorySummaryCommand.execute(dataModel), expectedCommandResult);
    }
}

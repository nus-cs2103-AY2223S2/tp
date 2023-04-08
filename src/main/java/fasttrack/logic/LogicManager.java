package fasttrack.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import fasttrack.commons.core.GuiSettings;
import fasttrack.commons.core.LogsCenter;
import fasttrack.logic.commands.Command;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.logic.parser.ExpenseTrackerParser;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.Model;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.storage.Storage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model dataModel;
    private final Storage storage;
    private final ExpenseTrackerParser expenseTrackerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model dataModel, Storage storage) {
        this.dataModel = dataModel;
        this.storage = storage;
        expenseTrackerParser = new ExpenseTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        Command command = expenseTrackerParser.parseCommand(commandText);
        commandResult = command.execute(dataModel);

        try {
            storage.saveExpenseTracker(dataModel.getExpenseTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyExpenseTracker getExpenseTracker() {
        return dataModel.getExpenseTracker();
    }

    @Override
    public ObservableList<Category> getFilteredCategoryList() {
        return dataModel.getFilteredCategoryList();
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return dataModel.getFilteredExpenseList();
    }

    @Override
    public ObservableList<RecurringExpenseManager> getRecurringExpenseManagerList() {
        return dataModel.getRecurringExpenseGenerators();
    }

    @Override
    public SimpleObjectProperty<ParserUtil.Timespan> getAppliedTimeSpanFilter() {
        return dataModel.getAppliedTimeSpanFilter();
    }

    @Override
    public SimpleObjectProperty<Category> getAppliedCategoryFilter() {
        return dataModel.getAppliedCategoryFilter();
    }

    @Override
    public Path getAddressBookFilePath() {
        return dataModel.getExpenseTrackerFilePath();
    }


    @Override
    public GuiSettings getGuiSettings() {
        return dataModel.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        dataModel.setGuiSettings(guiSettings);
    }
}

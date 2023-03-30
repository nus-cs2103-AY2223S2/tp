package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ExpenseTrackerParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model dataModel;
    private final Storage storage;
    private final ExpenseTrackerParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model dataModel, Storage storage) {
        this.dataModel = dataModel;
        this.storage = storage;
        addressBookParser = new ExpenseTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(dataModel);

        try {
            logger.info("expense tracker before save " + dataModel.getExpenseTracker().getCategoryList().size());
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

package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command2;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ListingBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model2;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.listing.Listing;
import seedu.address.storage.Storage2;


/**
 * The main LogicManager of the app.
 */
public class LogicManager2 implements Logic2 {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager2.class);

    private final Model2 model;
    private final Storage2 storage;
    private final ListingBookParser listingBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager2(Model2 model, Storage2 storage) {
        this.model = model;
        this.storage = storage;
        listingBookParser = new ListingBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command2 command = listingBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveListingBook(model.getListingBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyListingBook getListingBook() {
        return model.getListingBook();
    }

    @Override
    public ObservableList<Listing> getFilteredListingList() {
        return model.getFilteredListingList();
    }

    @Override
    public Path getListingBookFilePath() {
        return model.getListingBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}

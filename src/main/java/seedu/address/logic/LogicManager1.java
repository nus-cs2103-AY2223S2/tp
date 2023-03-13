package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command1;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InternshipCatalogueParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model1;
import seedu.address.model.ReadOnlyInternshipCatalogue;
import seedu.address.model.internship.Internship;
import seedu.address.storage.Storage1;

/**
 * The main LogicManager of the app.
 */
public class LogicManager1 implements Logic1 {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model1 model;
    private final Storage1 storage;
    private final InternshipCatalogueParser internshipCatalogueParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager1(Model1 model, Storage1 storage) {
        this.model = model;
        this.storage = storage;
        this.internshipCatalogueParser = new InternshipCatalogueParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command1 command = this.internshipCatalogueParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternshipCatalogue(model.getInternshipCatalogue());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternshipCatalogue getInternshipCatalogue() {
        return model.getInternshipCatalogue();
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getInternshipCatalogueFilePath() {
        return model.getInternshipCatalogueFilePath();
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

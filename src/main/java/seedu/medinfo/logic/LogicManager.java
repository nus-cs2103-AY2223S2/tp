package seedu.medinfo.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.medinfo.commons.core.GuiSettings;
import seedu.medinfo.commons.core.LogsCenter;
import seedu.medinfo.logic.commands.Command;
import seedu.medinfo.logic.commands.CommandResult;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.logic.parser.MedInfoParser;
import seedu.medinfo.logic.parser.exceptions.ParseException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MedInfoParser medInfoParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        medInfoParser = new MedInfoParser();
    }

    @Override
    public List<String> getStatsInfo() {
        return model.getStatsInfo();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = medInfoParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveMedInfo(model.getMedInfo());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMedInfo getMedInfo() {
        return model.getMedInfo();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Ward> getFilteredWardList() {
        return model.getFilteredWardList();
    }

    @Override
    public Path getMedInfoFilePath() {
        return model.getMedInfoFilePath();
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

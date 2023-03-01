package seedu.address.logic;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CareFlowParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.drug.Drug;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.readonly.ReadOnlyDrugInventory;
import seedu.address.model.readonly.ReadOnlyPatientRecord;
import seedu.address.storage.Storage;

import java.nio.file.Path;
import java.util.logging.Logger;

public class CareFlowLogicManager implements CareFlowLogic{
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(CareFlowLogicManager.class);

    private final CareFlowModel model;
    private final Storage storage;
    private final CareFlowParser careFlowParser;

    /**
     * Constructs a {@code CareFlowLogicManager} with the given {@code CareFlowModel} and {@code Storage}.
     */
    public CareFlowLogicManager(CareFlowModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.careFlowParser = new CareFlowParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        Command command = careFlowParser.parseCommand(commandText);
        commandResult = command.execute(model);

//        try {
            // specific storage methods to be created ltr
//            storage.savePatientRecord(model.getPatientRecord());
//            storage.saveDrugInventory(model.getDrugInventory());
//        } catch (IOException e) {
//            throw new CommandException(FILE_OPS_ERROR_MESSAGE + e, e);
//        }
        return commandResult;
    }

    @Override
    public ReadOnlyPatientRecord getPatientRecord() {
        return model.getPatientRecord();
    }

    @Override
    public ReadOnlyDrugInventory getDrugInventory() {
        return model.getDrugInventory();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Drug> getFilteredDrugList() {
        return model.getFilteredDrugList();
    }

    @Override
    public Path getPatientRecordFilePath() {
        return model.getPatientRecordFilePath();
    }

    @Override
    public Path getDrugInventoryFilePath() {
        return model.getDrugInventoryFilePath();
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

package seedu.careflow.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.commons.core.LogsCenter;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.logic.parser.CareFlowParser;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.storage.CareFlowStorage;

/**
 * The main LogicManager of the CareFlow application.
 */
public class CareFlowLogicManager implements CareFlowLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(CareFlowLogicManager.class);

    private final CareFlowModel model;
    private final CareFlowStorage storage;
    private final CareFlowParser careFlowParser;

    /**
     * Constructs a {@code CareFlowLogicManager} with the given {@code CareFlowModel} and {@code Storage}.
     */
    public CareFlowLogicManager(CareFlowModel model, CareFlowStorage storage) {
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

        try {
            storage.savePatientRecord(model.getPatientRecord());
            storage.saveDrugInventory(model.getDrugInventory());
        } catch (IOException e) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + e, e);
        }
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
    public ReadOnlyHospitalRecords getHospitalRecord() {
        return model.getHospitalRecords();
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
    public ObservableList<Hospital> getHospitalList() {
        return model.getHospitalList();
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

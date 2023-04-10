package seedu.patientist.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.logic.commands.Command;
import seedu.patientist.logic.commands.CommandResult;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.logic.parser.PatientistParser;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.Model;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.person.Person;
import seedu.patientist.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PatientistParser patientistParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        patientistParser = new PatientistParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = patientistParser.parseCommand(commandText);
        commandResult = command.execute(model);

        model.getPatientist().updatePersonList();

        try {
            storage.savePatientist(model.getPatientist());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPatientist getPatientist() {
        return model.getPatientist();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getPatientistPath() {
        return model.getPatientistFilePath();
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

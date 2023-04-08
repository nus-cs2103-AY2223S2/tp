package taa.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import taa.commons.core.GuiSettings;
import taa.commons.core.LogsCenter;
import taa.logic.commands.Command;
import taa.logic.commands.CommandResult;
import taa.logic.commands.exceptions.CommandException;
import taa.logic.parser.TaaParser;
import taa.logic.parser.exceptions.ParseException;
import taa.model.Model;
import taa.model.student.Student;
import taa.storage.Storage;
import taa.storage.TaaData;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TaaParser taaParser;
    private final ArrayList<String> cmdHistory;
    private int curCmdIdx;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        taaParser = new TaaParser();
        cmdHistory = new ArrayList<>();
        curCmdIdx = 0;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        cmdHistory.add(commandText);
        curCmdIdx = cmdHistory.size();

        CommandResult commandResult;
        Command command = taaParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTaaData(model.getTaaData());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public TaaData getTaaData() {
        return model.getTaaData();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getTaaDataFilePath() {
        return model.getTaaDataFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public String getPrevCmd() {
        return curCmdIdx <= 0 ? null : cmdHistory.get(--curCmdIdx);
    }

    @Override
    public String getNextCmd() {
        return curCmdIdx >= cmdHistory.size() - 1 ? null : cmdHistory.get(++curCmdIdx);
    }
}

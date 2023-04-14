package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ModuleTrackerParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyModuleTracker;
import seedu.address.model.module.Module;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ModuleTrackerParser moduleTrackerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        moduleTrackerParser = new ModuleTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = moduleTrackerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveModuleTracker(model.getModuleTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyModuleTracker getModuleTracker() {
        return model.getModuleTracker();
    }

    @Override
    public ObservableList<Module> getDisplayedModuleList() {
        return model.getDisplayedModuleList();
    }

    @Override
    public Path getModuleTrackerFilePath() {
        return model.getModuleTrackerFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    public String getWorkToday() {

        String deadlinesToday = "Deadlines Today: \n";
        String timeSlotsToday = "Time Slots Today: \n";

        for (int i = 0; i < model.getDisplayedModuleList().size(); i++) {
            boolean isDeadlineNull = model.getDisplayedModuleList().get(i).getDeadline().value == null;
            boolean isSlotNull = model.getDisplayedModuleList().get(i).getTimeSlot().getDay() == null;

            // Handle deadlines
            if (!isDeadlineNull) {
                deadlinesToday += getDeadlinesToday(i);
            }

            // Handle Time Slots
            if (!isSlotNull) {
                timeSlotsToday += getSlotsToday(i);
            }

        }
        String output = deadlinesToday + "\n\n" + timeSlotsToday;
        if (output.length() <= 40) {
            output = "No Time Slots or Deadlines Today! :)";
        }
        return output;
    }

    private String getDeadlinesToday(int index) {
        LocalDate today = LocalDate.now();
        Module module = model.getDisplayedModuleList().get(index);
        String fullLine = "";

        boolean isDeadlineToday = module.getDeadline().value.toLocalDate().isEqual(today);

        if (isDeadlineToday) {
            String moduleName = module.getName().fullName;
            String moduleType = module.getTags().toString();
            moduleType = moduleType.replace("[", "").replace("]", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            LocalDateTime date = module.getDeadline().value;
            String formattedDate = formatter.format(date);

            fullLine = moduleName + " " + moduleType + " by: " + formattedDate + "\n";
        }

        return fullLine;
    }

    private String getSlotsToday(int index) {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String fullLine = "";
        Module module = model.getDisplayedModuleList().get(index);

        boolean isSlotToday = module.getTimeSlot().getLocalDateTime().getDayOfWeek().equals(today);

        if (isSlotToday) {
            String moduleName = module.getName().fullName;
            String moduleType = module.getTags().toString();
            moduleType = moduleType.replace("[", "").replace("]", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            LocalDateTime date = module.getTimeSlot().getLocalDateTime();
            String formattedDate = formatter.format(date);

            fullLine = moduleName + " " + moduleType + " Starting at: " + formattedDate + "\n";
        }

        return fullLine;
    }
}

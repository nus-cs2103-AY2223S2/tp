package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.archive.Archive;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.LectureEditInfo;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.injector.Injector;
import seedu.address.logic.injector.NavigationInjector;
import seedu.address.logic.parser.TrackerParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.trackereventsystem.TrackerEventSystem;
import seedu.address.logic.trackereventsystem.observers.ListObserver;
import seedu.address.logic.trackereventsystem.observers.NavigationObserver;
import seedu.address.model.DisplayListLevel;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyNavigation;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TrackerParser trackerParser;
    private final Archive archive;
    private final Injector navigationInjector;
    private final TrackerEventSystem trackerEventSystem;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.navigationInjector = new NavigationInjector();
        trackerParser = new TrackerParser();
        trackerEventSystem = new TrackerEventSystem();
        archive = new Archive(storage);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {

        // Subscribe to tracker system
        ListObserver listObserver = new ListObserver(model);
        NavigationObserver navObserver = new NavigationObserver(model);
        this.trackerEventSystem.addOnModuleModifiedObserver(listObserver, navObserver);
        this.trackerEventSystem.addOnLectureModifiedObserver(listObserver, navObserver);
        this.trackerEventSystem.addOnVideoModifiedObserver(listObserver);

        logger.info("----------------[USER COMMAND][" + commandText + "]");

        commandText = navigationInjector.inject(commandText, model);

        logger.info("----------------[POST INJECTION USER COMMAND][" + commandText + "]");

        Command command = trackerParser.parseCommand(commandText);
        CommandResult commandResult = command.execute(model);

        if (!commandResult.getPath().isEmpty()) {
            if (commandResult.isExporting()) {
                archive.exportToArchive(commandResult.getPath().get(),
                        model.getTracker(), commandResult.isOverwriting(), storage.getTrackerFilePath());
            } else {
                // modify CommandResult object to include ModuleEditInfo objects to update trackerEventSystem
                commandResult = archive.importFromArchive(commandResult.getPath().get(), model,
                        commandResult.isImportingWholeArchive(),
                        commandResult.isOverwriting(), commandResult.getModuleCodesToImport(), trackerEventSystem);
            }
        }

        triggerTrackerEvents(commandResult);

        // Unsubscribe to tracker system
        this.trackerEventSystem.removeOnModuleModifiedObserver(navObserver, listObserver);
        this.trackerEventSystem.removeOnLectureModifiedObserver(navObserver, listObserver);
        this.trackerEventSystem.removeOnVideoModifiedObserver(listObserver);

        try {
            storage.saveTracker(model.getTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTracker getTracker() {
        return model.getTracker();
    }

    @Override
    public ReadOnlyNavigation getNavigation() {
        return model.getNavigation();
    }

    @Override
    public ObservableList<? extends ReadOnlyModule> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public ObservableList<? extends ReadOnlyLecture> getFilteredLectureList() {
        return model.getFilteredLectureList();
    }

    @Override
    public ObservableList<? extends Video> getFilteredVideoList() {
        return model.getFilteredVideoList();
    }

    @Override
    public Path getTrackerFilePath() {
        return model.getTrackerFilePath();
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
    public DisplayListLevel getLastListLevel() {
        return model.getLastListLevel();
    }

    private void triggerTrackerEvents(CommandResult commandResult) {
        List<ModuleEditInfo> moduleEditInfoList = commandResult.getModuleEditInfoList();
        for (ModuleEditInfo info : moduleEditInfoList) {
            trackerEventSystem.triggerOnModuleEditedEvent(info.getOriginalModule(), info.getEditedModule());
        }

        List<LectureEditInfo> lectureEditInfoList = commandResult.getLectureEditInfoList();
        for (LectureEditInfo info : lectureEditInfoList) {
            trackerEventSystem.triggerOnLectureEditedEvent(info.getModuleCode(), info.getOriginaLecture(),
                    info.getEditedLecture());
        }

        List<VideoEditInfo> videoEditInfoList = commandResult.getVideoEditInfoList();
        for (VideoEditInfo info : videoEditInfoList) {
            trackerEventSystem.triggerOnVideoEditedEvent(info.getModuleCode(), info.getLectureName(),
                    info.getOriginalVideo(), info.getEditedVideo());
        }
    }

}

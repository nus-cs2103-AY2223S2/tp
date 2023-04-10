package tfifteenfour.clipboard.model;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.logging.Logger;

import tfifteenfour.clipboard.commons.core.GuiSettings;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.Command;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Roster roster;
    private final UserPrefs userPrefs;

    private String commandTextExecuted;
    private Command commandExecuted;
    private CurrentSelection currentSelection;

    /**
     * Initializes a ModelManager with the given roster and userPrefs.
     */
    public ModelManager(ReadOnlyRoster roster, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(roster, userPrefs);

        logger.fine("Initializing with address book: " + roster + " and user prefs " + userPrefs);

        this.roster = new Roster(roster);
        this.userPrefs = new UserPrefs(userPrefs);
        this.currentSelection = new CurrentSelection();
    }

    public ModelManager() {
        this(new Roster(), new UserPrefs());
    }

    /**
     * Constructs a ModelManager with the given roster, user preferences, and previous state modifying command.
     *
     * @param roster The roster to use in the ModelManager.
     * @param userPrefs The user preferences to use in the ModelManager.
     * @param commandTextExecuted The previous state modifying command to use in the ModelManager.
     */
    public ModelManager(Roster roster, UserPrefs userPrefs, CurrentSelection currentSelection) {
        this.roster = roster;
        this.userPrefs = userPrefs;
        this.currentSelection = currentSelection;
    }

    @Override
    public void setCommandExecuted(Command command) {
        this.commandExecuted = command;
    }

    @Override
    public Command getCommandExecuted() {
        return this.commandExecuted;
    }

    @Override
    public void setCommandTextExecuted(String commandText) {
        this.commandTextExecuted = commandText;
    }

    @Override
    public String getCommandTextExecuted() {
        return this.commandTextExecuted;
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getRosterFilePath() {
        return userPrefs.getRosterFilePath();
    }

    @Override
    public void setRosterFilePath(Path rosterFilePath) {
        requireNonNull(rosterFilePath);
        userPrefs.setRosterFilePath(rosterFilePath);
    }

    //=========== Roster ================================================================================

    @Override
    public void setRoster(ReadOnlyRoster roster) {
        this.roster.resetData(roster);
    }

    @Override
    public Roster getRoster() {
        return roster;
    }

    @Override
    public Model copy() {
        return new ModelManager(this.roster.copy(), this.userPrefs, this.currentSelection.copy());
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return roster.equals(other.roster)
                && userPrefs.equals(other.userPrefs);
    }

    public CurrentSelection getCurrentSelection() {
        return currentSelection;
    }
}


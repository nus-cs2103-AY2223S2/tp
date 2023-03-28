package mycelium.mycelium.ui.commandbox.mode;

import java.util.Optional;
import java.util.logging.Logger;

import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.model.FuzzyManager;
import mycelium.mycelium.ui.MainWindow;

/**
 * Represents a mode that allows the user to search for clients and projects.
 */
public class SearchMode extends Mode {
    public static final ModeType MODE = ModeType.SEARCH_MODE;
    private final Logger logger = LogsCenter.getLogger(getClass());
    private final MainWindow mainWindow;
    private final Logic logic;
    private String cachedInput;
    private Optional<String> additive;

    /**
     * Creates a new SearchMode.
     *
     * @param mainWindow The main window
     * @param logic The logic component
     */
    public SearchMode(MainWindow mainWindow, Logic logic) {
        this.mainWindow = mainWindow;
        this.logic = logic;
        this.additive = Optional.empty();
    }

    @Override
    public void setupMode(String prevInput) {
        logger.info("Setting up search mode, caching input: " + prevInput);
        cachedInput = prevInput;
        mainWindow.setCommandBoxStyleListening();
        mainWindow.focusEntityPanel();
    }

    @Override
    public void onInputChange(String input) {
        mainWindow.setClients(FuzzyManager.rankItems(logic.getFilteredClientList(), input));
        mainWindow.setProjects(FuzzyManager.rankItems(logic.getFilteredProjectList(), input));
    }

    @Override
    public Optional<Mode> onInputSubmit(String input) {
        additive = mainWindow.getSelectedEntityIdentifier();
        return Optional.of(new CommandMode(mainWindow));
    }

    @Override
    public void teardownMode() {
        logger.info("Tearing down search mode: restoring input: " + cachedInput);
        mainWindow.setClients(logic.getFilteredClientList());
        mainWindow.setProjects(logic.getFilteredProjectList());
        mainWindow.setCommandBoxStyleDefault();
        mainWindow.setCommandBoxInput(cachedInput);
        if (additive.isPresent()) {
            mainWindow.appendCommandBoxHighlighted(additive.get());
        }
    }

    @Override
    public ModeType getModeType() {
        return MODE;
    }
}

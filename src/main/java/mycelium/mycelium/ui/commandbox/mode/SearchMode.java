package mycelium.mycelium.ui.commandbox.mode;

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

    /**
     * Creates a new SearchMode.
     *
     * @param mainWindow The main window
     * @param logic The logic component
     */
    public SearchMode(MainWindow mainWindow, Logic logic) {
        this.mainWindow = mainWindow;
        this.logic = logic;
    }

    @Override
    public void setupMode(String prevInput) {
        logger.info("Setting up search mode");
        cachedInput = prevInput;
        mainWindow.setCommandBoxStyleListening();
    }

    @Override
    public void onInputChange(String input) {
        mainWindow.setClients(FuzzyManager.rankItems(logic.getFilteredClientList(), input));
        mainWindow.setProjects(FuzzyManager.rankItems(logic.getFilteredProjectList(), input));
    }

    @Override
    public void onInputSubmit(String input) {
        // Do Nothing
    }

    @Override
    public String teardownMode() {
        logger.info("Tearing down search mode");
        mainWindow.setClients(logic.getFilteredClientList());
        mainWindow.setProjects(logic.getFilteredProjectList());
        mainWindow.setCommandBoxStyleDefault();
        return cachedInput;
    }

    @Override
    public ModeType getModeType() {
        return MODE;
    }
}

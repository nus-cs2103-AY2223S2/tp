package mycelium.mycelium.ui.commandbox.mode;

import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.ui.MainWindow;

/**
 * Represents a mode that allows the user to enter commands.
 */
public class CommandMode extends Mode {
    private static final ModeType MODE = ModeType.COMMAND_MODE;

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final MainWindow mainWindow;
    private String cachedInput = "";

    /**
     * Creates a new CommandMode.
     *
     * @param mainWindow
     */
    public CommandMode(MainWindow mainWindow) {
        requireAllNonNull(mainWindow);
        this.mainWindow = mainWindow;
    }

    @Override
    public void setupMode(String prevInput) {
        logger.info("Setting up command mode, caching input: " + prevInput);
        mainWindow.setCommandBoxStyleDefault();
        cachedInput = prevInput;
    }

    @Override
    public void onInputChange(String input) {
        // Do Nothing
        mainWindow.setCommandBoxStyleDefault();
    }

    @Override
    public void onInputSubmit(String input) {
        if (input.equals("")) {
            return;
        }
        try {
            mainWindow.executeCommand(input);
        } catch (CommandException | ParseException e) {
            mainWindow.setCommandBoxStyleError();
        }
    }

    @Override
    public String teardownMode() {
        logger.info("Tearing down command mode, restoring input: " + cachedInput);
        mainWindow.setCommandBoxStyleDefault();
        return cachedInput;
    }

    @Override
    public ModeType getModeType() {
        return MODE;
    }
}

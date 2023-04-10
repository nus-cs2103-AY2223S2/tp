package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;

// @@author seanfirefox-reused
/**
 * A handle to the {@code HelpWindow} of the application. Referenced from AB4.
 */
public class HelpWindowHandle extends StageHandle {

    public static final String HELP_WINDOW_TITLE = "Help";

    public HelpWindowHandle(Stage helpWindowStage) {
        super(helpWindowStage);
    }

    /**
     * Returns true if a help window is currently present in the application.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(HELP_WINDOW_TITLE);
    }
}


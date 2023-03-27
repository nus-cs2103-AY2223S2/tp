package seedu.internship.ui.pages;

import javafx.scene.layout.Region;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.ui.UiPart;

/**
 * This class represents UI component shown to the right of the InternshipList.
 */
abstract public class Page extends UiPart<Region> {

    protected Page(String fxmlFileName) {
        super(fxmlFileName);
    }

    /**
     * Returns the right type of Page according to commandResult.
     *
     * @param commandResult A CommandResult.
     * @return A concrete sub-type of Page.
     */
    public static Page of(CommandResult commandResult) {
        Page resultPage = null;

        switch(commandResult.getResultType()) {
        case STATS:
//            resultPage = new StatsPage(commandResult.getStatistics());
            break;
        case CLASH:
            resultPage = new ClashInfoPage(commandResult.getClashingEvents());
            break;
        case SHOW_INFO:
            resultPage = new InternshipInfoPage(commandResult.getInternship(), commandResult.getEvents());
            break;
        case HOME:
            resultPage = new HomePage(commandResult.getEvents());
            break;
        case CALENDAR:
            resultPage = new CalendarPage(commandResult.getEvents());
            break;
        default:
            break;
        }
        return resultPage;
    }
}

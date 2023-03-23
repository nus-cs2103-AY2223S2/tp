package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.tag.TodoType;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }

    /**
     * Changes the address in the status footer bar to suit current displaying contents.
     */
    public void setStatusFooterBarText(Logic logic, TodoType todoType) {
        switch (todoType) {
        case BOTH:
            String taskFilePath = Paths.get(".").resolve(logic.getTodoListFilePath()).toString()
                    + " and "
                    + Paths.get(".").resolve(logic.getNoteListFilePath()).toString();

            saveLocationStatus.setText(taskFilePath);
            break;

        case TODO:
            saveLocationStatus.setText(Paths.get(".").resolve(logic.getTodoListFilePath()).toString());
            break;

        case NOTE:
            saveLocationStatus.setText(Paths.get(".").resolve(logic.getNoteListFilePath()).toString());
            break;

        default:
            saveLocationStatus.setText(Paths.get(".").resolve(logic.getAddressBookFilePath()).toString());
        }

    }

}

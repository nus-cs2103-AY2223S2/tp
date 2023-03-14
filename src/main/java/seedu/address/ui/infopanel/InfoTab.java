package seedu.address.ui.infopanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonListPanel;
import seedu.address.ui.UiPart;

import java.util.logging.Logger;

/**
 * Panel containing detailed information about a person.
 */
public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoTab.class);

    private DetailedInfo detailedInfo;

    @FXML
    private Label name;

    @FXML
    private Label identity;

    @FXML
    private StackPane detailedInfoPlaceholder;

    /**
     * Creates a {@code InfoTab} with the given {@code protagonist} and {@code tab}.
     */
    public InfoTab(Person protagonist, String tab) {

        super(FXML);
        if (tab != null) {
            if (tab.equals("c")) {
                detailedInfo = new DetailedContact(protagonist);
            } else if (tab.equals("m")) {
                detailedInfo = new DetailedModule(protagonist);
            } else {
                detailedInfo = new DetailedSkill(protagonist);
            }
        }

        if (protagonist != null) {
            name.setText(protagonist.getName().fullName);
            detailedInfoPlaceholder.getChildren().add(detailedInfo.getRoot());
        }
    }

}

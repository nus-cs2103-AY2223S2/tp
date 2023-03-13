package codoc.ui.infopanel;

import codoc.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import codoc.ui.UiPart;

/**
 * Panel containing detailed information about a person.
 */
public class InfoTab extends UiPart<Region> {

    private static final String FXML = "InfoTab.fxml";

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
                detailedInfo = new DetailedContact();
            } else if (tab.equals("m")) {
                detailedInfo = new DetailedModule();
            } else {
                detailedInfo = new DetailedSkill();
            }
        }

        if (protagonist != null) {
            name.setText(protagonist.getName().fullName);
            detailedInfoPlaceholder.getChildren().add(detailedInfo.getRoot());
        }
    }

}

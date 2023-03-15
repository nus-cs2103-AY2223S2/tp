package codoc.ui.infopanel;

import codoc.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * DetailedContact controller for showing contact details at DetailedInfoPanel.
 */
public class DetailedContact extends DetailedInfo {

    private static final String FXML = "DetailedContact.fxml";

    @FXML
    private Label github;

    @FXML
    private Label email;

    @FXML
    private Label linkedin;

    /**
     * Creates a {@code DetailedContact} tab with the given {@code protagonist}.
     */
    public DetailedContact(Person protagonist) {
        super(FXML);
        github.setText(protagonist.getGithub().value);
        email.setText(protagonist.getEmail().value);
        linkedin.setText(protagonist.getLinkedin().value);
    }

}

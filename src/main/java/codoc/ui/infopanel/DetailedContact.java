package codoc.ui.infopanel;

import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.Person;
import codoc.ui.MainWindow;
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

    private MainWindow mainWindow;

    /**
     * Creates a {@code DetailedContact} tab with the given {@code protagonist}.
     */
    public DetailedContact(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        Person protagonist = mainWindow.getLogic().getProtagonist();
        email.setText(protagonist.getEmail().value);
        github.setText(protagonist.getGithub().value == null ? "Not Added" : "@" + protagonist.getGithub().value);
        linkedin.setText(protagonist.getLinkedin().value == null ? "Not Added" : protagonist.getLinkedin().value);
    }

    @FXML
    private void viewContactTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view c");
    }

    @FXML
    private void viewModulesTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view m");
    }

    @FXML
    private void viewSkillsTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view s");
    }

}

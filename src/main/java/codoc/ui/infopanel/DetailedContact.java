package codoc.ui.infopanel;

import codoc.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

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

    @FXML
    private Button copyEmailButton;

    @FXML
    private Button copyGithubButton;

    @FXML
    private Button copyLinkedinButton;


    private Person protagonist;

    /**
     * Creates a {@code DetailedContact} tab with the given {@code protagonist}.
     */
    public DetailedContact(Person protagonist) {
        super(FXML);
        this.protagonist = protagonist;
        github.setWrapText(true);
        linkedin.setWrapText(true);
        github.setText(protagonist.getGithub().value);
        email.setText(protagonist.getEmail().value);
        github.setText(protagonist.getGithub().value == null ? "Not Added" : "@" + protagonist.getGithub().value);
        linkedin.setText(protagonist.getLinkedin().value == null ? "Not Added" : protagonist.getLinkedin().value);


    }
    @FXML
    private void copyEmailUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(protagonist.getEmail().value);
        clipboard.setContent(url);
        getListener().copyText("email address");
    }
    @FXML
    private void copyGithubUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(protagonist.getGithub().value == null ? "" : "@" + protagonist.getGithub().value);
        clipboard.setContent(url);
        getListener().copyText("GitHub URL");
    }
    @FXML
    private void copyLinkedinUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(protagonist.getLinkedin().value == null ? "" : protagonist.getLinkedin().value);
        clipboard.setContent(url);
        getListener().copyText("LinkedIn URL");
    }

}

package codoc.ui.infopanel;

import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.ui.MainWindow;
import codoc.ui.UiEventListener;
import codoc.ui.UiPart;
import codoc.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

/**
 * Represents DetailedInfoPanel which is the lower part of the InfoPanel.
 */
public abstract class DetailedInfo extends UiPart<Region> {

    private Person protagonist;
    private MainWindow.ClickListener listener;

    /**
     * Creates a {@code DetailedInfoPanel} with the given {@code fxmlFileUrl}.
     */
    public DetailedInfo(String fxmlFileUrl) {
        super(fxmlFileUrl);
    }

    public void setListener(MainWindow.ClickListener listener) {
        this.listener = listener;
    }

    @FXML
    private void viewSkillsTab() throws CommandException, ParseException {
        listener.viewTab();
    }

}
